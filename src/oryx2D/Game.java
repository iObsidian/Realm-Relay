package oryx2D;

import oryx2D.entity.mob.Player;
import oryx2D.graphics.Screen;
import oryx2D.input.Keyboard;
import oryx2D.input.Mouse;
import oryx2D.level.Level;
import oryx2D.level.TileCoordinate;
import rotmg.WebMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

	private JFrame frame;
	private static final String TITLE = "Oryx2D";

	//

	private static final double frameRate = 60;

	private static final int scale = 6; // (50 / 8 = 6.25)

	private static final int width = 1000 / scale;
	private static final int height = 750 / scale;

	private static final int backgroundColor = 0000000;

	//

	private static boolean showFPS = true;
	private int currentFPS = 0;

	//

	private boolean running = false;
	private Thread thread;

	//

	private Keyboard key;

	private Level level = new Level(500, 500);

	private Player player;

	private Screen screen;

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		TileCoordinate playerSpawn = new TileCoordinate(113, 159);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);

		addKeyListener(key);

		try {
			BufferedImage image = ImageIO.read(Game.class.getResource("/oryx2D/cursor/cursor.png"));
			frame.setCursor(frame.getToolkit().createCustomCursor(
					image, new Point(image.getHeight() / 2, image.getWidth() / 2),
					"null"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public static void main(String[] args) {
		WebMain webMain = new WebMain();
		//webMain.hook(this);

		Game game = new Game();
		game.frame.add(game);
		game.frame.setTitle(Game.TITLE);
		game.frame.setResizable(true);
		game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.pack();
		game.frame.setVisible(true);
		game.start();

	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this, "Render thread");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / frameRate;
		double delta = 0;
		int updates = 0;

		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) { //Only happens 60 times a second
				update();
				render();

				updates++;
				delta--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				currentFPS = updates;
				updates = 0;
			}
		}
		stop();
	}

	public void update() {

		//System.out.println("Squares : " + AbstractMap.squares.length + ", BO : " + AbstractMap.boDict.size() + ", GO : " + AbstractMap.goDict.size());

		key.update();
		player.update();
	}

	public void render() {

		if (!this.isFocusOwner()) {
			return;
		}

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		int xscroll = player.x - screen.width / 2;
		int yscroll = player.y - screen.height / 2;

		screen.setOffset(xscroll, yscroll);

		level.render(screen);
		player.render(screen);

		Graphics g = bs.getDrawGraphics();

		screen.drawPixels(g, getWidth(), getHeight());

		drawDebug(g);

		g.dispose(); //Release the memory
		bs.show(); //Show the buffer

	}

	private void drawDebug(Graphics g) {
		if (showFPS) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", Font.PLAIN, 20));

			if (showFPS) {
				g.drawString("frameRate : " + currentFPS, 80, 40);
			}


				/*g.fillRect(Mouse.getX() - 10, Mouse.getY() - 10, 10, 10);
				if (Mouse.getButton() != -1)
					g.drawString("Button: " + Mouse.getButton(), 80, 80);*/

		}
	}
}

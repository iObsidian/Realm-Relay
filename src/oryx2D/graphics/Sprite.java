package oryx2D.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Sprite {

	public int SIZE;
	public int[] pixels;

	public Sprite(String path) {

		int w = 0;
		int h = 0;

		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			w = image.getWidth();
			h = image.getHeight();

			SIZE = w;
			pixels = new int[SIZE * SIZE];

			image.getRGB(0, 0, w, h, pixels, 0, w); //write rgb pixels to pixels array
		} catch (Exception e) {
			System.err.println("Error with file : " + path + ", width : " + w + ", height : " + h + ".");
			e.printStackTrace();
		}
	}


	/*private void setColour(int colour) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = colour;
		}
	}*/



}

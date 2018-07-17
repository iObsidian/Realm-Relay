package oryx2D.entity.mob;

import flash.display.BitmapData;
import oryx2D.Game;
import oryx2D.graphics.Screen;
import oryx2D.input.Keyboard;
import oryx2D.input.Mouse;

public class Player extends Mob {

	private static BitmapData player_forward = new BitmapData("/oryx2D/textures/player/player_forward.png");
	private static BitmapData player_forward_1 = new BitmapData("/oryx2D/textures/player/player_forward1.png");
	private static BitmapData player_forward_2 = new BitmapData("/oryx2D/textures/player/player_forward2.png");

	private static BitmapData player_back = new BitmapData("/oryx2D/textures/player/player_back.png");
	private static BitmapData player_back_1 = new BitmapData("/oryx2D/textures/player/player_back1.png");
	private static BitmapData player_back_2 = new BitmapData("/oryx2D/textures/player/player_back2.png");

	private static BitmapData player_side = new BitmapData("/oryx2D/textures/player/player_side.png");
	private static BitmapData player_side_1 = new BitmapData("/oryx2D/textures/player/player_side1.png");

	private boolean walking = false;
	private Keyboard input;
	private BitmapData sprite;
	private int anim = 0;

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void update() {
		int xa = 0;
		int ya = 0;

		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}

		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;

		if (xa != 0 || ya != 0) { //If we move
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		updateShooting();
	}

	private void updateShooting() { //get angle of shooting based of center of screen
		if (Mouse.getButton() == 1) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			sprite = player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = player_forward_1;
				} else {
					sprite = player_forward_2;
				}
			}
		} else if (dir == 2) {
			sprite = player_back;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = player_back_1;
				} else {
					sprite = player_back_2;
				}
			}
		} else {

			if (dir == 3) {
				flip = 1;
			}

			sprite = player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = player_side_1;
				} else {
					sprite = player_side;
				}
			}
		}

		screen.render(x - sprite.width / 2, y - sprite.height / 2, sprite, flip);
	}

}

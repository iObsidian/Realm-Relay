package oryx2D.level.tile;

import flash.display.BitmapData;
import oryx2D.graphics.Screen;

public class Tile {

	public BitmapData sprite;
	public int x;
	public int y;

	public Tile(int x, int y, BitmapData sprite) {
		this(x, y, sprite, false);
	}

	public Tile(int x, int y, BitmapData sprite, boolean isSolid) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.solid = isSolid;
	}

	public static BitmapData voidTile = new BitmapData("/oryx2D/textures/tiles/void.png");

	private boolean solid = false;

	/**
	 * Every tiles render themselves
	 */
	public void render(int x, int y, Screen screen) {
		screen.render(x * sprite.height, y * sprite.width, sprite);
	}

	public boolean isSolid() {
		return solid;
	}

	private void setSolid(boolean solid) {
		this.solid = solid;
	}
}
package oryx2D.level.tile;

import oryx2D.graphics.Screen;
import oryx2D.graphics.Sprite;

public class Tile {

	public Sprite sprite;
	public int x;
	public int y;

	public Tile(int x, int y, Sprite sprite) {
		this(x, y, sprite, false);
	}

	public Tile(int x, int y, Sprite sprite, boolean isSolid) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.solid = isSolid;
	}

	public static Sprite spawn_grass_tile = new Sprite("/oryx2D/textures/tiles/grass.png");
	public static Sprite spawn_hedge_tile = new Sprite("/oryx2D/textures/tiles/hedge.png");
	public static Sprite spawn_water_tile = new Sprite("/oryx2D/textures/tiles/water.png");
	public static Sprite spawn_wall1_tile = new Sprite("/oryx2D/textures/tiles/wall1.png");
	public static Sprite spawn_wall2_tile = new Sprite("/oryx2D/textures/tiles/wall2.png");
	public static Sprite spawn_floor_tile = new Sprite("/oryx2D/textures/tiles/floor.png");

	public static Sprite voidTile = new Sprite("/oryx2D/textures/tiles/void.png");

	public final static int col_spawn_grass = 0xff50FF35;
	public final static int col_spawn_hedge = 0; //unused
	public final static int col_wpawn_water = 0; //unused;
	public final static int col_spawn_wall1 = 0xff808080;
	public final static int col_spawn_wall2 = 0xff303030;
	public final static int col_spawn_floor = 0xff7F3300;

	private boolean solid = false;

	/**
	 * Every tiles render themselves
	 */
	public void render(int x, int y, Screen screen) {
		screen.render(x * sprite.SIZE, y * sprite.SIZE, sprite);
	}

	public boolean isSolid() {
		return solid;
	}

	private void setSolid(boolean solid) {
		this.solid = solid;
	}
}
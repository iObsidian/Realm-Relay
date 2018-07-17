package oryx2D.level;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import oryx2D.graphics.Screen;
import rotmg.map.AbstractMap;
import rotmg.objects.Square;

public class Level {

	public static BitmapData voidTile = new BitmapData("/oryx2D/textures/tiles/void.png");


	public static final int TILE_SIZE = 8;

	protected Vector<Square> tiles = AbstractMap.squares;

	protected int width, height;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/*
	Render level
	https://www.youtube.com/watch?v=lNFAJPydlbU
	 */
	public void render(Screen screen) {
		// Deal with tiles as tile position (not pixel)

		// Used to draw exactly how needed (everything inside of screen)
		int halfTile = (TILE_SIZE / 2);

		int tilePositionTopLeft = screen.xOffset / TILE_SIZE;
		int tilePositionTopRight = ((screen.xOffset + screen.width) / TILE_SIZE) + halfTile;

		int tilePositionBottomLeft = screen.yOffset / TILE_SIZE;
		int tilePositionBottomRight = ((screen.yOffset + screen.height) / TILE_SIZE) + halfTile;

		for (int x = tilePositionTopLeft; x < tilePositionTopRight; x++) {
			for (int y = tilePositionBottomLeft; y < tilePositionBottomRight; y++) {
				if (getTile(x, y) != null) {
					getTile(x, y).render(x, y, screen);
				}
			}
		}
	}

	public Square getTile(int x, int y) {
		for (Square t : tiles) {
			if ((t.x == x) && (t.y == y)) {
				return t;
			}
		}
		return null;
	}

}

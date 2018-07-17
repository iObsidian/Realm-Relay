package oryx2D.level;

import oryx2D.graphics.Screen;
import oryx2D.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static oryx2D.level.tile.Tile.voidTile;

public class Level {

	public static final int TILE_SIZE = 8;

	protected List<Tile> tiles = new ArrayList<>();

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
				if (getTile(x,y) != null) {
					getTile(x, y).render(x, y, screen);
				}
			}
		}
	}

	public Tile getTile(int x, int y) {
		for (Tile t : tiles) {
			if ((t.x == x) && (t.y == y)) {
				return t;
			}
		}
		return new Tile(x, y, voidTile);
	}

}

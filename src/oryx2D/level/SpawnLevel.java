package oryx2D.level;

import oryx2D.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			int[] tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					Tile t;

					if (tiles[x + y * width] == Tile.col_spawn_floor) {
						t = new Tile(x, y, Tile.spawn_floor_tile);
					} else if (tiles[x + y * width] == Tile.col_spawn_grass) {
						t = new Tile(x, y, Tile.spawn_grass_tile);
					} else if (tiles[x + y * width] == Tile.col_spawn_hedge) {
						t = new Tile(x, y, Tile.spawn_hedge_tile, true);
					} else if (tiles[x + y * width] == Tile.col_spawn_wall1) {
						t = new Tile(x, y, Tile.spawn_wall1_tile, true);
					} else if (tiles[x + y * width] == Tile.col_spawn_wall2) {
						t = new Tile(x, y, Tile.spawn_wall2_tile, true);
					} else if (tiles[x + y * width] == Tile.col_wpawn_water) {
						t = new Tile(x, y, Tile.spawn_water_tile);
					} else {
						t = new Tile(x, y, Tile.voidTile);
					}

					this.tiles.add(t);

				}
			}
		} catch (Exception e) {

			System.err.println("Error with file : " + path);

			e.printStackTrace();
		}
	}

	protected void generateLevel() {
	}

}

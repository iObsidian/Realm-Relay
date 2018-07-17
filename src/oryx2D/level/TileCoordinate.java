package oryx2D.level;

public class TileCoordinate {

	private int x, y;

	public TileCoordinate(int x, int y) {
		this.x = x * Level.TILE_SIZE;
		this.y = y * Level.TILE_SIZE;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

}

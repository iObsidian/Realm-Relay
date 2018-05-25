package rotmg.objects;

import rotmg.game.map.Map;

public class Square {

	public Map map;
	public int x;
	public int y;
	public int tileType = 255;

	public Square(Map map, double x, double y) {
	}

	public void setTileType(int type) {
		this.tileType = type;
	}

}

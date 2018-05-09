package realmrelay.game.objects;

import realmrelay.game.map.Map;

public class Square {

	public Map map;
	public int x;
	public int y;
	public int tileType = 255;

	public Square(Map map, float x, float y) {
	}

	public void setTileType(int type) {
		this.tileType = type;
	}

}

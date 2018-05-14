package realmrelay.game.minimap.model;

import realmrelay.game._as3.Signal;

public class UpdateGroundTileVO extends Signal {
	public int tileX;

	public int tileY;

	public int tileType;

	public UpdateGroundTileVO(int param1, int param2, int param3) {
		super();
		this.tileX = param1;
		this.tileY = param2;
		this.tileType = param3;
	}
}

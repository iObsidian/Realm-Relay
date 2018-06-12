package rotmg.minimap.model;

import org.osflash.signals.Signal;

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

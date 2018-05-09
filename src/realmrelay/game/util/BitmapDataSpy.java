package realmrelay.game.util;

import realmrelay.packets.data.unused.BitmapData;

public class BitmapDataSpy extends BitmapData {
	public BitmapDataSpy(int width, int height, boolean param3, int param4) {
		super(width, height, param3, param4);
	}

	@Override
	public BitmapData clone() {
		return super.clone();
	}
}

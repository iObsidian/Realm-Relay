package rotmg.util;

import flash.display.BitmapData;

public class BitmapDataSpy extends BitmapData {
	public BitmapDataSpy(int width, int height, boolean param3, int param4) {
		super(width, height, param3, param4);
	}

	@Override
	public BitmapData clone() {
		return super.clone();
	}
}

package rotmg.text.view;

import flash.display.BitmapData;
import flash.geom.Matrix;
import rotmg.text.view.stringBuilder.StringBuilder;

public class BitmapTextFactory {
	static BitmapTextFactory instance;

	public static BitmapTextFactory getInstance() {
		if (instance == null) {
			instance = new BitmapTextFactory();
		}

		return instance;
	}

	public BitmapData make(StringBuilder loc1, int i, int i1, boolean b, Matrix identityMatrix, boolean b1) {
		return null;
	}
}

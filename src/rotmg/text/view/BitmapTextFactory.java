package rotmg.text.view;

import flash.display.BitmapData;
import flash.geom.Matrix;
<<<<<<< HEAD:src/kabam/rotmg/text/view/BitmapTextFactory.java
import kabam.rotmg.text.view.stringBuilder.StringBuilder;
=======
import rotmg.text.view.stringBuilder.StringBuilder;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/text/view/BitmapTextFactory.java

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

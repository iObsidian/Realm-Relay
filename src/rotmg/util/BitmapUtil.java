package rotmg.util;

import flash.display.BitmapData;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


/**
 * This method here does NOT match the client at all.
 * These method are UNTESTED
 */
public class BitmapUtil {

	public static BitmapData cropToBitmapData(BitmapData bitmapData, int x, int y, int width, int height) {
		return new BitmapData(bitmapData.image.getSubimage(x, y, width, height));
	}

	public static BitmapData mirror(BitmapData image, int width) {
		BitmapData b = new BitmapData(image.image);

		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-b.image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		b.image = op.filter(image.image, null);

		return b;
	}

	public static int mostCommonColor(BitmapData loc4) {
		return 1;
	}

	public static BitmapData rotateBitmapData(BitmapData loc4, int param2) {
		return null;
	}
}

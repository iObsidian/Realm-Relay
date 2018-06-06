package rotmg.util.redrawers;

import alde.flash.utils.Dictionary;
import flash.airglobal.BitmapFilterQuality;
import flash.airglobal.Shape;
import flash.display.BitmapData;
import flash.display.GradientType;
import flash.geom.Matrix;
import javafx.scene.effect.BlendMode;
import rotmg.util.PointUtil;
import rotmg.util.TextureRedrawer;
import spark.filter.GlowFilter;

public class GlowRedrawer {

	private static final int GRADIENT_MAX_SUB = 2631720;

	private static final GlowFilter GLOW_FILTER = new GlowFilter(0, 0.3, 12, 12, 2, BitmapFilterQuality.LOW, false, false);

	private static final GlowFilter GLOW_FILTER_ALT = new GlowFilter(0, 0.5, 16, 16, 3, BitmapFilterQuality.LOW, false, false);

	private static Matrix tempMatrix = new Matrix();

	private static Shape gradient = getGradient();

	private static Dictionary glowHashes = new Dictionary();

	public GlowRedrawer() {
		super();
	}

	public static BitmapData outlineGlow(BitmapData param1, int param2, double param3 =1.4, boolean param4 =false, int param5 =0) {
		String loc6 = getHash(param2, param3, param5);
		if (param4 && isCached(param1, loc6)) {
			return glowHashes[param1][loc6];
		}
		BitmapData loc7 = param1.clone();
		tempMatrix.identity();
		tempMatrix.scale(param1.width / 256, param1.height / 256);
		loc7.draw(gradient, tempMatrix, null, BlendMode.SUBTRACT);
		Bitmap loc8 = new Bitmap(param1);
		loc7.draw(loc8, null, null, BlendMode.ALPHA);
		TextureRedrawer.OUTLINE_FILTER.blurX = param3;
		TextureRedrawer.OUTLINE_FILTER.blurY = param3;
		TextureRedrawer.OUTLINE_FILTER.color = param5;
		loc7.applyFilter(loc7, loc7.rect, PointUtil.ORIGIN, TextureRedrawer.OUTLINE_FILTER);
		if (param2 != 4294967295) {
			if (Parameters.isGpuRender() && param2 != 0) {
				GLOW_FILTER_ALT.color = param2;
				loc7.applyFilter(loc7, loc7.rect, PointUtil.ORIGIN, GLOW_FILTER_ALT);
			} else {
				GLOW_FILTER.color = param2;
				loc7.applyFilter(loc7, loc7.rect, PointUtil.ORIGIN, GLOW_FILTER);
			}
		}
		if (param4) {
			cache(param1, param2, param3, loc7, param5);
		}
		return loc7;
	}

	private static void cache(BitmapData param1, int param2, double param3, BitmapData param4, int param5) {
		Object loc7 = null;
		String loc6 = getHash(param2, param3, param5);
		if (glowHashes.contains(param1)) {
			glowHashes[param1][loc6] = param4;
		} else {
			loc7 = {};
			loc7[loc6] = param4;
			glowHashes[param1] = loc7;
		}
	}

	private static boolean isCached(BitmapData param1, String param2) {
		Object loc3 = null;
		if (glowHashes.contains(param1)) {
			loc3 = glowHashes[param1];
			if (loc3.contains(param2)) {
				return true;
			}
		}
		return false;
	}

	private static String getHash(int param1, double param2, int param3) {
		return int(param2 * 10).toString() + param1 + param3;
	}

	private static Shape getGradient() {
		Shape loc1 = new Shape();
		Matrix loc2 = new Matrix();
		loc2.createGradientBox(256, 256, Math.PI / 2, 0, 0);
		loc1.graphics.beginGradientFill(GradientType.LINEAR,[0, GRADIENT_MAX_SUB], [1, 1], [127, 255],loc2);
		loc1.graphics.drawRect(0, 0, 256, 256);
		loc1.graphics.endFill();
		return loc1;
	}


}
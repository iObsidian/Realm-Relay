package rotmg.util;


import alde.flash.utils.Vector;
import flash.geom.ColorTransform;

public class MoreColorUtil {

	public static final Vector greyscaleFilterMatrix = new Vector<>(0.3, 0.59, 0.11, 0.0, 0.0, 0.3, 0.59, 0.11, 0.0, 0.0, 0.3, 0.59, 0.11, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

	public static final Vector redFilterMatrix = new Vector<>(0.3, 0.59, 0.11, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

	public static final ColorTransform identity = new ColorTransform();

	public static final ColorTransform invisible = new ColorTransform(1, 1, 1, 0, 0, 0, 0, 0);

	public static final ColorTransform transparentCT = new ColorTransform(1, 1, 1, 0.3, 0, 0, 0, 0);

	public static final ColorTransform slightlyTransparentCT = new ColorTransform(1, 1, 1, 0.7, 0, 0, 0, 0);

	public static final ColorTransform greenCT = new ColorTransform(0.6, 1, 0.6, 1, 0, 0, 0, 0);

	public static final ColorTransform lightGreenCT = new ColorTransform(0.8, 1, 0.8, 1, 0, 0, 0, 0);

	public static final ColorTransform veryGreenCT = new ColorTransform(0.2, 1, 0.2, 1, 0, 100, 0, 0);

	public static final ColorTransform transparentGreenCT = new ColorTransform(0.5, 1, 0.5, 0.3, 0, 0, 0, 0);

	public static final ColorTransform transparentVeryGreenCT = new ColorTransform(0.3, 1, 0.3, 0.5, 0, 0, 0, 0);

	public static final ColorTransform redCT = new ColorTransform(1, 0.5, 0.5, 1, 0, 0, 0, 0);

	public static final ColorTransform lightRedCT = new ColorTransform(1, 0.7, 0.7, 1, 0, 0, 0, 0);

	public static final ColorTransform veryRedCT = new ColorTransform(1, 0.2, 0.2, 1, 100, 0, 0, 0);

	public static final ColorTransform transparentRedCT = new ColorTransform(1, 0.5, 0.5, 0.3, 0, 0, 0, 0);

	public static final ColorTransform transparentVeryRedCT = new ColorTransform(1, 0.3, 0.3, 0.5, 0, 0, 0, 0);

	public static final ColorTransform blueCT = new ColorTransform(0.5, 0.5, 1, 1, 0, 0, 0, 0);

	public static final ColorTransform lightBlueCT = new ColorTransform(0.7, 0.7, 1, 1, 0, 0, 100, 0);

	public static final ColorTransform veryBlueCT = new ColorTransform(0.3, 0.3, 1, 1, 0, 0, 100, 0);

	public static final ColorTransform transparentBlueCT = new ColorTransform(0.5, 0.5, 1, 0.3, 0, 0, 0, 0);

	public static final ColorTransform transparentVeryBlueCT = new ColorTransform(0.3, 0.3, 1, 0.5, 0, 0, 0, 0);

	public static final ColorTransform purpleCT = new ColorTransform(1, 0.5, 1, 1, 0, 0, 0, 0);

	public static final ColorTransform veryPurpleCT = new ColorTransform(1, 0.2, 1, 1, 100, 0, 100, 0);

	public static final ColorTransform darkCT = new ColorTransform(0.6, 0.6, 0.6, 1, 0, 0, 0, 0);

	public static final ColorTransform veryDarkCT = new ColorTransform(0.4, 0.4, 0.4, 1, 0, 0, 0, 0);

	public static final ColorTransform makeWhiteCT = new ColorTransform(1, 1, 1, 1, 255, 255, 255, 0);

	public static int hsvToRgb(double param1, double param2, double param3) {
		double loc9 = 0;
		double loc10 = 0;
		double loc11 = 0;
		int loc4 = (int) (param1 / 60 % 6);
		double loc5 = param1 / 60 - Math.floor(param1 / 60);
		double loc6 = param3 * (1 - param2);
		double loc7 = param3 * (1 - loc5 * param2);
		double loc8 = param3 * (1 - (1 - loc5) * param2);
		switch (loc4) {
			case 0:
				loc9 = param3;
				loc10 = loc8;
				loc11 = loc6;
				break;
			case 1:
				loc9 = loc7;
				loc10 = param3;
				loc11 = loc6;
				break;
			case 2:
				loc9 = loc6;
				loc10 = param3;
				loc11 = loc8;
				break;
			case 3:
				loc9 = loc6;
				loc10 = loc7;
				loc11 = param3;
				break;
			case 4:
				loc9 = loc8;
				loc10 = loc6;
				loc11 = param3;
				break;
			case 5:
				loc9 = param3;
				loc10 = loc6;
				loc11 = loc7;
		}

		return 0; // TODO
		//return Math.min(255, Math.floor(loc9 * 255)) << 16 | Math.min(255, Math.floor(loc10 * 255)) << 8 | Math.min(255, Math.floor(loc11 * 255));
	}

	public static int randomColor() {
		return (int) (16777215 * Math.random());
	}

	public static int randomColor32() {

		return 0; //TODO
		//return 16777215 * Math.random() | 4278190080;
	}

	public static int transformColor(ColorTransform param1, int param2) {
		int loc3 = ((param2 & 16711680) >> 16) * param1.redMultiplier + param1.redOffset;
		loc3 = loc3 < 0 ? 0 : loc3 > 255 ? 255 : loc3;
		int loc4 = ((param2 & 65280) >> 8) * param1.greenMultiplier + param1.greenOffset;
		loc4 = loc4 < 0 ? 0 : loc4 > 255 ? 255 : loc4;
		int loc5 = (param2 & 255) * param1.blueMultiplier + param1.blueOffset;
		loc5 = loc5 < 0 ? 0 : loc5 > 255 ? 255 : loc5;
		return loc3 << 16 | loc4 << 8 | loc5;
	}

	public static ColorTransform copyColorTransform(ColorTransform param1) {
		return new ColorTransform(param1.redMultiplier, param1.greenMultiplier, param1.blueMultiplier, param1.alphaMultiplier, param1.redOffset, param1.greenOffset, param1.blueOffset, param1.alphaOffset);
	}

	public static ColorTransform lerpColorTransform(ColorTransform param1, ColorTransform param2, double param3) {
		if (param1 == null) {
			param1 = identity;
		}
		if (param2 == null) {
			param2 = identity;
		}
		double loc4 = 1 - param3;
		ColorTransform loc5 = new ColorTransform(param1.redMultiplier * loc4 + param2.redMultiplier * param3, param1.greenMultiplier * loc4 + param2.greenMultiplier * param3, param1.blueMultiplier * loc4 + param2.blueMultiplier * param3, param1.alphaMultiplier * loc4 + param2.alphaMultiplier * param3, param1.redOffset * loc4 + param2.redOffset * param3, param1.greenOffset * loc4 + param2.greenOffset * param3, param1.blueOffset * loc4 + param2.blueOffset * param3, param1.alphaOffset * loc4 + param2.alphaOffset * param3);
		return loc5;
	}

	public static int lerpColor(int param1, int param2, double param3) {
		double loc4 = 1 - param3;
		int loc5 = param1 >> 24 & 255;
		int loc6 = param1 >> 16 & 255;
		int loc7 = param1 >> 8 & 255;
		int loc8 = param1 & 255;
		int loc9 = param2 >> 24 & 255;
		int loc10 = param2 >> 16 & 255;
		int loc11 = param2 >> 8 & 255;
		int loc12 = param2 & 255;
		int loc13 = (int) (loc5 * loc4 + loc9 * param3);
		int loc14 = (int) (loc6 * loc4 + loc10 * param3);
		int loc15 = (int) (loc7 * loc4 + loc11 * param3);
		int loc16 = (int) (loc8 * loc4 + loc12 * param3);
		int loc17 = loc13 << 24 | loc14 << 16 | loc15 << 8 | loc16;
		return loc17;
	}

	public static double transformAlpha(ColorTransform param1, double param2) {
		int loc3 = (int) (param2 * 255);
		int loc4 = loc3 * param1.alphaMultiplier + param1.alphaOffset;
		loc4 = loc4 < 0 ? 0 : loc4 > 255 ? 255 : loc4;
		return loc4 / 255;
	}

	public static int multiplyColor(int param1, double param2) {
		int loc3 = (int) (((param1 & 16711680) >> 16) * param2);
		loc3 = loc3 < 0 ? 0 : loc3 > 255 ? 255 : loc3;
		int loc4 = (int) (((param1 & 65280) >> 8) * param2);
		loc4 = loc4 < 0 ? 0 : loc4 > 255 ? 255 : loc4;
		int loc5 = (int) ((param1 & 255) * param2);
		loc5 = loc5 < 0 ? 0 : loc5 > 255 ? 255 : loc5;
		return loc3 << 16 | loc4 << 8 | loc5;
	}

	public static int adjustBrightness(int param1, double param2) {
		/**int loc3 = param1 & 4278190080;

		 int loc4 = (int) (((param1 & 16711680) >> 16) + param2 * 255);
		 loc4 = loc4 < 0 ? 0 : loc4 > 255 ? 255 : loc4;
		 int loc5 = (int) (((param1 & 65280) >> 8) + param2 * 255);
		 loc5 = loc5 < 0 ? 0 : loc5 > 255 ? 255 : loc5;
		 int loc6 = (int) ((param1 & 255) + param2 * 255);
		 loc6 = loc6 < 0 ? 0 : loc6 > 255 ? 255 : loc6;
		 return loc3 | loc4 << 16 | loc5 << 8 | loc6;*/

		return 1;
	}

	public static Vector colorToShaderParameter(int param1) {
		double loc2 = (param1 >> 24 & 255) / 256;
		return new Vector<Double>(loc2 * ((param1 >> 16 & 255) / 256), loc2 * ((param1 >> 8 & 255) / 256), loc2 * ((param1 & 255) / 256), loc2);
	}

	public static int rgbToGreyscale(int param1) {
		/**int loc2 = (int) (((param1 & 16711680) >> 16) * 0.3 + ((param1 & 65280) >> 8) * 0.59 + (param1 & 255) * 0.11);
		 return (param1 && 4278190080) | loc2 << 16 | loc2 << 8 | loc2;*/

		return 1;
	}

	public static Vector singleColorFilterMatrix(int param1) {
		return new Vector<Integer>(0, 0, 0, 0, (param1 & 16711680) >> 16, 0, 0, 0, 0, (param1 & 65280) >> 8, 0, 0, 0, 0, param1 & 255, 0, 0, 0, 1, 0);
	}
}
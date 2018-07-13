package rotmg.util;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.utils.Dictionary;


public class BloodComposition {

	private static Dictionary<Integer, Vector<Integer>> idDict = new Dictionary<>();

	private static Dictionary<BitmapData, Vector<Integer>> imageDict = new Dictionary<>();

	public BloodComposition() {
		super();
	}

	public static Vector<Integer> getBloodComposition(int param1, BitmapData param2, double param3, int param4) {
		Vector<Integer> loc5 = idDict.get(param1);
		if (loc5 != null) {
			return loc5;
		}
		loc5 = new Vector<Integer>();
		Vector<Integer> loc6 = getColors(param2);
		int loc7 = 0;
		while (loc7 < loc6.length) {
			if (Math.random() < param3) {
				loc5.add(param4);
			} else {
				loc5.add(loc6.get((int) (loc6.length * Math.random())));
			}
			loc7++;
		}
		return loc5;
	}

	public static Vector<Integer> getColors(BitmapData param1) {
		Vector<Integer> loc2 = imageDict.get(param1);
		if (loc2 == null) {
			loc2 = buildColors(param1);
			imageDict.put(param1, loc2);
		}
		return loc2;
	}

	private static Vector<Integer> buildColors(BitmapData param1) {
		int loc4 = 0;
		int loc5 = 0;
		Vector<Integer> loc2 = new Vector<Integer>();
		int loc3 = 0;
		while (loc3 < param1.width) {
			loc4 = 0;
			while (loc4 < param1.height) {
				loc5 = param1.getPixel32(loc3, loc4);
				/*if ((loc5 & 4278190080) != 0) {*/
				loc2.add(loc5);
				//}
				loc4++;
			}
			loc3++;
		}
		return loc2;
	}

}

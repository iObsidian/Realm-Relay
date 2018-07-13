package rotmg.util;

import flash.display.BitmapData;
import flash.filters.BitmapFilter;
import flash.geom.ColorTransform;
import flash.utils.Dictionary;

public class CachingColorTransformer {

	private static Dictionary<BitmapData, Dictionary<Object, BitmapData>> bds = new Dictionary<>();

	public CachingColorTransformer() {
		super();
	}

	public static BitmapData transformBitmapData(BitmapData param1, ColorTransform param2) {
		BitmapData loc3 = null;
		Dictionary<Object, BitmapData> loc4 = bds.get(param1);
		if (loc4 != null) {
			loc3 = loc4.get(param2);
		} else {
			loc4 = new Dictionary<>();
			bds.put(param1, loc4);
		}
		if (loc3 == null) {
			loc3 = param1.clone();
			loc3.colorTransform(loc3.rect, param2);
			loc4.put(param2, loc3);
		}
		return loc3;
	}

	public static BitmapData filterBitmapData(BitmapData param1, BitmapFilter param2) {
		Object loc3 = null;
		Dictionary loc4 = bds.get(param1);
		if (loc4 != null) {
			loc3 = loc4.get(param2);
		} else {
			loc4 = new Dictionary<>();
			bds.put(param1, loc4);
		}
		if (loc3 == null) {
			loc3 = param1.clone();
			//loc3.applyFilter(loc3, loc3.rect, new Point(), param2);
			loc4.put(param2, loc3);
		}
		return (BitmapData) loc3;
	}

	public static BitmapData alphaBitmapData(BitmapData param1, double param2) {
		int loc3 = (int) (param2 * 100);
		ColorTransform loc4 = new ColorTransform(1, 1, 1, loc3 / 100);
		return transformBitmapData(param1, loc4);
	}

	public static void clear() {
		for (Dictionary<?, BitmapData> loc1 : bds) {
			for (BitmapData loc2 : loc1) {
				loc2.dispose();
			}
		}
		bds = new Dictionary<>();
	}

}

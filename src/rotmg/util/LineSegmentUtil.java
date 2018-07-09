package rotmg.util;

import flash.geom.Point;

public class LineSegmentUtil {

	public LineSegmentUtil() {
		super();
	}

	public static Point intersection(double param1, double param2, double param3, double param4, double param5, double param6, double param7, double param8) {
		double loc9 = (param8 - param6) * (param3 - param1) - (param7 - param5) * (param4 - param2);
		if (loc9 == 0) {
			return null;
		}
		double loc10 = ((param7 - param5) * (param2 - param6) - (param8 - param6) * (param1 - param5)) / loc9;
		double loc11 = ((param3 - param1) * (param2 - param6) - (param4 - param2) * (param1 - param5)) / loc9;
		if (loc10 > 1 || loc10 < 0 || loc11 > 1 || loc11 < 0) {
			return null;
		}
		Point loc12 = new Point(param1 + loc10 * (param3 - param1), param2 + loc10 * (param4 - param2));
		return loc12;
	}

	public static double pointDistance(double param1, double param2, double param3, double param4, double param5, double param6) {
		double loc10 = 0;
		double loc11 = 0;
		double loc12 = 0;
		double loc7 = param5 - param3;
		double loc8 = param6 - param4;
		double loc9 = loc7 * loc7 + loc8 * loc8;
		if (loc9 < 0.001) {
			loc10 = param3;
			loc11 = param4;
		} else {
			loc12 = ((param1 - param3) * loc7 + (param2 - param4) * loc8) / loc9;
			if (loc12 < 0) {
				loc10 = param3;
				loc11 = param4;
			} else if (loc12 > 1) {
				loc10 = param5;
				loc11 = param6;
			} else {
				loc10 = param3 + loc12 * loc7;
				loc11 = param4 + loc12 * loc8;
			}
		}
		loc7 = param1 - loc10;
		loc8 = param2 - loc11;
		return Math.sqrt(loc7 * loc7 + loc8 * loc8);
	}


}

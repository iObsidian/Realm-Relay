package rotmg.util;

import flash.geom.Point;
import flash.geom.Rectangle;

public class RectangleUtil {


	public RectangleUtil() {
		super();
	}

	public static double pointDist(Rectangle param1, double param2, double param3) {
		double loc4 = param2;
		double loc5 = param3;
		if (loc4 < param1.x) {
			loc4 = param1.x;
		} else if (loc4 > param1.right) {
			loc4 = param1.right;
		}
		if (loc5 < param1.y) {
			loc5 = param1.y;
		} else if (loc5 > param1.bottom) {
			loc5 = param1.bottom;
		}
		if (loc4 == param2 && loc5 == param3) {
			return 0;
		}
		return PointUtil.distanceXY(loc4, loc5, param2, param3);
	}

	public static Point closestPoint(Rectangle param1, double param2, double param3) {
		double loc4 = param2;
		double loc5 = param3;
		if (loc4 < param1.x) {
			loc4 = param1.x;
		} else if (loc4 > param1.right) {
			loc4 = param1.right;
		}
		if (loc5 < param1.y) {
			loc5 = param1.y;
		} else if (loc5 > param1.bottom) {
			loc5 = param1.bottom;
		}
		return new Point(loc4, loc5);
	}

	public static boolean lineSegmentIntersectsXY(Rectangle param1, double param2, double param3, double param4, double param5) {
		double loc8 = 0;
		double loc9 = 0;
		double loc10 = 0;
		double loc11 = 0;
		if (param1.left > param2 && param1.left > param4 || param1.right < param2 && param1.right < param4 || param1.top > param3 && param1.top > param5 || param1.bottom < param3 && param1.bottom < param5) {
			return false;
		}
		if (param1.left < param2 && param2 < param1.right && param1.top < param3 && param3 < param1.bottom || param1.left < param4 && param4 < param1.right && param1.top < param5 && param5 < param1.bottom) {
			return true;
		}
		double loc6 = (param5 - param3) / (param4 - param2);
		double loc7 = param3 - loc6 * param2;
		if (loc6 > 0) {
			loc8 = loc6 * param1.left + loc7;
			loc9 = loc6 * param1.right + loc7;
		} else {
			loc8 = loc6 * param1.right + loc7;
			loc9 = loc6 * param1.left + loc7;
		}
		if (param3 < param5) {
			loc11 = param3;
			loc10 = param5;
		} else {
			loc11 = param5;
			loc10 = param3;
		}
		double loc12 = loc8 > loc11 ? loc8 : loc11;
		double loc13 = loc9 < loc10 ? loc9 : loc10;
		return loc12 < loc13 && !(loc13 < param1.top || loc12 > param1.bottom);
	}

	public static boolean lineSegmentIntersectXY(Rectangle param1, double param2, double param3, double param4, double param5, Point param6) {
		double loc7 = 0;
		double loc8 = 0;
		double loc9 = 0;
		double loc10 = 0;
		if (param4 <= param1.x) {
			loc7 = (param5 - param3) / (param4 - param2);
			loc8 = param3 - param2 * loc7;
			loc9 = loc7 * param1.x + loc8;
			if (loc9 >= param1.y && loc9 <= param1.y + param1.height) {
				param6.x = param1.x;
				param6.y = loc9;
				return true;
			}
		} else if (param4 >= param1.x + param1.width) {
			loc7 = (param5 - param3) / (param4 - param2);
			loc8 = param3 - param2 * loc7;
			loc9 = loc7 * (param1.x + param1.width) + loc8;
			if (loc9 >= param1.y && loc9 <= param1.y + param1.height) {
				param6.x = param1.x + param1.width;
				param6.y = loc9;
				return true;
			}
		}
		if (param5 <= param1.y) {
			loc7 = (param4 - param2) / (param5 - param3);
			loc8 = param2 - param3 * loc7;
			loc10 = loc7 * param1.y + loc8;
			if (loc10 >= param1.x && loc10 <= param1.x + param1.width) {
				param6.x = loc10;
				param6.y = param1.y;
				return true;
			}
		} else if (param5 >= param1.y + param1.height) {
			loc7 = (param4 - param2) / (param5 - param3);
			loc8 = param2 - param3 * loc7;
			loc10 = loc7 * (param1.y + param1.height) + loc8;
			if (loc10 >= param1.x && loc10 <= param1.x + param1.width) {
				param6.x = loc10;
				param6.y = param1.y + param1.height;
				return true;
			}
		}
		return false;
	}

	public static Point lineSegmentIntersect(Rectangle param1, IntPoint param2, IntPoint param3) {
		double loc4 = 0;
		double loc5 = 0;
		double loc6 = 0;
		double loc7 = 0;
		if (param3.x() <= param1.x) {
			loc4 = (param3.y() - param2.y()) / (param3.x() - param2.x());
			loc5 = param2.y() - param2.x() * loc4;
			loc6 = loc4 * param1.x + loc5;
			if (loc6 >= param1.y && loc6 <= param1.y + param1.height) {
				return new Point(param1.x, loc6);
			}
		} else if (param3.x() >= param1.x + param1.width) {
			loc4 = (param3.y() - param2.y()) / (param3.x() - param2.x());
			loc5 = param2.y() - param2.x() * loc4;
			loc6 = loc4 * (param1.x + param1.width) + loc5;
			if (loc6 >= param1.y && loc6 <= param1.y + param1.height) {
				return new Point(param1.x + param1.width, loc6);
			}
		}
		if (param3.y() <= param1.y) {
			loc4 = (param3.x() - param2.x()) / (param3.y() - param2.y());
			loc5 = param2.x() - param2.y() * loc4;
			loc7 = loc4 * param1.y + loc5;
			if (loc7 >= param1.x && loc7 <= param1.x + param1.width) {
				return new Point(loc7, param1.y);
			}
		} else if (param3.y() >= param1.y + param1.height) {
			loc4 = (param3.x() - param2.x()) / (param3.y() - param2.y());
			loc5 = param2.x() - param2.y() * loc4;
			loc7 = loc4 * (param1.y + param1.height) + loc5;
			if (loc7 >= param1.x && loc7 <= param1.x + param1.width) {
				return new Point(loc7, param1.y + param1.height);
			}
		}
		return null;
	}

	/*public static Extents2D getRotatedRectExtents2D(double param1, double param2, double param3, double param4, double param5) {
		Point loc9 = null;
		int loc11 = 0;
		Matrix loc6 = new Matrix();
		loc6.translate(-param4 / 2, -param5 / 2);
		loc6.rotate(param3);
		loc6.translate(param1, param2);
		Extents2D loc7 = new Extents2D();
		Point loc8 = new Point();
		int loc10 = 0;
		while (loc10 <= 1) {
			loc11 = 0;
			while (loc11 <= 1) {
				loc8.x = loc10 * param4;
				loc8.y = loc11 * param5;
				loc9 = loc6.transformPoint(loc8);
				loc7.add(loc9.x, loc9.y);
				loc11++;
			}
			loc10++;
		}
		return loc7;
	}*/


}

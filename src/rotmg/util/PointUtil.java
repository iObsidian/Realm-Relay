package rotmg.util;

import flash.geom.Point;

public class PointUtil {

	public static final Point ORIGIN = new Point(0, 0);


	public static Point roundPoint(Point param1) {
		Point loc2 = param1.clone();
		loc2.x = Math.round(loc2.x);
		loc2.y = Math.round(loc2.y);
		return loc2;
	}

	public static double distanceSquared(Point param1, Point param2) {
		return distanceSquaredXY(param1.x, param1.y, param2.x, param2.y);
	}

	public static double distanceSquaredXY(double param1, double param2, double param3, double param4) {
		double loc5 = param3 - param1;
		double loc6 = param4 - param2;
		return loc5 * loc5 + loc6 * loc6;
	}

	public static double distanceXY(double param1, double param2, double param3, double param4) {
		double loc5 = param3 - param1;
		double loc6 = param4 - param2;
		return Math.sqrt(loc5 * loc5 + loc6 * loc6);
	}

	public static Point lerpXY(double param1, double param2, double param3, double param4, double param5) {
		return new Point(param1 + (param3 - param1) * param5, param2 + (param4 - param2) * param5);
	}

	public static double angleTo(Point param1, Point param2) {
		return Math.atan2(param2.y - param1.y, param2.x - param1.x);
	}

	public static Point pointAt(Point param1, double param2, double param3) {
		Point loc4 = new Point();
		loc4.x = param1.x + param3 * Math.cos(param2);
		loc4.y = param1.y + param3 * Math.sin(param2);
		return loc4;
	}


}

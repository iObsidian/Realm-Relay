package rotmg.engine3d;

import flash.airglobal.Math;
import flash.geom.Point;
import flash.geom.Rectangle;
import rotmg.util.LineSegmentUtil;
import rotmg.util.PointUtil;

public class Triangle {

	public double x0;

	public double y0;

	public double x1;

	public double y1;

	public double x2;

	public double y2;

	public double vx1;

	public double vy1;

	public double vx2;

	public double vy2;

	public Triangle(double param1, double param2, double param3, double param4, double param5, double param6) {
		super();
		this.x0 = param1;
		this.y0 = param2;
		this.x1 = param3;
		this.y1 = param4;
		this.x2 = param5;
		this.y2 = param6;
		this.vx1 = this.x1 - this.x0;
		this.vy1 = this.y1 - this.y0;
		this.vx2 = this.x2 - this.x0;
		this.vy2 = this.y2 - this.y0;
	}

	public static boolean containsXY(double param1, double param2, double param3, double param4, double param5, double param6, double param7, double param8) {
		double loc9 = param3 - param1;
		double loc10 = param4 - param2;
		double loc11 = param5 - param1;
		double loc12 = param6 - param2;
		double loc13 = (param7 * loc12 - param8 * loc11 - (param1 * loc12 - param2 * loc11)) / (loc9 * loc12 - loc10 * loc11);
		double loc14 = -(param7 * loc10 - param8 * loc9 - (param1 * loc10 - param2 * loc9)) / (loc9 * loc12 - loc10 * loc11);
		return loc13 >= 0 && loc14 >= 0 && loc13 + loc14 <= 1;
	}

	public static boolean intersectTriAABB(double param1, double param2, double param3, double param4, double param5, double param6, double param7, double param8, double param9, double param10) {
		if (param7 > param1 && param7 > param3 && param7 > param5 || param9 < param1 && param9 < param3 && param9 < param5 || param8 > param2 && param8 > param4 && param8 > param6 || param10 < param2 && param10 < param4 && param10 < param6) {
			return false;
		}
		if (param7 < param1 && param1 < param9 && param8 < param2 && param2 < param10 || param7 < param3 && param3 < param9 && param8 < param4 && param4 < param10 || param7 < param5 && param5 < param9 && param8 < param6 && param6 < param10) {
			return true;
		}
		return lineRectIntersect(param1, param2, param3, param4, param7, param8, param9, param10) || lineRectIntersect(param3, param4, param5, param6, param7, param8, param9, param10) || lineRectIntersect(param5, param6, param1, param2, param7, param8, param9, param10);
	}

	private static boolean lineRectIntersect(double param1, double param2, double param3, double param4, double param5, double param6, double param7, double param8) {
		double loc11 = 0;
		double loc12 = 0;
		double loc13 = 0;
		double loc14 = 0;
		double loc9 = (param4 - param2) / (param3 - param1);
		double loc10 = param2 - loc9 * param1;
		if (loc9 > 0) {
			loc11 = loc9 * param5 + loc10;
			loc12 = loc9 * param7 + loc10;
		} else {
			loc11 = loc9 * param7 + loc10;
			loc12 = loc9 * param5 + loc10;
		}
		if (param2 < param4) {
			loc13 = param2;
			loc14 = param4;
		} else {
			loc13 = param4;
			loc14 = param2;
		}
		double loc15 = loc11 > loc13 ? loc11 : loc13;
		double loc16 = loc12 < loc14 ? loc12 : loc14;
		return loc15 < loc16 && !(loc16 < param6 || loc15 > param8);
	}

	public Rectangle aabb() {

		double loc1 = Math.min(this.x0, this.x1, this.x2);
		double loc2 = Math.max(this.x0, this.x1, this.x2);
		double loc3 = Math.min(this.y0, this.y1, this.y2);
		double loc4 = Math.max(this.y0, this.y1, this.y2);
		return new Rectangle(loc1, loc3, loc2 - loc1, loc4 - loc3);
	}

	public double area() {
		return Math.abs((this.x0 * (this.y1 - this.y2) + this.x1 * (this.y2 - this.y0) + this.x2 * (this.y0 - this.y1)) / 2);
	}

	public void incenter(Point param1) {
		double loc2 = PointUtil.distanceXY(this.x1, this.y1, this.x2, this.y2);
		double loc3 = PointUtil.distanceXY(this.x0, this.y0, this.x2, this.y2);
		double loc4 = PointUtil.distanceXY(this.x0, this.y0, this.x1, this.y1);
		param1.x = (loc2 * this.x0 + loc3 * this.x1 + loc4 * this.x2) / (loc2 + loc3 + loc4);
		param1.y = (loc2 * this.y0 + loc3 * this.y1 + loc4 * this.y2) / (loc2 + loc3 + loc4);
	}

	public boolean contains(double param1, double param2) {
		double loc3 = (param1 * this.vy2 - param2 * this.vx2 - (this.x0 * this.vy2 - this.y0 * this.vx2)) / (this.vx1 * this.vy2 - this.vy1 * this.vx2);
		double loc4 = -(param1 * this.vy1 - param2 * this.vx1 - (this.x0 * this.vy1 - this.y0 * this.vx1)) / (this.vx1 * this.vy2 - this.vy1 * this.vx2);
		return loc3 >= 0 && loc4 >= 0 && loc3 + loc4 <= 1;
	}

	public double distance(double param1, double param2) {
		if (this.contains(param1, param2)) {
			return 0;
		}
		return Math.min(LineSegmentUtil.pointDistance(param1, param2, this.x0, this.y0, this.x1, this.y1), LineSegmentUtil.pointDistance(param1, param2, this.x1, this.y1, this.x2, this.y2), LineSegmentUtil.pointDistance(param1, param2, this.x0, this.y0, this.x2, this.y2));
	}

	public boolean intersectAABB(double param1, double param2, double param3, double param4) {
		return intersectTriAABB(this.x0, this.y0, this.x1, this.y1, this.x2, this.y2, param1, param2, param3, param4);
	}


}

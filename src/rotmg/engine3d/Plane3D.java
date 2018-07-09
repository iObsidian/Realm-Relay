package rotmg.engine3d;

import alde.flash.utils.Vector;
import flash.geom.Vector3D;

public class Plane3D {


	public static final int NONE = 0;

	public static final int POSITIVE = 1;

	public static final int NEGATIVE = 2;

	public static final int EQUAL = 3;


	public Vector3D normal;

	public double d;

	public Plane3D() {
		this(null, null, null);
	}

	public Plane3D(Vector3D param1, Vector3D param2, Vector3D param3) {
		super();
		if (param1 != null && param2 != null && param3 != null) {
			this.normal = new Vector3D();
			computeNormal(param1, param2, param3, this.normal);
			this.d = -this.normal.dotProduct(param1);
		}
	}

	public static void computeNormal(Vector3D param1, Vector3D param2, Vector3D param3, Vector3D param4) {
		double loc5 = param2.x - param1.x;
		double loc6 = param2.y - param1.y;
		double loc7 = param2.z - param1.z;
		double loc8 = param3.x - param1.x;
		double loc9 = param3.y - param1.y;
		double loc10 = param3.z - param1.z;
		param4.x = loc6 * loc10 - loc7 * loc9;
		param4.y = loc7 * loc8 - loc5 * loc10;
		param4.z = loc5 * loc9 - loc6 * loc8;
		param4.normalize();
	}

	public static void computeNormalVec(Vector<Double> param1, Vector3D param2) {
		double loc3 = param1.get(3) - param1.get(0);
		double loc4 = param1.get(4) - param1.get(1);
		double loc5 = param1.get(5) - param1.get(2);
		double loc6 = param1.get(6) - param1.get(0);
		double loc7 = param1.get(7) - param1.get(1);
		double loc8 = param1.get(8) - param1.get(2);
		param2.x = loc4 * loc8 - loc5 * loc7;
		param2.y = loc5 * loc6 - loc3 * loc8;
		param2.z = loc3 * loc7 - loc4 * loc6;
		param2.normalize();
	}

	public int testPoint(Vector3D param1) {
		double loc2 = this.normal.dotProduct(param1) + this.d;
		if (loc2 > 0.001) {
			return POSITIVE;
		}
		if (loc2 < -0.001) {
			return NEGATIVE;
		}
		return EQUAL;
	}

	public double lineIntersect(Line3D param1) {
		double loc2 = -this.d - this.normal.x * param1.v0.x - this.normal.y * param1.v0.y - this.normal.z * param1.v0.z;
		double loc3 = this.normal.x * (param1.v1.x - param1.v0.x) + this.normal.y * (param1.v1.y - param1.v0.y) + this.normal.z * (param1.v1.z - param1.v0.z);
		if (loc3 == 0) {
			return 0;
		}
		return loc2 / loc3;
	}

	public double zAtXY(double param1, double param2) {
		return -(this.d + this.normal.x * param1 + this.normal.y * param2) / this.normal.z;
	}

	public String toString() {
		return "Plane(n = " + this.normal + ", d = " + this.d + ")";
	}


}

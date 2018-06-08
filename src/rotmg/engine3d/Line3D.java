package rotmg.engine3d;


import flash.geom.Vector3D;

public class Line3D {


	public Vector3D v0;

	public Vector3D v1;

	public Line3D(Vector3D param1, Vector3D param2) {
		super();
		this.v0 = param1;
		this.v1 = param2;
	}

	public static boolean unitTest() {
		return UnitTest.run();
	}

	public int crossZ(Line3D param1) {
		double loc2 = (param1.v1.y - param1.v0.y) * (this.v1.x - this.v0.x) - (param1.v1.x - param1.v0.x) * (this.v1.y - this.v0.y);
		if (loc2 < 0.001 && loc2 > -0.001) {
			return Order.NEITHER;
		}
		double loc3 = (param1.v1.x - param1.v0.x) * (this.v0.y - param1.v0.y) - (param1.v1.y - param1.v0.y) * (this.v0.x - param1.v0.x);
		double loc4 = (this.v1.x - this.v0.x) * (this.v0.y - param1.v0.y) - (this.v1.y - this.v0.y) * (this.v0.x - param1.v0.x);
		if (loc3 < 0.001 && loc3 > -0.001 && loc4 < 0.001 && loc4 > -0.001) {
			return Order.NEITHER;
		}
		double loc5 = loc3 / loc2;
		double loc6 = loc4 / loc2;
		if (loc5 > 1 || loc5 < 0 || loc6 > 1 || loc6 < 0) {
			return Order.NEITHER;
		}
		double loc7 = this.v0.z + loc5 * (this.v1.z - this.v0.z) - (param1.v0.z + loc6 * (param1.v1.z - param1.v0.z));
		if (loc7 < 0.001 && loc7 > -0.001) {
			return Order.NEITHER;
		}
		if (loc7 > 0) {
			return Order.IN_FRONT;
		}
		return Order.BEHIND;
	}

	public Vector3D lerp(double param1) {
		return new Vector3D(this.v0.x + (this.v1.x - this.v0.x) * param1, this.v0.y + (this.v1.y - this.v0.y) * param1, this.v0.z + (this.v1.z - this.v0.z) * param1);
	}

	public String toString() {
		return "(" + this.v0 + ", " + this.v1 + ")";
	}
}

class UnitTest {


	UnitTest() {
		super();
	}

	private static boolean testCrossZ() {
		Line3D loc1 = new Line3D(new Vector3D(0, 0, 0), new Vector3D(0, 100, 0));
		Line3D loc2 = new Line3D(new Vector3D(10, 0, 10), new Vector3D(-10, 100, -100));
		if (loc1.crossZ(loc2) != Order.IN_FRONT) {
			return false;
		}
		if (loc2.crossZ(loc1) != Order.BEHIND) {
			return false;
		}
		loc1 = new Line3D(new Vector3D(1, 1, 200), new Vector3D(6, 6, 200));
		loc2 = new Line3D(new Vector3D(3, 1, -100), new Vector3D(1, 3, -100));
		if (loc1.crossZ(loc2) != Order.IN_FRONT) {
			return false;
		}
		if (loc2.crossZ(loc1) != Order.BEHIND) {
			return false;
		}
		return true;
	}

	public static boolean run() {
		if (!testCrossZ()) {
			return false;
		}
		return true;
	}
}

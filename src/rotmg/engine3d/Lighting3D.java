package rotmg.engine3d;

import flash.geom.Vector3D;

public class Lighting3D {

	public static final Vector3D LIGHT_VECTOR = createLightVector();

	public Lighting3D() {
		super();
	}

	public static double shadeValue(Vector3D param1, double param2) {
		double loc3 = Math.max(0, param1.dotProduct(Lighting3D.LIGHT_VECTOR));
		return param2 + (1 - param2) * loc3;
	}

	private static Vector3D createLightVector() {
		Vector3D loc1 = new Vector3D(1, 3, 2);
		loc1.normalize();
		return loc1;
	}


}

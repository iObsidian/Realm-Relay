package flash.geom;

public class Vector3D {

	static public final Vector3D X_AXIS = null;
	static public final Vector3D Y_AXIS = null;
	static public final Vector3D Z_AXIS = null;

	public double x;
	public double y;
	public double z;

	public int length;
	public double w;

	public Vector3D() {

	}

	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double dotProduct(Vector3D r) {
		return 0;
	}

	public void normalize() {
	}
}

package rotmg.net;

/**
 * This is almost a complete match, except that Number was changed to double instead of double
 */
public class LatLong {

	private static final double TO_DEGREES = 180 / Math.PI;

	private static final double TO_RADIANS = Math.PI / 180;

	private static final double DISTANCE_SCALAR = 60 * 1.1515 * 1.609344 * 1000;
	public double latitude;
	public double longitude;

	public LatLong(double param1, double param2) {
		super();
		this.latitude = param1;
		this.longitude = param2;
	}

	public static double distance(LatLong param1, LatLong param2) {
		double theta = TO_RADIANS * (param1.longitude - param2.longitude);
		double lat1 = TO_RADIANS * param1.latitude;
		double lat2 = TO_RADIANS * param2.latitude;
		double dist = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(theta);
		dist = TO_DEGREES * Math.acos(dist) * DISTANCE_SCALAR;
		return dist;
	}

	public String toString() {
		return "(" + this.latitude + ", " + this.longitude + ")";
	}


}

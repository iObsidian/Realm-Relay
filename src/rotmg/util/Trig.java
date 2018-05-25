package rotmg.util;

public class Trig {

	public static final double toDegrees = 180 / Math.PI;

	public static final double toRadians = Math.PI / 180;

	public static double slerp(double param1, double param2, double param3) {
		double loc4 = Double.MAX_VALUE;
		if (param1 > param2) {
			if (param1 - param2 > Math.PI) {
				loc4 = param1 * (1 - param3) + (param2 + 2 * Math.PI) * param3;
			} else {
				loc4 = param1 * (1 - param3) + param2 * param3;
			}
		} else if (param2 - param1 > Math.PI) {
			loc4 = (param1 + 2 * Math.PI) * (1 - param3) + param2 * param3;
		} else {
			loc4 = param1 * (1 - param3) + param2 * param3;
		}
		if (loc4 < -Math.PI || loc4 > Math.PI) {
			loc4 = boundToPI(loc4);
		}
		return loc4;
	}

	public static double angleDiff(double param1, double param2) {
		if (param1 > param2) {
			if (param1 - param2 > Math.PI) {
				return param2 + 2 * Math.PI - param1;
			}
			return param1 - param2;
		}
		if (param2 - param1 > Math.PI) {
			return param1 + 2 * Math.PI - param2;
		}
		return param2 - param1;
	}

	public static double sin(double param1) {
		double loc2 = 0;
		if (param1 < -Math.PI || param1 > Math.PI) {
			param1 = boundToPI(param1);
		}
		if (param1 < 0) {
			loc2 = 1.27323954 * param1 + 0.405284735 * param1 * param1;
			if (loc2 < 0) {
				loc2 = 0.225 * (loc2 * -loc2 - loc2) + loc2;
			} else {
				loc2 = 0.225 * (loc2 * loc2 - loc2) + loc2;
			}
		} else {
			loc2 = 1.27323954 * param1 - 0.405284735 * param1 * param1;
			if (loc2 < 0) {
				loc2 = 0.225 * (loc2 * -loc2 - loc2) + loc2;
			} else {
				loc2 = 0.225 * (loc2 * loc2 - loc2) + loc2;
			}
		}
		return loc2;
	}

	public static double cos(double param1) {
		return sin(param1 + Math.PI / 2);
	}

	public static double atan2(double param1, double param2) {
		double loc3 = 0;
		if (param2 == 0) {
			if (param1 < 0) {
				return -Math.PI / 2;
			}
			if (param1 > 0) {
				return Math.PI / 2;
			}
			return 0;
		}
		if (param1 == 0) {
			if (param2 < 0) {
				return Math.PI;
			}
			return 0;
		}
		if ((param2 > 0 ? param2 : -param2) > (param1 > 0 ? param1 : -param1)) {
			loc3 = (param2 < 0 ? -Math.PI : 0) + atan2Helper(param1, param2);
		} else {
			loc3 = (param1 > 0 ? Math.PI / 2 : -Math.PI / 2) - atan2Helper(param2, param1);
		}
		if (loc3 < -Math.PI || loc3 > Math.PI) {
			loc3 = boundToPI(loc3);
		}
		return loc3;
	}

	public static double atan2Helper(double param1, double param2) {
		double loc3 = param1 / param2;
		double loc4 = loc3;
		double loc5 = loc3;
		double loc6 = 1;
		int loc7 = 1;
		do {
			loc6 = loc6 + 2;
			loc7 = loc7 > 0 ? -1 : 1;
			loc5 = loc5 * loc3 * loc3;
			loc4 = loc4 + loc7 * loc5 / loc6;
		}
		while ((loc5 > 0.01 || loc5 < -0.01) && loc6 <= 11);

		return loc4;
	}

	public static double boundToPI(double param1) {
		int loc2 = 0;
		if (param1 < -Math.PI) {
			loc2 = ((int) (param1 / -Math.PI) + 1) / 2;
			param1 = param1 + loc2 * 2 * Math.PI;
		} else if (param1 > Math.PI) {
			loc2 = ((int) (param1 / Math.PI) + 1) / 2;
			param1 = param1 - loc2 * 2 * Math.PI;
		}
		return param1;
	}

	public static double boundTo180(double param1) {
		int loc2 = 0;
		if (param1 < -180) {
			loc2 = (int) ((param1 / -180) + 1 / 2);
			param1 = param1 + loc2 * 360;
		} else if (param1 > 180) {
			loc2 = (int) ((param1 / 180) + 1 / 2);
			param1 = param1 - loc2 * 360;
		}
		return param1;
	}

}

package rotmg.util;

import flash.utils.Date;

public class TimeUtil {


	public static final int DAY_IN_MS = 86400000;

	public static final int DAY_IN_S = 86400;

	public static final int HOUR_IN_S = 3600;

	public static final int MIN_IN_S = 60;


	public TimeUtil() {
		super();
	}

	public static double secondsToDays(double param1) {
		return param1 / DAY_IN_S;
	}

	public static double secondsToHours(double param1) {
		return param1 / HOUR_IN_S;
	}

	public static double secondsToMins(double param1) {
		return param1 / MIN_IN_S;
	}

	public static Date parseUTCDate(String param1) {
		return new Date();
	}


}

package rotmg.util;

import java.text.SimpleDateFormat;

public class MoreDateUtil {

	public static String getDayStringInPT() {
		return new SimpleDateFormat("MM dd, yyyy").toLocalizedPattern();
	}

}

package flash.utils.timer;

public class getTimer {

	private static long startTime = System.currentTimeMillis();

	public static int getTimer() {
		return (int) (System.currentTimeMillis() - startTime);
	}

}

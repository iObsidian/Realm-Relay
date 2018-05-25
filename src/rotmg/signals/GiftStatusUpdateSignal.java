package rotmg.signals;

public class GiftStatusUpdateSignal extends Signal<Boolean> {

	public static GiftStatusUpdateSignal instance;

	public static GiftStatusUpdateSignal getInstance() {
		if (instance == null) {
			instance = new GiftStatusUpdateSignal();
		}
		return instance;
	}

	public static boolean HAS_GIFT = true;

	public static boolean HAS_NO_GIFT = false;

}

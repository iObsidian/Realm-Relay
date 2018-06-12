package rotmg.signals;

import org.osflash.signals.Signal;

public class GiftStatusUpdateSignal extends Signal<Boolean> {

	public static GiftStatusUpdateSignal instance;
	public static boolean HAS_GIFT = true;
	public static boolean HAS_NO_GIFT = false;

	public static GiftStatusUpdateSignal getInstance() {
		if (instance == null) {
			instance = new GiftStatusUpdateSignal();
		}
		return instance;
	}

}

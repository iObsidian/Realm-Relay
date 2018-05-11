package realmrelay.game.signals;

import realmrelay.game.Signal;

public class GiftStatusUpdateSignal extends Signal<Boolean> {

	public static boolean HAS_GIFT = true;

	public static boolean HAS_NO_GIFT = false;

	public GiftStatusUpdateSignal instance;

	public static GiftStatusUpdateSignal getInstance() {
		if (instance == null) {
			instance = new GiftStatusUpdateSignal();
		}
		return instance;
	}
}

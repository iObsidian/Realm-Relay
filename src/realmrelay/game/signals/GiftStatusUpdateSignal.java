package realmrelay.game.signals;

import realmrelay.game._as3.Signal;

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

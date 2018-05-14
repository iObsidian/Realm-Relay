package realmrelay.game.ui.signals;

import realmrelay.game.signals.AddTextLineSignal;

public class GiftStatusUpdateSignal extends Signal {

	private static GiftStatusUpdateSignal instance;

	public static GiftStatusUpdateSignal getInstance() {
		if (instance == null) {
			instance = new GiftStatusUpdateSignal();
		}
		return instance;
	}

}

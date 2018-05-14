package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class BuyCharacterPendingSignal extends Signal {

	private static BuyCharacterPendingSignal instance;

	public static BuyCharacterPendingSignal getInstance() {
		if (instance == null) {
			instance = new BuyCharacterPendingSignal();
		}
		return instance;
	}

}

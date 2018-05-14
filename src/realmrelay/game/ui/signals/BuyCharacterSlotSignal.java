package realmrelay.game.ui.signals;

import realmrelay.game._as3.Signal;

public class BuyCharacterSlotSignal extends Signal<Integer> {

	private static BuyCharacterSlotSignal instance;

	public static BuyCharacterSlotSignal getInstance() {
		if (instance == null) {
			instance = new BuyCharacterSlotSignal();
		}
		return instance;
	}

}

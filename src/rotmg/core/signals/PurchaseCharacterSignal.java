package rotmg.core.signals;

import rotmg.game._as3.Signal;

public class PurchaseCharacterSignal extends Signal<Integer> {

	private static PurchaseCharacterSignal instance;

	public static PurchaseCharacterSignal getInstance() {
		if (instance == null) {
			instance = new PurchaseCharacterSignal();
		}
		return instance;
	}

}

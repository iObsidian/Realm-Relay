package rotmg.core.signals;

import org.osflash.signals.Signal;

public class BuyCharacterPendingSignal extends Signal {

	private static BuyCharacterPendingSignal instance;

	public static BuyCharacterPendingSignal getInstance() {
		if (instance == null) {
			instance = new BuyCharacterPendingSignal();
		}
		return instance;
	}

}

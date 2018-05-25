package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;

public class UpdatePotionInventorySignal extends Signal {

	private static UpdatePotionInventorySignal instance;

	public static UpdatePotionInventorySignal getInstance() {
		if (instance == null) {
			instance = new UpdatePotionInventorySignal();
		}
		return instance;
	}

}

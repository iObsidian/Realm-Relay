package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.ui.model.Key;

public class HideKeySignal extends Signal<Key> {

	private static HideKeySignal instance;

	public static HideKeySignal getInstance() {
		if (instance == null) {
			instance = new HideKeySignal();
		}
		return instance;
	}

}

package rotmg.ui.signals;

import org.osflash.signals.Signal;

public class ShowKeySignal extends Signal<Key> {

	private static ShowKeySignal instance;

	public static ShowKeySignal getInstance() {
		if (instance == null) {
			instance = new ShowKeySignal();
		}
		return instance;
	}

}

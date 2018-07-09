package rotmg.core.signals;

import org.osflash.signals.Signal;

public class GotoPreviousScreenSignal extends Signal {

	private static GotoPreviousScreenSignal instance;

	public static GotoPreviousScreenSignal getInstance() {
		if (instance == null) {
			instance = new GotoPreviousScreenSignal();
		}
		return instance;
	}

}

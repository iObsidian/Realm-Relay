package rotmg.ui.signals;

import org.osflash.signals.Signal;

public class EnterGameSignal extends Signal {

	private static EnterGameSignal instance;

	public static EnterGameSignal getInstance() {
		if (instance == null) {
			instance = new EnterGameSignal();
		}
		return instance;
	}

}

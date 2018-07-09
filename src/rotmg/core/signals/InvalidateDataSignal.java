package rotmg.core.signals;

import org.osflash.signals.Signal;

public class InvalidateDataSignal extends Signal {

	private static InvalidateDataSignal instance;

	public static InvalidateDataSignal getInstance() {
		if (instance == null) {
			instance = new InvalidateDataSignal();
		}
		return instance;
	}

}

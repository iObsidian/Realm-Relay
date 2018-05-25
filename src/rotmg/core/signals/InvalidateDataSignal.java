package rotmg.core.signals;

import rotmg.game._as3.Signal;

public class InvalidateDataSignal extends Signal {

	private static InvalidateDataSignal instance;

	public static InvalidateDataSignal getInstance() {
		if (instance == null) {
			instance = new InvalidateDataSignal();
		}
		return instance;
	}

}

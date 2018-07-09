package rotmg.core.signals;

import org.osflash.signals.Signal;

public class SetupAnalyticsSignal extends Signal {

	private static SetupAnalyticsSignal instance;

	public static SetupAnalyticsSignal getInstance() {
		if (instance == null) {
			instance = new SetupAnalyticsSignal();
		}
		return instance;
	}

}

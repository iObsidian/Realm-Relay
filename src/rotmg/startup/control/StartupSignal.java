package rotmg.startup.control;

import org.osflash.signals.Signal;

public class StartupSignal extends Signal {

	public static StartupSignal instance;

	public static StartupSignal getInstance() {
		if (instance == null) {
			instance = new StartupSignal();
		}

		return instance;
	}

}

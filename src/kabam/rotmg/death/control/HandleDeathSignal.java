package kabam.rotmg.death.control;

import kabam.rotmg.messaging.incoming.Death;
import org.osflash.signals.Signal;

public class HandleDeathSignal extends Signal<Death> {

	static HandleDeathSignal instance;

	public static HandleDeathSignal getInstance() {
		if (instance == null) {
			instance = new HandleDeathSignal();
		}
		return instance;
	}

}

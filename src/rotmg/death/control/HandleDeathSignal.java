package rotmg.death.control;

import org.osflash.signals.Signal;
import rotmg.messaging.incoming.Death;

public class HandleDeathSignal extends Signal<Death> {

	static HandleDeathSignal instance;

	public static HandleDeathSignal getInstance() {
		if (instance == null) {
			instance = new HandleDeathSignal();
		}
		return instance;
	}

}

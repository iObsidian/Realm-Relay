package kabam.rotmg.death.control;

import kabam.rotmg.messaging.incoming.Death;
import org.osflash.signals.Signal;

public class ZombifySignal extends Signal<Death> {

	static ZombifySignal instance;

	public static ZombifySignal getInstance() {
		if (instance == null) {
			instance = new ZombifySignal();
		}
		return instance;
	}

}

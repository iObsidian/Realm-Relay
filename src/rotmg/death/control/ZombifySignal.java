package rotmg.death.control;

import org.osflash.signals.Signal;

import rotmg.messaging.incoming.Death;

public class ZombifySignal extends Signal<Death> {

	static ZombifySignal instance;

	public static ZombifySignal getInstance() {
		if (instance == null) {
			instance = new ZombifySignal();
		}
		return instance;
	}

}

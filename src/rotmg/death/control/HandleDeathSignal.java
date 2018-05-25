package rotmg.death.control;

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

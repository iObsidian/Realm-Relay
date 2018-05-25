package rotmg.death.control;

import rotmg.game._as3.Signal;
import rotmg.game.messaging.incoming.Death;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;
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

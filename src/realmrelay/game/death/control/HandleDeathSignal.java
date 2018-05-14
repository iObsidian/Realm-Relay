package realmrelay.game.death.control;

import realmrelay.game._as3.Signal;
import realmrelay.game.messaging.incoming.Death;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class HandleDeathSignal extends Signal<Death> {

	static HandleDeathSignal instance;

	public static HandleDeathSignal getInstance() {
		if (instance == null) {
			instance = new HandleDeathSignal();
		}
		return instance;
	}

}

package realmrelay.game.death.control;

import realmrelay.game.Signal;
import realmrelay.game.messaging.incoming.Death;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class ZombifySignal extends Signal<Death> {

	static ZombifySignal instance;

	public static ZombifySignal getInstance() {
		if (instance == null) {
			instance = new ZombifySignal();
		}
		return instance;
	}

}

package rotmg.death.control;

import rotmg.game._as3.Signal;
import rotmg.game.messaging.incoming.Death;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;
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

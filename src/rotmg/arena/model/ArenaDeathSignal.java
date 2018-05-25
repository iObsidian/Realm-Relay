package rotmg.arena.model;

import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class ArenaDeathSignal extends Signal {
	static ArenaDeathSignal instance;

	public static ArenaDeathSignal getInstance() {
		if (instance == null) {
			instance = new ArenaDeathSignal();
		}
		return instance;
	}
}

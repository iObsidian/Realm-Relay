package realmrelay.game.arena.model;

import realmrelay.game._as3.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class ArenaDeathSignal extends Signal {
	static ArenaDeathSignal instance;

	public static ArenaDeathSignal getInstance() {
		if (instance == null) {
			instance = new ArenaDeathSignal();
		}
		return instance;
	}
}

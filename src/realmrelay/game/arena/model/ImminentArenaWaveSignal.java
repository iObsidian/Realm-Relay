package realmrelay.game.arena.model;

import realmrelay.game._as3.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class ImminentArenaWaveSignal extends Signal {

	static ImminentArenaWaveSignal instance;

	public static ImminentArenaWaveSignal getInstance() {
		if (instance == null) {
			instance = new ImminentArenaWaveSignal();
		}
		return instance;
	}

}

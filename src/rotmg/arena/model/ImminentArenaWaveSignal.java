package rotmg.arena.model;

import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class ImminentArenaWaveSignal extends Signal {

	static ImminentArenaWaveSignal instance;

	public static ImminentArenaWaveSignal getInstance() {
		if (instance == null) {
			instance = new ImminentArenaWaveSignal();
		}
		return instance;
	}

}

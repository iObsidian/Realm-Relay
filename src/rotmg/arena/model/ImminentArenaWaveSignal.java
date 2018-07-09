package rotmg.arena.model;

import org.osflash.signals.Signal;

public class ImminentArenaWaveSignal extends Signal {

	static ImminentArenaWaveSignal instance;

	public static ImminentArenaWaveSignal getInstance() {
		if (instance == null) {
			instance = new ImminentArenaWaveSignal();
		}
		return instance;
	}

}

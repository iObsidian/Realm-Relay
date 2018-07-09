package rotmg.arena.model;

import org.osflash.signals.Signal;

public class ArenaDeathSignal extends Signal {
	static ArenaDeathSignal instance;

	public static ArenaDeathSignal getInstance() {
		if (instance == null) {
			instance = new ArenaDeathSignal();
		}
		return instance;
	}
}

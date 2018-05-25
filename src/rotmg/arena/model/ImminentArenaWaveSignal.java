package rotmg.arena.model;

public class ImminentArenaWaveSignal extends Signal {

	static ImminentArenaWaveSignal instance;

	public static ImminentArenaWaveSignal getInstance() {
		if (instance == null) {
			instance = new ImminentArenaWaveSignal();
		}
		return instance;
	}

}

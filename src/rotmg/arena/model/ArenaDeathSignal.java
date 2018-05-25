package rotmg.arena.model;

public class ArenaDeathSignal extends Signal {
	static ArenaDeathSignal instance;

	public static ArenaDeathSignal getInstance() {
		if (instance == null) {
			instance = new ArenaDeathSignal();
		}
		return instance;
	}
}

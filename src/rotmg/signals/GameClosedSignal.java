package rotmg.signals;

public class GameClosedSignal extends Signal {

	private static GameClosedSignal instance;

	public static GameClosedSignal getInstance() {
		if (instance == null) {
			instance = new GameClosedSignal();
		}
		return instance;
	}

}

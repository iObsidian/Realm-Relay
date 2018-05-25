package rotmg.core.signals;

public class LaunchGameSignal extends Signal {

	private static LaunchGameSignal instance;

	public static LaunchGameSignal getInstance() {
		if (instance == null) {
			instance = new LaunchGameSignal();
		}
		return instance;
	}

}

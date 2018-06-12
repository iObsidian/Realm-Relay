package rotmg.signals;

public class ExitGameSignal extends Signal {

	private static ExitGameSignal instance;

	public static ExitGameSignal getInstance() {
		if (instance == null) {
			instance = new ExitGameSignal();
		}
		return instance;
	}

}

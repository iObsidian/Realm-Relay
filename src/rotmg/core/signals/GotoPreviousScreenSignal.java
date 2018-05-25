package rotmg.core.signals;

public class GotoPreviousScreenSignal extends Signal {

	private static GotoPreviousScreenSignal instance;

	public static GotoPreviousScreenSignal getInstance() {
		if (instance == null) {
			instance = new GotoPreviousScreenSignal();
		}
		return instance;
	}

}

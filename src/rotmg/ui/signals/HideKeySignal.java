package rotmg.ui.signals;

public class HideKeySignal extends Signal<Key> {

	private static HideKeySignal instance;

	public static HideKeySignal getInstance() {
		if (instance == null) {
			instance = new HideKeySignal();
		}
		return instance;
	}

}

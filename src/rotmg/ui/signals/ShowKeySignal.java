package rotmg.ui.signals;

import rotmg.ui.model.Key;

public class ShowKeySignal extends Signal<Key> {

	private static ShowKeySignal instance;

	public static ShowKeySignal getInstance() {
		if (instance == null) {
			instance = new ShowKeySignal();
		}
		return instance;
	}

}

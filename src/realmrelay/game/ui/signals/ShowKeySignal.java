package realmrelay.game.ui.signals;

import realmrelay.game.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.ui.model.Key;

public class ShowKeySignal extends Signal<Key> {

	private static ShowKeySignal instance;

	public static ShowKeySignal getInstance() {
		if (instance == null) {
			instance = new ShowKeySignal();
		}
		return instance;
	}

}

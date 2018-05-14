package realmrelay.game.ui.signals;

import realmrelay.game._as3.Signal;
import realmrelay.game.ui.model.Key;

public class HideKeySignal extends Signal<Key> {

	private static HideKeySignal instance;

	public static HideKeySignal getInstance() {
		if (instance == null) {
			instance = new HideKeySignal();
		}
		return instance;
	}

}

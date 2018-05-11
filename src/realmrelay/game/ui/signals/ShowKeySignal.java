package realmrelay.game.ui.signals;

import realmrelay.game.Signal;
import realmrelay.game.ui.model.Key;

public class ShowKeySignal extends Signal<Key> {

	public static ShowKeySignal instance;

	public ShowKeySignal() {
		instance = this;
	}

}

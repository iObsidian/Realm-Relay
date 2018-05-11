package realmrelay.game.focus.control;

import realmrelay.game.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class SetGameFocusSignal extends Signal<String> {

	static SetGameFocusSignal instance;

	public static SetGameFocusSignal getInstance() {
		if (instance == null) {
			instance = new SetGameFocusSignal();
		}
		return instance;
	}

}

package rotmg.focus.control;

import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class SetGameFocusSignal extends Signal<String> {

	static SetGameFocusSignal instance;

	public static SetGameFocusSignal getInstance() {
		if (instance == null) {
			instance = new SetGameFocusSignal();
		}
		return instance;
	}

}

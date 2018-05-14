package realmrelay.game.ui.signals;

import realmrelay.game._as3.Signal;
import realmrelay.game.signals.AddTextLineSignal;

public class ShowKeyUISignal extends Signal {

	private static ShowKeyUISignal instance;

	public static ShowKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowKeyUISignal();
		}
		return instance;
	}

}

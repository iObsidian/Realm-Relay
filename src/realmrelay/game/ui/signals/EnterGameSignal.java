package realmrelay.game.ui.signals;

import realmrelay.game._as3.Signal;

public class EnterGameSignal extends Signal {

	private static EnterGameSignal instance;

	public static EnterGameSignal getInstance() {
		if (instance == null) {
			instance = new EnterGameSignal();
		}
		return instance;
	}

}

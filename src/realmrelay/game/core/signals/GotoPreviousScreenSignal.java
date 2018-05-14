package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class GotoPreviousScreenSignal extends Signal {

	private static GotoPreviousScreenSignal instance;

	public static GotoPreviousScreenSignal getInstance() {
		if (instance == null) {
			instance = new GotoPreviousScreenSignal();
		}
		return instance;
	}

}

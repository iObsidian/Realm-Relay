package realmrelay.game.signals;

import realmrelay.game._as3.Signal;

public class ExitGameSignal extends Signal {

	private static ExitGameSignal instance;

	public static ExitGameSignal getInstance() {
		if (instance == null) {
			instance = new ExitGameSignal();
		}
		return instance;
	}

}

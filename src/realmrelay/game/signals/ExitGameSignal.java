package realmrelay.game.signals;

import realmrelay.game.Signal;

public class ExitGameSignal extends Signal {

	private static ExitGameSignal instance;

	public static ExitGameSignal getInstance() {
		if (instance == null) {
			instance = new ExitGameSignal();
		}
		return instance;
	}

}

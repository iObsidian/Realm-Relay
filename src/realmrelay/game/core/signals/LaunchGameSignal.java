package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class LaunchGameSignal extends Signal {

	private static LaunchGameSignal instance;

	public static LaunchGameSignal getInstance() {
		if (instance == null) {
			instance = new LaunchGameSignal();
		}
		return instance;
	}

}

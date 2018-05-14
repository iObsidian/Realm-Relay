package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class TrackPageViewSignal extends Signal<String> {

	private static TrackPageViewSignal instance;

	public static TrackPageViewSignal getInstance() {
		if (instance == null) {
			instance = new TrackPageViewSignal();
		}
		return instance;
	}

}

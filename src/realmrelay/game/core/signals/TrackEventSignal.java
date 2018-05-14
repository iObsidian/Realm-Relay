package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class TrackEventSignal extends Signal<TrackingData> {

	private static TrackEventSignal instance;

	public static TrackEventSignal getInstance() {
		if (instance == null) {
			instance = new TrackEventSignal();
		}
		return instance;
	}

}

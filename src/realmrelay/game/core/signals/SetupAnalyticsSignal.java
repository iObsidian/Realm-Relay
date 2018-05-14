package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class SetupAnalyticsSignal extends Signal {

	private static SetupAnalyticsSignal instance;

	public static SetupAnalyticsSignal getInstance() {
		if (instance == null) {
			instance = new SetupAnalyticsSignal();
		}
		return instance;
	}

}

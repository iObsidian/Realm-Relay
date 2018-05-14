package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class MoneyFrameEnableCancelSignal extends Signal {

	private static MoneyFrameEnableCancelSignal instance;

	public static MoneyFrameEnableCancelSignal getInstance() {
		if (instance == null) {
			instance = new MoneyFrameEnableCancelSignal();
		}
		return instance;
	}

}

package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class SetScreenWithValidDataSignal extends Signal<Sprite> {

	private static SetScreenWithValidDataSignal instance;

	public static SetScreenWithValidDataSignal getInstance() {
		if (instance == null) {
			instance = new SetScreenWithValidDataSignal();
		}
		return instance;
	}

}

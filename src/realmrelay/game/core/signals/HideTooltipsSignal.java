package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class HideTooltipsSignal extends Signal {

	private static HideTooltipsSignal instance;

	public static HideTooltipsSignal getInstance() {
		if (instance == null) {
			instance = new HideTooltipsSignal();
		}
		return instance;
	}

}

package realmrelay.game.ui.signals;

import realmrelay.game._as3.Signal;

public class HUDModelInitialized extends Signal {

	private static HUDModelInitialized instance;

	public static HUDModelInitialized getInstance() {
		if (instance == null) {
			instance = new HUDModelInitialized();
		}
		return instance;
	}

}

package realmrelay.game.ui.signals;

import realmrelay.game.GameSprite;
import realmrelay.game._as3.Signal;

public class HUDSetupStarted extends Signal<GameSprite> {

	private static HUDSetupStarted instance;

	public static HUDSetupStarted getInstance() {
		if (instance == null) {
			instance = new HUDSetupStarted();
		}
		return instance;
	}

}

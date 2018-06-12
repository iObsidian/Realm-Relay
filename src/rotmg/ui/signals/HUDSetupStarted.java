package rotmg.ui.signals;

import rotmg.GameSprite;

public class HUDSetupStarted extends Signal<GameSprite> {

	private static HUDSetupStarted instance;

	public static HUDSetupStarted getInstance() {
		if (instance == null) {
			instance = new HUDSetupStarted();
		}
		return instance;
	}

}

package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game.GameSprite;
import rotmg.game._as3.Signal;

public class HUDSetupStarted extends Signal<GameSprite> {

	private static HUDSetupStarted instance;

	public static HUDSetupStarted getInstance() {
		if (instance == null) {
			instance = new HUDSetupStarted();
		}
		return instance;
	}

}

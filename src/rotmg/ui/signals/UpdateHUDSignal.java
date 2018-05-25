package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;
import rotmg.game.objects.Player;

public class UpdateHUDSignal extends Signal<Player> {

	private static UpdateHUDSignal instance;

	public static UpdateHUDSignal getInstance() {
		if (instance == null) {
			instance = new UpdateHUDSignal();
		}
		return instance;
	}

}

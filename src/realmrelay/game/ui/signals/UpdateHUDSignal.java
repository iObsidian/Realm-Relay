package realmrelay.game.ui.signals;

import realmrelay.game._as3.Signal;
import realmrelay.game.objects.Player;

public class UpdateHUDSignal extends Signal<Player> {

	private static UpdateHUDSignal instance;

	public static UpdateHUDSignal getInstance() {
		if (instance == null) {
			instance = new UpdateHUDSignal();
		}
		return instance;
	}

}

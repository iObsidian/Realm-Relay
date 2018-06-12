package rotmg.ui.signals;

import rotmg.objects.Player;

public class UpdateHUDSignal extends Signal<Player> {

	private static UpdateHUDSignal instance;

	public static UpdateHUDSignal getInstance() {
		if (instance == null) {
			instance = new UpdateHUDSignal();
		}
		return instance;
	}

}

package realmrelay.game.ui.signals;

import realmrelay.game.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class UpdateBackpackTabSignal extends Signal<Boolean> {


	static UpdateBackpackTabSignal instance;

	public static UpdateBackpackTabSignal getInstance() {
		if (instance == null) {
			instance = new UpdateBackpackTabSignal();
		}
		return instance;
	}
}

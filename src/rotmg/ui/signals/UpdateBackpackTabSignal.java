package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class UpdateBackpackTabSignal extends Signal<Boolean> {

	private static UpdateBackpackTabSignal instance;

	public static UpdateBackpackTabSignal getInstance() {
		if (instance == null) {
			instance = new UpdateBackpackTabSignal();
		}
		return instance;
	}
}

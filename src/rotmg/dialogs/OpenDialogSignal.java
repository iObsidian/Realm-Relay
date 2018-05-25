package rotmg.dialogs;

import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class OpenDialogSignal extends Signal {

	static OpenDialogSignal instance;

	public static OpenDialogSignal getInstance() {
		if (instance == null) {
			instance = new OpenDialogSignal();
		}
		return instance;
	}
}

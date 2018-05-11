package realmrelay.game.dialogs;

import realmrelay.game.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class CloseDialogsSignal extends Signal {
	static CloseDialogsSignal instance;

	public static CloseDialogsSignal getInstance() {
		if (instance == null) {
			instance = new CloseDialogsSignal();
		}
		return instance;
	}
}

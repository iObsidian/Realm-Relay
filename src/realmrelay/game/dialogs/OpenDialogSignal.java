package realmrelay.game.dialogs;

import realmrelay.game.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

//<Sprite> 

public class OpenDialogSignal extends Signal {

	static OpenDialogSignal instance;

	public static OpenDialogSignal getInstance() {
		if (instance == null) {
			instance = new OpenDialogSignal();
		}
		return instance;
	}
}

package rotmg.dialogs;

import org.osflash.signals.Signal;

import flash.display.Sprite;

public class PushDialogSignal extends Signal<Sprite> {

	static PushDialogSignal instance;

	public static PushDialogSignal getInstance() {
		if (instance == null) {
			instance = new PushDialogSignal();
		}
		return instance;
	}

}




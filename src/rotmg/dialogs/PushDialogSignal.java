package rotmg.dialogs;

import flash.display.Sprite;
import org.osflash.signals.Signal;

public class PushDialogSignal extends Signal<Sprite> {

	static PushDialogSignal instance;

	public static PushDialogSignal getInstance() {
		if (instance == null) {
			instance = new PushDialogSignal();
		}
		return instance;
	}

}




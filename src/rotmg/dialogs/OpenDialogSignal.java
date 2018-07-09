package rotmg.dialogs;

import org.osflash.signals.Signal;

public class OpenDialogSignal extends Signal {

	static OpenDialogSignal instance;

	public static OpenDialogSignal getInstance() {
		if (instance == null) {
			instance = new OpenDialogSignal();
		}
		return instance;
	}
}

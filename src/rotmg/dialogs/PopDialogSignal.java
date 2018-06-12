package rotmg.dialogs;

import org.osflash.signals.Signal;

public class PopDialogSignal extends Signal {

	static PopDialogSignal instance;

	public static PopDialogSignal getInstance() {
		if (instance == null) {
			instance = new PopDialogSignal();
		}
		return instance;
	}


}

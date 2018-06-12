package rotmg.dialogs;

import org.osflash.signals.Signal;

public class ShowDialogBackgroundSignal extends Signal {

	static ShowDialogBackgroundSignal instance;

	public static ShowDialogBackgroundSignal getInstance() {
		if (instance == null) {
			instance = new ShowDialogBackgroundSignal();
		}
		return instance;
	}

}


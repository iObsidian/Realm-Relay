package rotmg.ui.signals;

import org.osflash.signals.Signal;

public class ShowTitleUISignal extends Signal {

	private static ShowTitleUISignal instance;

	public static ShowTitleUISignal getInstance() {
		if (instance == null) {
			instance = new ShowTitleUISignal();
		}
		return instance;
	}

}

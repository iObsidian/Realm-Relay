package rotmg.dialogs;

import org.osflash.signals.Signal;

public class FlushPopupStartupQueueSignal extends Signal {

	static FlushPopupStartupQueueSignal instance;

	public static FlushPopupStartupQueueSignal getInstance() {
		if (instance == null) {
			instance = new FlushPopupStartupQueueSignal();
		}
		return instance;
	}


}


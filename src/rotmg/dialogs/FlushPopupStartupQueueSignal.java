package rotmg.dialogs;

import rotmg.game._as3.Signal;

public class FlushPopupStartupQueueSignal extends Signal {

	static FlushPopupStartupQueueSignal instance;

	public static FlushPopupStartupQueueSignal getInstance() {
		if (instance == null) {
			instance = new FlushPopupStartupQueueSignal();
		}
		return instance;
	}


}


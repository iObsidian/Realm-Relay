package rotmg.dialogs;

public class FlushPopupStartupQueueSignal extends Signal {

	static FlushPopupStartupQueueSignal instance;

	public static FlushPopupStartupQueueSignal getInstance() {
		if (instance == null) {
			instance = new FlushPopupStartupQueueSignal();
		}
		return instance;
	}


}


package rotmg.signals;

import org.osflash.signals.Signal;

public class DeferredQueueSignal<T> extends Signal<T> {

	private static DeferredQueueSignal instance;

	public static DeferredQueueSignal getInstance() {
		if (instance == null) {
			instance = new DeferredQueueSignal();
		}
		return instance;
	}

}

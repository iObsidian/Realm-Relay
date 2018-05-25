package rotmg.signals;

import rotmg.game._as3.Signal;

public class DeferredQueueSignal<T> extends Signal<T> {

	private static DeferredQueueSignal instance;

	public static DeferredQueueSignal getInstance() {
		if (instance == null) {
			instance = new DeferredQueueSignal();
		}
		return instance;
	}

}

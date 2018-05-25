package rotmg.signals;

public class DeferredQueueSignal<T> extends Signal<T> {

	private static DeferredQueueSignal instance;

	public static DeferredQueueSignal getInstance() {
		if (instance == null) {
			instance = new DeferredQueueSignal();
		}
		return instance;
	}

}

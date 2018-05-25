package rotmg.core.signals;

public class SetLoadingMessageSignal extends Signal<String> {

	private static SetLoadingMessageSignal instance;

	public static SetLoadingMessageSignal getInstance() {
		if (instance == null) {
			instance = new SetLoadingMessageSignal();
		}
		return instance;
	}

}

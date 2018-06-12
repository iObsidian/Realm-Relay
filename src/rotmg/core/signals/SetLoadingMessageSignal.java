package rotmg.core.signals;

import org.osflash.signals.Signal;

public class SetLoadingMessageSignal extends Signal<String> {

	private static SetLoadingMessageSignal instance;

	public static SetLoadingMessageSignal getInstance() {
		if (instance == null) {
			instance = new SetLoadingMessageSignal();
		}
		return instance;
	}

}

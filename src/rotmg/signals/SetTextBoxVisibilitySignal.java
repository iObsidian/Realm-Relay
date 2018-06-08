package rotmg.signals;

import org.osflash.signals.Signal;

public class SetTextBoxVisibilitySignal extends Signal<Boolean> {

	private static SetTextBoxVisibilitySignal instance;

	public static SetTextBoxVisibilitySignal getInstance() {
		if (instance == null) {
			instance = new SetTextBoxVisibilitySignal();
		}
		return instance;
	}

}

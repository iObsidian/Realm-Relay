package rotmg.signals;

import rotmg.game._as3.Signal;

public class SetTextBoxVisibilitySignal extends Signal<Boolean> {

	private static SetTextBoxVisibilitySignal instance;

	public static SetTextBoxVisibilitySignal getInstance() {
		if (instance == null) {
			instance = new SetTextBoxVisibilitySignal();
		}
		return instance;
	}

}

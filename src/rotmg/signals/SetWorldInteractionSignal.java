package rotmg.signals;

import rotmg.game._as3.Signal;

public class SetWorldInteractionSignal extends Signal<Boolean> {

	private static SetWorldInteractionSignal instance;

	public static SetWorldInteractionSignal getInstance() {
		if (instance == null) {
			instance = new SetWorldInteractionSignal();
		}
		return instance;
	}

}

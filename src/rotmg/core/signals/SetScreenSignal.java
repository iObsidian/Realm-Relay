package rotmg.core.signals;

import rotmg.game._as3.Signal;

public class SetScreenSignal extends Signal<Sprite> {

	private static SetScreenSignal instance;

	public static SetScreenSignal getInstance() {
		if (instance == null) {
			instance = new SetScreenSignal();
		}
		return instance;
	}

}

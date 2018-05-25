package rotmg.core.signals;

import rotmg.game._as3.Signal;

public class HideTooltipsSignal extends Signal {

	private static HideTooltipsSignal instance;

	public static HideTooltipsSignal getInstance() {
		if (instance == null) {
			instance = new HideTooltipsSignal();
		}
		return instance;
	}

}

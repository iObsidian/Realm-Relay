package rotmg.core.signals;

import org.osflash.signals.Signal;

public class HideTooltipsSignal extends Signal {

	private static HideTooltipsSignal instance;

	public static HideTooltipsSignal getInstance() {
		if (instance == null) {
			instance = new HideTooltipsSignal();
		}
		return instance;
	}

}

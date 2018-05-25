package rotmg.ui.signals;

import org.osflash.signals.Signal;

public class HUDModelInitialized extends Signal {

	private static HUDModelInitialized instance;

	public static HUDModelInitialized getInstance() {
		if (instance == null) {
			instance = new HUDModelInitialized();
		}
		return instance;
	}

}

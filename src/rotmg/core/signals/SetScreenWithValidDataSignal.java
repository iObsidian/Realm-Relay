package rotmg.core.signals;

import flash.display.Sprite;
import org.osflash.signals.Signal;

public class SetScreenWithValidDataSignal extends Signal<Sprite> {

	private static SetScreenWithValidDataSignal instance;

	public static SetScreenWithValidDataSignal getInstance() {
		if (instance == null) {
			instance = new SetScreenWithValidDataSignal();
		}
		return instance;
	}

}

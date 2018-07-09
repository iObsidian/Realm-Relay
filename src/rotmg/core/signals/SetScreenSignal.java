package rotmg.core.signals;

import flash.display.Sprite;
import org.osflash.signals.Signal;

public class SetScreenSignal extends Signal<Sprite> {

	private static SetScreenSignal instance;

	public static SetScreenSignal getInstance() {
		if (instance == null) {
			instance = new SetScreenSignal();
		}
		return instance;
	}

}

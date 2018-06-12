package rotmg.core.signals;

import org.osflash.signals.Signal;

import flash.display.Sprite;

public class SetScreenWithValidDataSignal extends Signal<Sprite> {

	private static SetScreenWithValidDataSignal instance;

	public static SetScreenWithValidDataSignal getInstance() {
		if (instance == null) {
			instance = new SetScreenWithValidDataSignal();
		}
		return instance;
	}

}

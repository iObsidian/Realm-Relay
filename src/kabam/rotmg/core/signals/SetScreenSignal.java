package kabam.rotmg.core.signals;

import org.osflash.signals.Signal;

import flash.display.Sprite;
public class SetScreenSignal extends Signal<Sprite> {

	private static SetScreenSignal instance;

	public static SetScreenSignal getInstance() {
		if (instance == null) {
			instance = new SetScreenSignal();
		}
		return instance;
	}

}

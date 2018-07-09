package rotmg.core.signals;

import alde.flash.utils.XML;
import org.osflash.signals.Signal;

public class AppInitDataReceivedSignal extends Signal<XML> {

	private static AppInitDataReceivedSignal instance;

	public static AppInitDataReceivedSignal getInstance() {
		if (instance == null) {
			instance = new AppInitDataReceivedSignal();
		}
		return instance;
	}

}

package rotmg.core.signals;

import org.osflash.signals.Signal;

import alde.flash.utils.XML;

public class AppInitDataReceivedSignal extends Signal<XML> {

	private static AppInitDataReceivedSignal instance;

	public static AppInitDataReceivedSignal getInstance() {
		if (instance == null) {
			instance = new AppInitDataReceivedSignal();
		}
		return instance;
	}

}

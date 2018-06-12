package rotmg.core.signals;

import org.osflash.signals.Signal;

public class UpdateNewCharacterScreenSignal extends Signal {

	private static UpdateNewCharacterScreenSignal instance;

	public static UpdateNewCharacterScreenSignal getInstance() {
		if (instance == null) {
			instance = new UpdateNewCharacterScreenSignal();
		}
		return instance;
	}

}

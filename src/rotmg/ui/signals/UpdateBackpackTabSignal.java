package rotmg.ui.signals;

import org.osflash.signals.Signal;

public class UpdateBackpackTabSignal extends Signal<Boolean> {

	private static UpdateBackpackTabSignal instance;

	public static UpdateBackpackTabSignal getInstance() {
		if (instance == null) {
			instance = new UpdateBackpackTabSignal();
		}
		return instance;
	}
}

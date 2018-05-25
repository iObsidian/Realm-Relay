package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;

public class NameChangedSignal extends Signal<String> {

	private static NameChangedSignal instance;

	public static NameChangedSignal getInstance() {
		if (instance == null) {
			instance = new NameChangedSignal();
		}
		return instance;
	}

}

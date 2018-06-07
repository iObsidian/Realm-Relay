package rotmg.ui.popups.signals;

import org.osflash.signals.Signal;
import rotmg.signals.AddTextLineSignal;

public class CloseAllPopupsSignal extends Signal {

	static CloseAllPopupsSignal instance;

	public static CloseAllPopupsSignal getInstance() {
		if (instance == null) {
			instance = new CloseAllPopupsSignal();
		}
		return instance;
	}
}

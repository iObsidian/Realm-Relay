package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;

public class ShowLoadingUISignal extends Signal {

	private static ShowLoadingUISignal instance;

	public static ShowLoadingUISignal getInstance() {
		if (instance == null) {
			instance = new ShowLoadingUISignal();
		}
		return instance;
	}

}

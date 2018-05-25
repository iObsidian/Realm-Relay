package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;

public class RefreshScreenAfterLoginSignal extends Signal {

	private static RefreshScreenAfterLoginSignal instance;

	public static RefreshScreenAfterLoginSignal getInstance() {
		if (instance == null) {
			instance = new RefreshScreenAfterLoginSignal();
		}
		return instance;
	}

}

package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;

public class ChooseNameSignal extends Signal {

	private static ChooseNameSignal instance;

	public static ChooseNameSignal getInstance() {
		if (instance == null) {
			instance = new ChooseNameSignal();
		}
		return instance;
	}

}

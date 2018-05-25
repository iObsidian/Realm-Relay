package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;

public class ShowKeyUISignal extends Signal {

	private static ShowKeyUISignal instance;

	public static ShowKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowKeyUISignal();
		}
		return instance;
	}

}

package rotmg.ui.signals;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.ui.model.Key;

public class ShowKeySignal extends Signal<Key> {

	private static ShowKeySignal instance;

	public static ShowKeySignal getInstance() {
		if (instance == null) {
			instance = new ShowKeySignal();
		}
		return instance;
	}

}

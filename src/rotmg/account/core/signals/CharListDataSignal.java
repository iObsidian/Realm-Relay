package rotmg.account.core.signals;

import org.osflash.signals.Signal;

public class CharListDataSignal extends Signal {

	CharListDataSignal instance;

	public CharListDataSignal getInstance() {
		if (instance == null) {
			instance = new CharListDataSignal();
		}

		return instance;
	}

}

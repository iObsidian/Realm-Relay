package rotmg.core.signals;

import org.osflash.signals.Signal;

public class MoneyFrameEnableCancelSignal extends Signal {

	private static MoneyFrameEnableCancelSignal instance;

	public static MoneyFrameEnableCancelSignal getInstance() {
		if (instance == null) {
			instance = new MoneyFrameEnableCancelSignal();
		}
		return instance;
	}

}

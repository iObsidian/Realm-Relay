package rotmg.core.signals;

import org.osflash.signals.Signal;
import rotmg.core.service.TrackingData;

public class TrackEventSignal extends Signal<TrackingData> {

	private static TrackEventSignal instance;

	public static TrackEventSignal getInstance() {
		if (instance == null) {
			instance = new TrackEventSignal();
		}
		return instance;
	}

}

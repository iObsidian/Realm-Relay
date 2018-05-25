package rotmg.core.signals;

public class TrackEventSignal extends Signal<TrackingData> {

	private static TrackEventSignal instance;

	public static TrackEventSignal getInstance() {
		if (instance == null) {
			instance = new TrackEventSignal();
		}
		return instance;
	}

}

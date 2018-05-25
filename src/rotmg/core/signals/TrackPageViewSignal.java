package rotmg.core.signals;

public class TrackPageViewSignal extends Signal<String> {

	private static TrackPageViewSignal instance;

	public static TrackPageViewSignal getInstance() {
		if (instance == null) {
			instance = new TrackPageViewSignal();
		}
		return instance;
	}

}

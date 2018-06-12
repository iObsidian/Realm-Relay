package rotmg;

public class MiniMapZoomSignal extends Signal<String> {

	public static final String OUT = "OUT";
	public static final String IN = "IN";
	private static MiniMapZoomSignal instance;

	public static MiniMapZoomSignal getInstance() {
		if (instance == null) {
			instance = new MiniMapZoomSignal();
		}
		return instance;
	}


}

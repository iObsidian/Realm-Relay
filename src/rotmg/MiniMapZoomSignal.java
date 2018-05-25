package rotmg;

import org.osflash.signals.Signal;
import rotmg.game._as3.Signal;

public class MiniMapZoomSignal extends Signal<String> {

	private static MiniMapZoomSignal instance;

	public static MiniMapZoomSignal getInstance() {
		if (instance == null) {
			instance = new MiniMapZoomSignal();
		}
		return instance;
	}

	public static final String OUT = "OUT";

	public static final String IN = "IN";


}

package rotmg.maploading.signals;

import org.osflash.signals.Signal;
import rotmg.messaging.incoming.MapInfo;

public class MapLoadedSignal extends Signal<MapInfo> {

	static MapLoadedSignal instance;

	public static MapLoadedSignal getInstance() {
		if (instance == null) {
			instance = new MapLoadedSignal();
		}
		return instance;
	}

}


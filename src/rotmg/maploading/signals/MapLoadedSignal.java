package rotmg.maploading.signals;

import rotmg.game._as3.Signal;
import rotmg.game.messaging.incoming.MapInfo;
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


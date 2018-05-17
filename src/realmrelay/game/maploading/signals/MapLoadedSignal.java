package realmrelay.game.maploading.signals;

import realmrelay.game._as3.Signal;
import realmrelay.game.messaging.incoming.MapInfo;

public class MapLoadedSignal extends Signal<MapInfo> {

	static MapLoadedSignal instance;

	public static MapLoadedSignal getInstance() {
		if (instance == null) {
			instance = new MapLoadedSignal();
		}
		return instance;
	}

}


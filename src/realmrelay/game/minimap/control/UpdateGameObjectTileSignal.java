package realmrelay.game.minimap.control;

import realmrelay.game.signals.AddTextLineSignal;

public class UpdateGameObjectTileSignal extends Signal<UpdateGameObjectTileVO> {

	static UpdateGameObjectTileSignal instance;

	public static UpdateGameObjectTileSignal getInstance() {
		if (instance == null) {
			instance = new UpdateGameObjectTileSignal();
		}
		return instance;
	}

}

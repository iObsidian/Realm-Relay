package realmrelay.game.minimap.control;

import realmrelay.game._as3.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.ui.model.UpdateGameObjectTileVO;

public class UpdateGameObjectTileSignal extends Signal<UpdateGameObjectTileVO> {

	static UpdateGameObjectTileSignal instance;

	public static UpdateGameObjectTileSignal getInstance() {
		if (instance == null) {
			instance = new UpdateGameObjectTileSignal();
		}
		return instance;
	}

}

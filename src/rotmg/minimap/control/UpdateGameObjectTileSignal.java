package rotmg.minimap.control;

import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.ui.model.UpdateGameObjectTileVO;

public class UpdateGameObjectTileSignal extends Signal<UpdateGameObjectTileVO> {

	static UpdateGameObjectTileSignal instance;

	public static UpdateGameObjectTileSignal getInstance() {
		if (instance == null) {
			instance = new UpdateGameObjectTileSignal();
		}
		return instance;
	}

}

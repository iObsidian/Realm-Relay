package rotmg.minimap.control;

import rotmg.ui.model.UpdateGameObjectTileVO;

public class UpdateGameObjectTileSignal extends Signal<UpdateGameObjectTileVO> {

	static UpdateGameObjectTileSignal instance;

	public static UpdateGameObjectTileSignal getInstance() {
		if (instance == null) {
			instance = new UpdateGameObjectTileSignal();
		}
		return instance;
	}

}

package rotmg.focus.control;

import org.osflash.signals.Signal;

import rotmg.minimap.model.UpdateGroundTileVO;

public class UpdateGroundTileSignal extends Signal<UpdateGroundTileVO> {

	static UpdateGroundTileSignal instance;

	public static UpdateGroundTileSignal getInstance() {
		if (instance == null) {
			instance = new UpdateGroundTileSignal();
		}
		return instance;
	}

}

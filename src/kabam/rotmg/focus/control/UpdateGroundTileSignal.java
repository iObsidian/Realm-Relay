package kabam.rotmg.focus.control;

import kabam.rotmg.minimap.model.UpdateGroundTileVO;
import org.osflash.signals.Signal;

public class UpdateGroundTileSignal extends Signal<UpdateGroundTileVO> {

	static UpdateGroundTileSignal instance;

	public static UpdateGroundTileSignal getInstance() {
		if (instance == null) {
			instance = new UpdateGroundTileSignal();
		}
		return instance;
	}

}

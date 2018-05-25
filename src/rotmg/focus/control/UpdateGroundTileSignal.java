package rotmg.focus.control;

import rotmg.game._as3.Signal;
import rotmg.game.minimap.model.UpdateGroundTileVO;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;
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

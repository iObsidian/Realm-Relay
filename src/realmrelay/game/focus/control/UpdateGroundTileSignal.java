package realmrelay.game.focus.control;

import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class UpdateGroundTileSignal extends Signal<UpdateGroundTileVO> {

	static UpdateGroundTileSignal instance;

	public static UpdateGroundTileSignal getInstance() {
		if (instance == null) {
			instance = new UpdateGroundTileSignal();
		}
		return instance;
	}

}

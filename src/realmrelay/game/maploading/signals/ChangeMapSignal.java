package realmrelay.game.maploading.signals;

import realmrelay.game._as3.Signal;
import realmrelay.game.focus.control.UpdateGroundTileSignal;

public class ChangeMapSignal  extends Signal {

	static ChangeMapSignal instance;

	public static ChangeMapSignal getInstance() {
		if (instance == null) {
			instance = new ChangeMapSignal();
		}
		return instance;
	}

}
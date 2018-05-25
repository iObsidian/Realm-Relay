package rotmg.maploading.signals;

import rotmg.game._as3.Signal;
import rotmg.game.focus.control.UpdateGroundTileSignal;

public class ChangeMapSignal  extends Signal {

	static ChangeMapSignal instance;

	public static ChangeMapSignal getInstance() {
		if (instance == null) {
			instance = new ChangeMapSignal();
		}
		return instance;
	}

}

package realmrelay.game.signals;
import realmrelay.game._as3.Signal;

public class UpdateGiftStatusDisplaySignal extends Signal {

	private static UpdateGiftStatusDisplaySignal instance;

	public static UpdateGiftStatusDisplaySignal getInstance() {
		if (instance == null) {
			instance = new UpdateGiftStatusDisplaySignal();
		}
		return instance;
	}

}


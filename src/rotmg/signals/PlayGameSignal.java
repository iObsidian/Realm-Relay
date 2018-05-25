package rotmg.signals;

import kabam.rotmg.game.model.GameInitData;
import rotmg.game._as3.Signal;
import rotmg.game.model.GameInitData;

public class PlayGameSignal extends Signal<GameInitData> {

	private static PlayGameSignal instance;

	public static PlayGameSignal getInstance() {
		if (instance == null) {
			instance = new PlayGameSignal();
		}
		return instance;
	}

}

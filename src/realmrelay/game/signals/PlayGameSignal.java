package realmrelay.game.signals;

import kabam.rotmg.game.model.GameInitData;
import realmrelay.game.Signal;

public class PlayGameSignal extends Signal<GameInitData> {

	private static PlayGameSignal instance;

	public static PlayGameSignal getInstance() {
		if (instance == null) {
			instance = new PlayGameSignal();
		}
		return instance;
	}

}

package realmrelay.game.signals;

import kabam.rotmg.game.model.GameInitData;
import realmrelay.game._as3.Signal;
import realmrelay.game.model.GameInitData;

public class PlayGameSignal extends Signal<GameInitData> {

	private static PlayGameSignal instance;

	public static PlayGameSignal getInstance() {
		if (instance == null) {
			instance = new PlayGameSignal();
		}
		return instance;
	}

}

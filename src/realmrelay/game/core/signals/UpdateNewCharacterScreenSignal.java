package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class UpdateNewCharacterScreenSignal extends Signal {

	private static UpdateNewCharacterScreenSignal instance;

	public static UpdateNewCharacterScreenSignal getInstance() {
		if (instance == null) {
			instance = new UpdateNewCharacterScreenSignal();
		}
		return instance;
	}

}

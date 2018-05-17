package realmrelay.game.maploading.signals;

import realmrelay.game._as3.Signal;

public class HideMapLoadingSignalNoFade extends Signal {

	static HideMapLoadingSignalNoFade instance;

	public static HideMapLoadingSignalNoFade getInstance() {
		if (instance == null) {
			instance = new HideMapLoadingSignalNoFade();
		}
		return instance;
	}

}


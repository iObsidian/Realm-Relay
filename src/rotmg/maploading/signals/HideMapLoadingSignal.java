package rotmg.maploading.signals;

import rotmg.game._as3.Signal;

public class HideMapLoadingSignal extends Signal {

	static HideMapLoadingSignal instance;

	public static HideMapLoadingSignal getInstance() {
		if (instance == null) {
			instance = new HideMapLoadingSignal();
		}
		return instance;
	}

}


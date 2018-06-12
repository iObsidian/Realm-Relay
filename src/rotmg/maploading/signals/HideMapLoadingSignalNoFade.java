package rotmg.maploading.signals;

import org.osflash.signals.Signal;

public class HideMapLoadingSignalNoFade extends Signal {

	static HideMapLoadingSignalNoFade instance;

	public static HideMapLoadingSignalNoFade getInstance() {
		if (instance == null) {
			instance = new HideMapLoadingSignalNoFade();
		}
		return instance;
	}

}


package rotmg.maploading.signals;

import org.osflash.signals.Signal;

public class ShowLoadingViewSignal extends Signal {
	static ShowLoadingViewSignal instance;

	public static ShowLoadingViewSignal getInstance() {
		if (instance == null) {
			instance = new ShowLoadingViewSignal();
		}
		return instance;
	}
}


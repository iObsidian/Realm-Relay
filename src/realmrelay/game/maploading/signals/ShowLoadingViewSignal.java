package realmrelay.game.maploading.signals;

public class ShowLoadingViewSignal<Signal> {
	static ShowLoadingViewSignal instance;

	public static ShowLoadingViewSignal getInstance() {
		if (instance == null) {
			instance = new ShowLoadingViewSignal();
		}
		return instance;
	}
}


package rotmg.ui.signals;

public class ShowLoadingUISignal extends Signal {

	private static ShowLoadingUISignal instance;

	public static ShowLoadingUISignal getInstance() {
		if (instance == null) {
			instance = new ShowLoadingUISignal();
		}
		return instance;
	}

}

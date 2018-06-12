package rotmg.ui.signals;

public class ShowTitleUISignal extends Signal {

	private static ShowTitleUISignal instance;

	public static ShowTitleUISignal getInstance() {
		if (instance == null) {
			instance = new ShowTitleUISignal();
		}
		return instance;
	}

}

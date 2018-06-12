package rotmg.ui.signals;

public class ShowKeyUISignal extends Signal {

	private static ShowKeyUISignal instance;

	public static ShowKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowKeyUISignal();
		}
		return instance;
	}

}

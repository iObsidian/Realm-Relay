package rotmg.signals;

public class SetTextBoxVisibilitySignal extends Signal<Boolean> {

	private static SetTextBoxVisibilitySignal instance;

	public static SetTextBoxVisibilitySignal getInstance() {
		if (instance == null) {
			instance = new SetTextBoxVisibilitySignal();
		}
		return instance;
	}

}

package rotmg.focus.control;

public class SetGameFocusSignal extends Signal<String> {

	static SetGameFocusSignal instance;

	public static SetGameFocusSignal getInstance() {
		if (instance == null) {
			instance = new SetGameFocusSignal();
		}
		return instance;
	}

}

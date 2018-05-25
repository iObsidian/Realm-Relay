package rotmg.dialogs;

public class CloseDialogsSignal extends Signal {

	static CloseDialogsSignal instance;

	public static CloseDialogsSignal getInstance() {
		if (instance == null) {
			instance = new CloseDialogsSignal();
		}
		return instance;
	}

}

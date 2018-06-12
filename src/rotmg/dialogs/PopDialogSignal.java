package rotmg.dialogs;

public class PopDialogSignal extends Signal {

	static PopDialogSignal instance;

	public static PopDialogSignal getInstance() {
		if (instance == null) {
			instance = new PopDialogSignal();
		}
		return instance;
	}


}

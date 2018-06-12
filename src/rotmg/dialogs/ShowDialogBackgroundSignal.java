package rotmg.dialogs;

public class ShowDialogBackgroundSignal extends Signal {

	static ShowDialogBackgroundSignal instance;

	public static ShowDialogBackgroundSignal getInstance() {
		if (instance == null) {
			instance = new ShowDialogBackgroundSignal();
		}
		return instance;
	}

}


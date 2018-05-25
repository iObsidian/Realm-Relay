package rotmg.dialogs;

import rotmg.game._as3.Signal;

public class ShowDialogBackgroundSignal extends Signal {

	static ShowDialogBackgroundSignal instance;

	public static ShowDialogBackgroundSignal getInstance() {
		if (instance == null) {
			instance = new ShowDialogBackgroundSignal();
		}
		return instance;
	}

}

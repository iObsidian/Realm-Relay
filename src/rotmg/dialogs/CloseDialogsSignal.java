package rotmg.dialogs;

import rotmg.game._as3.Signal;

public class CloseDialogsSignal extends Signal {

	static CloseDialogsSignal instance;

	public static CloseDialogsSignal getInstance() {
		if (instance == null) {
			instance = new CloseDialogsSignal();
		}
		return instance;
	}

}

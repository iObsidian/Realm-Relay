package realmrelay.game.ui.signals;

import realmrelay.game._as3.Signal;

public class NameChangedSignal extends Signal<String> {

	private static NameChangedSignal instance;

	public static NameChangedSignal getInstance() {
		if (instance == null) {
			instance = new NameChangedSignal();
		}
		return instance;
	}

}

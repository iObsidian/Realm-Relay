package realmrelay.game.ui.popups.signals;

import realmrelay.game.MiniMapZoomSignal;
import realmrelay.game._as3.Signal;

public class ClosePopupByClassSignal extends Signal<Class> {

	private static ClosePopupByClassSignal instance;

	public static ClosePopupByClassSignal getInstance() {
		if (instance == null) {
			instance = new ClosePopupByClassSignal();
		}
		return instance;
	}

}

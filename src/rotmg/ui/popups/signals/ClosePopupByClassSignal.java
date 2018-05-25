package rotmg.ui.popups.signals;

import org.osflash.signals.Signal;
import rotmg.game.MiniMapZoomSignal;
import rotmg.game._as3.Signal;

public class ClosePopupByClassSignal extends Signal<Class> {

	private static ClosePopupByClassSignal instance;

	public static ClosePopupByClassSignal getInstance() {
		if (instance == null) {
			instance = new ClosePopupByClassSignal();
		}
		return instance;
	}

}

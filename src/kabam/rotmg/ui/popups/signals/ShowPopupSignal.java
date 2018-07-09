package kabam.rotmg.ui.popups.signals;

import kabam.rotmg.ui.popups.BasePopup;
import org.osflash.signals.Signal;

import rotmg.ui.popups.BasePopup;

public class ShowPopupSignal extends Signal<BasePopup> {

	static ShowPopupSignal instance;

	public static ShowPopupSignal getInstance() {
		if (instance == null) {
			instance = new ShowPopupSignal();
		}

		return instance;
	}

}

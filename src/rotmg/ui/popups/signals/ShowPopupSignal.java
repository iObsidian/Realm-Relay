package rotmg.ui.popups.signals;

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

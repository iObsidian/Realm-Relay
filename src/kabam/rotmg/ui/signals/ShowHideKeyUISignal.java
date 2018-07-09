package kabam.rotmg.ui.signals;


import kabam.rotmg.signals.GiftStatusUpdateSignal;
import org.osflash.signals.Signal;

public class ShowHideKeyUISignal extends Signal<GiftStatusUpdateSignal> {

	private static ShowHideKeyUISignal instance;

	public static ShowHideKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowHideKeyUISignal();
		}
		return instance;
	}

}

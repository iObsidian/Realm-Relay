package rotmg.ui.signals;


import org.osflash.signals.Signal;
import rotmg.signals.GiftStatusUpdateSignal;

public class ShowHideKeyUISignal extends Signal<GiftStatusUpdateSignal> {

	private static ShowHideKeyUISignal instance;

	public static ShowHideKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowHideKeyUISignal();
		}
		return instance;
	}

}

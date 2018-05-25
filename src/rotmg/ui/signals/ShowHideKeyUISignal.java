package rotmg.ui.signals;


import org.osflash.signals.Signal;

import rotmg.signals.AddSpeechBalloonSignal;

public class ShowHideKeyUISignal extends Signal<AddSpeechBalloonSignal.GiftStatusUpdateSignal> {

	private static ShowHideKeyUISignal instance;

	public static ShowHideKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowHideKeyUISignal();
		}
		return instance;
	}

}

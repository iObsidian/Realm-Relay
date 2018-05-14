package realmrelay.game.ui.signals;


import realmrelay.game._as3.Signal;
import realmrelay.game.signals.AddSpeechBalloonSignal;

public class ShowHideKeyUISignal extends Signal<AddSpeechBalloonSignal.GiftStatusUpdateSignal> {

	private static ShowHideKeyUISignal instance;

	public static ShowHideKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowHideKeyUISignal();
		}
		return instance;
	}

}

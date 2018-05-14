package realmrelay.game.ui.signals;


import realmrelay.game.Signal;
import realmrelay.game.signals.AddTextLineSignal;

public class ShowHideKeyUISignal extends Signal<GiftStatusUpdateSignal> {

	private static ShowHideKeyUISignal instance;

	public static ShowHideKeyUISignal getInstance() {
		if (instance == null) {
			instance = new ShowHideKeyUISignal();
		}
		return instance;
	}

}

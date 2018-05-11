package realmrelay.game.ui.signals;


import realmrelay.game.Signal;

public class ShowHideKeyUISignal extends Signal<GiftStatusUpdateSignal> {

	public static ShowHideKeyUISignal instance;

	public ShowHideKeyUISignal() {
		super();
		instance = this;
	}

}

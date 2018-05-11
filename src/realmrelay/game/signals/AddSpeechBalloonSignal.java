package realmrelay.game.signals;

import realmrelay.game.Signal;

public class AddSpeechBalloonSignal extends Signal<AddSpeechBalloonVO> {

	private static AddSpeechBalloonSignal instance;

	public static AddSpeechBalloonSignal getInstance() {
		if (instance == null) {
			instance = new AddSpeechBalloonSignal();
		}
		return instance;
	}
}

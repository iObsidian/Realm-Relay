package realmrelay.game.signals;

import realmrelay.game._as3.Signal;

public class AddSpeechBalloonSignal extends Signal<AddSpeechBalloonVO> {

	private static AddSpeechBalloonSignal instance;

	public static AddSpeechBalloonSignal getInstance() {
		if (instance == null) {
			instance = new AddSpeechBalloonSignal();
		}
		return instance;
	}

	public static class GiftStatusUpdateSignal extends Signal {

		private static GiftStatusUpdateSignal instance;

		public static GiftStatusUpdateSignal getInstance() {
			if (instance == null) {
				instance = new GiftStatusUpdateSignal();
			}
			return instance;
		}

	}
}

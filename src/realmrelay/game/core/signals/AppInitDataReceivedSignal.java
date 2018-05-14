package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;
import realmrelay.game._as3.XML;
import realmrelay.game.signals.AddSpeechBalloonSignal;

public class AppInitDataReceivedSignal extends Signal<XML> {

	private static AppInitDataReceivedSignal instance;

	public static AppInitDataReceivedSignal getInstance() {
		if (instance == null) {
			instance = new AppInitDataReceivedSignal();
		}
		return instance;
	}

}

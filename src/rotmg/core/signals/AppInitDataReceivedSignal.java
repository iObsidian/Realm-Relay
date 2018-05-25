package rotmg.core.signals;

import rotmg.game._as3.Signal;
import rotmg.game._as3.XML;
import rotmg.game.signals.AddSpeechBalloonSignal;

public class AppInitDataReceivedSignal extends Signal<XML> {

	private static AppInitDataReceivedSignal instance;

	public static AppInitDataReceivedSignal getInstance() {
		if (instance == null) {
			instance = new AppInitDataReceivedSignal();
		}
		return instance;
	}

}

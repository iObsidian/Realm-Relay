package rotmg.language.model;

import rotmg.signals.AddSpeechBalloonSignal;

public class StringMap {

	static StringMap instance;

	public static StringMap getInstance() {
		if (instance == null) {
			instance = new StringMap();
		}
		return instance;
	}
}

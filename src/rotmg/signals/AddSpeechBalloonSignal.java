package rotmg.signals;

import org.osflash.signals.Signal;

import rotmg.model.AddSpeechBalloonVO;

public class AddSpeechBalloonSignal extends Signal<AddSpeechBalloonVO> {

	private static AddSpeechBalloonSignal instance;

	public static AddSpeechBalloonSignal getInstance() {
		if (instance == null) {
			instance = new AddSpeechBalloonSignal();
		}
		return instance;
	}

}

package kabam.rotmg.signals;


import kabam.rotmg.model.AddSpeechBalloonVO;
import org.osflash.signals.Signal;

public class AddSpeechBalloonSignal extends Signal<AddSpeechBalloonVO> {

	private static AddSpeechBalloonSignal instance;

	public static AddSpeechBalloonSignal getInstance() {
		if (instance == null) {
			instance = new AddSpeechBalloonSignal();
		}
		return instance;
	}

}

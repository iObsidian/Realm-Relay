package rotmg.signals;

import org.osflash.signals.Signal;

import rotmg.model.GameInitData;

public class PlayGameSignal extends Signal<GameInitData> {

	private static PlayGameSignal instance;

	public static PlayGameSignal getInstance() {
		if (instance == null) {
			instance = new PlayGameSignal();
		}
		return instance;
	}

}

package rotmg.signals;

import org.osflash.signals.Signal;

import rotmg.model.UseBuyPotionVO;

public class UseBuyPotionSignal extends Signal<UseBuyPotionVO> {

	private static UseBuyPotionSignal instance;

	public static UseBuyPotionSignal getInstance() {
		if (instance == null) {
			instance = new UseBuyPotionSignal();
		}
		return instance;
	}

}

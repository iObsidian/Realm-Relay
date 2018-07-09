package kabam.rotmg.signals;

import kabam.rotmg.model.UseBuyPotionVO;
import org.osflash.signals.Signal;

public class UseBuyPotionSignal extends Signal<UseBuyPotionVO> {

	private static UseBuyPotionSignal instance;

	public static UseBuyPotionSignal getInstance() {
		if (instance == null) {
			instance = new UseBuyPotionSignal();
		}
		return instance;
	}

}

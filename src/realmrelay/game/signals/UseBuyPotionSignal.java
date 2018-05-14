package realmrelay.game.signals;

import kabam.rotmg.game.model.UseBuyPotionVO;
import realmrelay.game.Signal;

public class UseBuyPotionSignal extends Signal<UseBuyPotionVO> {

	private static UseBuyPotionSignal instance;

	public static UseBuyPotionSignal getInstance() {
		if (instance == null) {
			instance = new UseBuyPotionSignal();
		}
		return instance;
	}

}

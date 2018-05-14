package realmrelay.game.signals;

import kabam.rotmg.game.model.UseBuyPotionVO;
import realmrelay.game._as3.Signal;

public class UseBuyPotionSignal extends Signal<UseBuyPotionVO> {

	private static UseBuyPotionSignal instance;

	public static UseBuyPotionSignal getInstance() {
		if (instance == null) {
			instance = new UseBuyPotionSignal();
		}
		return instance;
	}

}

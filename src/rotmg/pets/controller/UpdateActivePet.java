package rotmg.pets.controller;

import rotmg.game._as3.Signal;
import rotmg.game.signals.GameClosedSignal;

public class UpdateActivePet extends Signal<Integer> {

	private static UpdateActivePet instance;

	public static UpdateActivePet getInstance() {
		if (instance == null) {
			instance = new UpdateActivePet();
		}
		return instance;
	}

}

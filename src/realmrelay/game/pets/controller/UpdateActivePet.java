package realmrelay.game.pets.controller;

import realmrelay.game._as3.Signal;
import realmrelay.game.signals.GameClosedSignal;

public class UpdateActivePet extends Signal<Integer> {

	private static UpdateActivePet instance;

	public static UpdateActivePet getInstance() {
		if (instance == null) {
			instance = new UpdateActivePet();
		}
		return instance;
	}

}

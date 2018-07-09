package rotmg.pets.controller;

import org.osflash.signals.Signal;

public class UpdateActivePet extends Signal<Integer> {

	private static UpdateActivePet instance;

	public static UpdateActivePet getInstance() {
		if (instance == null) {
			instance = new UpdateActivePet();
		}
		return instance;
	}

}

package rotmg.pets.controller;

import org.osflash.signals.Signal;

public class PetFeedResultSignal extends Signal {
	static PetFeedResultSignal instance;

	public static PetFeedResultSignal getInstance() {
		if (instance == null) {
			instance = new PetFeedResultSignal();
		}
		return instance;
	}
}

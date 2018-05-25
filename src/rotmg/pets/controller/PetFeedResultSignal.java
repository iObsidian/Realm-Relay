package rotmg.pets.controller;

import rotmg.game._as3.Signal;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class PetFeedResultSignal extends Signal {
	static PetFeedResultSignal instance;

	public static PetFeedResultSignal getInstance() {
		if (instance == null) {
			instance = new PetFeedResultSignal();
		}
		return instance;
	}
}

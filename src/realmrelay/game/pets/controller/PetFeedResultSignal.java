package realmrelay.game.pets.controller;

import realmrelay.game._as3.Signal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class PetFeedResultSignal extends Signal {
	static PetFeedResultSignal instance;

	public static PetFeedResultSignal getInstance() {
		if (instance == null) {
			instance = new PetFeedResultSignal();
		}
		return instance;
	}
}

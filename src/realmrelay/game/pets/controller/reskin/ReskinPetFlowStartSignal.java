package realmrelay.game.pets.controller.reskin;

import realmrelay.game._as3.Signal;
import realmrelay.game.messaging.outgoing.Reskin;

public class ReskinPetFlowStartSignal extends Signal {

	public ReskinPetFlowStartSignal instance;

	public ReskinPetFlowStartSignal getInstance() {
		if (instance == null) {
			instance = new ReskinPetFlowStartSignal();
		}
		return instance;
	}
}

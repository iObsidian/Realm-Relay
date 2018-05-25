package rotmg.pets.controller.reskin;

import rotmg.game._as3.Signal;
import rotmg.game.messaging.outgoing.Reskin;

public class ReskinPetFlowStartSignal extends Signal {

	public ReskinPetFlowStartSignal instance;

	public ReskinPetFlowStartSignal getInstance() {
		if (instance == null) {
			instance = new ReskinPetFlowStartSignal();
		}
		return instance;
	}
}

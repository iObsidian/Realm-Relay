package rotmg.pets.controller.reskin;

import org.osflash.signals.Signal;

public class ReskinPetFlowStartSignal extends Signal {

	public static ReskinPetFlowStartSignal instance;

	public static ReskinPetFlowStartSignal getInstance() {
		if (instance == null) {
			instance = new ReskinPetFlowStartSignal();
		}
		return instance;
	}
}

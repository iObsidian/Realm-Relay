package rotmg.pets.controller.reskin;

public class ReskinPetFlowStartSignal extends Signal {

	public static ReskinPetFlowStartSignal instance;

	public static ReskinPetFlowStartSignal getInstance() {
		if (instance == null) {
			instance = new ReskinPetFlowStartSignal();
		}
		return instance;
	}
}

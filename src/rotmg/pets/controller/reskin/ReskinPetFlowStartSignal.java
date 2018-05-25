package rotmg.pets.controller.reskin;

public class ReskinPetFlowStartSignal extends Signal {

	public ReskinPetFlowStartSignal instance;

	public ReskinPetFlowStartSignal getInstance() {
		if (instance == null) {
			instance = new ReskinPetFlowStartSignal();
		}
		return instance;
	}
}

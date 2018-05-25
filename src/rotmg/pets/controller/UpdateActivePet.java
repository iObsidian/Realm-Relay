package rotmg.pets.controller;

public class UpdateActivePet extends Signal<Integer> {

	private static UpdateActivePet instance;

	public static UpdateActivePet getInstance() {
		if (instance == null) {
			instance = new UpdateActivePet();
		}
		return instance;
	}

}

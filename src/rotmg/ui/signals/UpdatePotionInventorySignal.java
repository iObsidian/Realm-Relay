package rotmg.ui.signals;

public class UpdatePotionInventorySignal extends Signal {

	private static UpdatePotionInventorySignal instance;

	public static UpdatePotionInventorySignal getInstance() {
		if (instance == null) {
			instance = new UpdatePotionInventorySignal();
		}
		return instance;
	}

}

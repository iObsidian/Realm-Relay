package rotmg.core.signals;

public class PurchaseCharacterSignal extends Signal<Integer> {

	private static PurchaseCharacterSignal instance;

	public static PurchaseCharacterSignal getInstance() {
		if (instance == null) {
			instance = new PurchaseCharacterSignal();
		}
		return instance;
	}

}

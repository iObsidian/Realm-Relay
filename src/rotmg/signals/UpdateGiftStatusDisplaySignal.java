package rotmg.signals;

public class UpdateGiftStatusDisplaySignal extends Signal {

	private static UpdateGiftStatusDisplaySignal instance;

	public static UpdateGiftStatusDisplaySignal getInstance() {
		if (instance == null) {
			instance = new UpdateGiftStatusDisplaySignal();
		}
		return instance;
	}

}


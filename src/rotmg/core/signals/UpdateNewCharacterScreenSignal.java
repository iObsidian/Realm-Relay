package rotmg.core.signals;

public class UpdateNewCharacterScreenSignal extends Signal {

	private static UpdateNewCharacterScreenSignal instance;

	public static UpdateNewCharacterScreenSignal getInstance() {
		if (instance == null) {
			instance = new UpdateNewCharacterScreenSignal();
		}
		return instance;
	}

}

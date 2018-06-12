package rotmg.ui.signals;

public class UpdateBackpackTabSignal extends Signal<Boolean> {

	private static UpdateBackpackTabSignal instance;

	public static UpdateBackpackTabSignal getInstance() {
		if (instance == null) {
			instance = new UpdateBackpackTabSignal();
		}
		return instance;
	}
}

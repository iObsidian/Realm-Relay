package rotmg.signals;

public class SetWorldInteractionSignal extends Signal<Boolean> {

	private static SetWorldInteractionSignal instance;

	public static SetWorldInteractionSignal getInstance() {
		if (instance == null) {
			instance = new SetWorldInteractionSignal();
		}
		return instance;
	}

}

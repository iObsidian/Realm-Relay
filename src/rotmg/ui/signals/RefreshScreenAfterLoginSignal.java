package rotmg.ui.signals;

public class RefreshScreenAfterLoginSignal extends Signal {

	private static RefreshScreenAfterLoginSignal instance;

	public static RefreshScreenAfterLoginSignal getInstance() {
		if (instance == null) {
			instance = new RefreshScreenAfterLoginSignal();
		}
		return instance;
	}

}

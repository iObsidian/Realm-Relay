package rotmg.ui.popups.signals;

public class ClosePopupByClassSignal extends Signal<Class> {

	private static ClosePopupByClassSignal instance;

	public static ClosePopupByClassSignal getInstance() {
		if (instance == null) {
			instance = new ClosePopupByClassSignal();
		}
		return instance;
	}

}

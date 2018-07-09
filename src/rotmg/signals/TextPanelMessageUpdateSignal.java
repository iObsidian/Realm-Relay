package rotmg.signals;

import org.osflash.signals.Signal;

public class TextPanelMessageUpdateSignal extends Signal<String> {

	private static TextPanelMessageUpdateSignal instance;

	public static TextPanelMessageUpdateSignal getInstance() {
		if (instance == null) {
			instance = new TextPanelMessageUpdateSignal();
		}
		return instance;
	}

}


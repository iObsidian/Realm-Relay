package realmrelay.game.signals;
import realmrelay.game._as3.Signal;

public class TextPanelMessageUpdateSignal extends Signal<String> {

	private static TextPanelMessageUpdateSignal instance;

	public static TextPanelMessageUpdateSignal getInstance() {
		if (instance == null) {
			instance = new TextPanelMessageUpdateSignal();
		}
		return instance;
	}

}


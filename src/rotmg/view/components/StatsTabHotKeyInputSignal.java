package rotmg.view.components;

public class StatsTabHotKeyInputSignal extends Signal {

	private static StatsTabHotKeyInputSignal instance;

	public static StatsTabHotKeyInputSignal getInstance() {
		if (instance == null) {
			instance = new StatsTabHotKeyInputSignal();
		}
		return instance;
	}

}

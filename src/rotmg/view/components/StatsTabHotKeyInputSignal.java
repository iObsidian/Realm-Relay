package rotmg.view.components;

import rotmg.game._as3.Signal;

public class StatsTabHotKeyInputSignal extends Signal {

	private static StatsTabHotKeyInputSignal instance;

	public static StatsTabHotKeyInputSignal getInstance() {
		if (instance == null) {
			instance = new StatsTabHotKeyInputSignal();
		}
		return instance;
	}

}

package rotmg.core.signals;

import rotmg.game._as3.Signal;

public class ShowTooltipSignal extends Signal<ToolTip> {

	private static ShowTooltipSignal instance;

	public static ShowTooltipSignal getInstance() {
		if (instance == null) {
			instance = new ShowTooltipSignal();
		}
		return instance;
	}

}

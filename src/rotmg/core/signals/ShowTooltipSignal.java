package rotmg.core.signals;

import org.osflash.signals.Signal;
import rotmg.ui.tooltip.ToolTip;

public class ShowTooltipSignal extends Signal<ToolTip> {

	private static ShowTooltipSignal instance;

	public static ShowTooltipSignal getInstance() {
		if (instance == null) {
			instance = new ShowTooltipSignal();
		}
		return instance;
	}

}

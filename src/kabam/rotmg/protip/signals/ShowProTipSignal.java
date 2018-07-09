package protip.signals;

import org.osflash.signals.Signal;
import rotmg.account.core.Account;

public class ShowProTipSignal extends Signal {

	public static ShowProTipSignal instance;

	public static ShowProTipSignal getInstance() {
		if (instance == null) {
			instance = new ShowProTipSignal();
		}
		return instance;
	}
}

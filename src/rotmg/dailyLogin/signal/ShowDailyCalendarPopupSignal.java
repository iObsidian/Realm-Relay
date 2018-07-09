package rotmg.dailyLogin.signal;

import org.osflash.signals.Signal;

public class ShowDailyCalendarPopupSignal extends Signal {
	static ShowDailyCalendarPopupSignal instance;

	public static ShowDailyCalendarPopupSignal getInstance() {
		if (instance == null) {
			instance = new ShowDailyCalendarPopupSignal();
		}
		return instance;
	}
}

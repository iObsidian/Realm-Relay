package rotmg.dailyLogin.signal;

import rotmg.game._as3.Signal;

public class ShowDailyCalendarPopupSignal extends Signal {
	static ShowDailyCalendarPopupSignal instance;

	public static ShowDailyCalendarPopupSignal getInstance() {
		if (instance == null) {
			instance = new ShowDailyCalendarPopupSignal();
		}
		return instance;
	}
}

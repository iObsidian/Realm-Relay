package rotmg.dailyLogin.signal;

import rotmg.messaging.incoming.ClaimDailyRewardResponse;

public class ClaimDailyRewardResponseSignal extends Signal<ClaimDailyRewardResponse> {
	static ClaimDailyRewardResponseSignal instance;

	public static ClaimDailyRewardResponseSignal getInstance() {
		if (instance == null) {
			instance = new ClaimDailyRewardResponseSignal();
		}
		return instance;
	}
}

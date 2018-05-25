package rotmg.dailyLogin.signal;

import rotmg.game._as3.Signal;
import rotmg.game.messaging.incoming.ClaimDailyRewardResponse;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class ClaimDailyRewardResponseSignal extends Signal<ClaimDailyRewardResponse> {
	static ClaimDailyRewardResponseSignal instance;

	public static ClaimDailyRewardResponseSignal getInstance() {
		if (instance == null) {
			instance = new ClaimDailyRewardResponseSignal();
		}
		return instance;
	}
}

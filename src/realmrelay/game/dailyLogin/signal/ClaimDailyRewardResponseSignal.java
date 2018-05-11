package realmrelay.game.dailyLogin.signal;

import realmrelay.game.Signal;
import realmrelay.game.messaging.incoming.ClaimDailyRewardResponse;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class ClaimDailyRewardResponseSignal extends Signal<ClaimDailyRewardResponse> {
	static ClaimDailyRewardResponseSignal instance;

	public static ClaimDailyRewardResponseSignal getInstance() {
		if (instance == null) {
			instance = new ClaimDailyRewardResponseSignal();
		}
		return instance;
	}
}

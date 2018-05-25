package rotmg.dailyQuests.signal;

import rotmg.game._as3.Signal;
import rotmg.game.dailyQuests.QuestRedeemResponse;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class QuestRedeemCompleteSignal extends Signal<QuestRedeemResponse> {
	static QuestRedeemCompleteSignal instance;

	public static QuestRedeemCompleteSignal getInstance() {
		if (instance == null) {
			instance = new QuestRedeemCompleteSignal();
		}
		return instance;
	}
}

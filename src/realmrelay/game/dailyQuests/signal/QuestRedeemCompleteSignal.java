package realmrelay.game.dailyQuests.signal;

import realmrelay.game._as3.Signal;
import realmrelay.game.dailyQuests.QuestRedeemResponse;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class QuestRedeemCompleteSignal extends Signal<QuestRedeemResponse> {
	static QuestRedeemCompleteSignal instance;

	public static QuestRedeemCompleteSignal getInstance() {
		if (instance == null) {
			instance = new QuestRedeemCompleteSignal();
		}
		return instance;
	}
}

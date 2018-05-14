package realmrelay.game.dailyQuests.signal;

import realmrelay.game._as3.Signal;
import realmrelay.game.dailyQuests.QuestFetchResponse;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;

public class QuestFetchCompleteSignal extends Signal<QuestFetchResponse> {
	static QuestFetchCompleteSignal instance;

	public static QuestFetchCompleteSignal getInstance() {
		if (instance == null) {
			instance = new QuestFetchCompleteSignal();
		}
		return instance;
	}
}

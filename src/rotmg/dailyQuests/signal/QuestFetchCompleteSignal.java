package rotmg.dailyQuests.signal;

import rotmg.game._as3.Signal;
import rotmg.game.dailyQuests.QuestFetchResponse;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;

public class QuestFetchCompleteSignal extends Signal<QuestFetchResponse> {
	static QuestFetchCompleteSignal instance;

	public static QuestFetchCompleteSignal getInstance() {
		if (instance == null) {
			instance = new QuestFetchCompleteSignal();
		}
		return instance;
	}
}

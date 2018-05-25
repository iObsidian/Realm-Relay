package rotmg.dailyQuests.signal;

import rotmg.dailyQuests.QuestFetchResponse;

public class QuestFetchCompleteSignal extends Signal<QuestFetchResponse> {
	static QuestFetchCompleteSignal instance;

	public static QuestFetchCompleteSignal getInstance() {
		if (instance == null) {
			instance = new QuestFetchCompleteSignal();
		}
		return instance;
	}
}

package rotmg.dailyQuests.signal;

import org.osflash.signals.Signal;
import rotmg.dailyQuests.QuestRedeemResponse;

public class QuestRedeemCompleteSignal extends Signal<QuestRedeemResponse> {
	static QuestRedeemCompleteSignal instance;

	public static QuestRedeemCompleteSignal getInstance() {
		if (instance == null) {
			instance = new QuestRedeemCompleteSignal();
		}
		return instance;
	}
}

package rotmg.dailyQuests.signal;


import kabam.rotmg.dailyQuests.QuestRedeemResponse;
import org.osflash.signals.Signal;

public class QuestRedeemCompleteSignal extends Signal<QuestRedeemResponse> {
	static QuestRedeemCompleteSignal instance;

	public static QuestRedeemCompleteSignal getInstance() {
		if (instance == null) {
			instance = new QuestRedeemCompleteSignal();
		}
		return instance;
	}
}

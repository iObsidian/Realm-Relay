package realmrelay.game.dailyQuests;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.dailyQuests.messages.data.QuestData;
import realmrelay.game.messaging.incoming.IncomingMessage;

public class QuestFetchResponse extends IncomingMessage {

	public QuestData[] quests;

	public QuestFetchResponse(int param1, Consumer param2) {
		super(param1, param2);
		this.quests = new QuestData[0];
	}

	public void parseFromInput(DataInput param1) throws IOException {
		int loc2 = param1.readShort();
		int loc3 = 0;
		while (loc3 < loc2) {
			this.quests[loc3] = new QuestData();
			this.quests[loc3].parseFromInput(param1);
			loc3++;
		}
	}

}

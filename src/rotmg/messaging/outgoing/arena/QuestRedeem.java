package rotmg.messaging.outgoing.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.game.messaging.data.SlotObjectData;
import rotmg.game.messaging.outgoing.OutgoingMessage;

public class QuestRedeem extends OutgoingMessage {

	String questID;
	SlotObjectData[] slots;
	int item;

	public QuestRedeem(int id, Consumer callback) {
		super(id, callback);
	}
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
		questID = in.readUTF();
		item = in.readInt();
		slots = new SlotObjectData[in.readShort()];
		for (int i = 0; i < slots.length; i++) {
			SlotObjectData s = new SlotObjectData();
			s.parseFromInput(in);
			slots[i] = s;
		}

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(questID);
		out.writeInt(item);
		out.writeShort(slots.length);

		for (int i = 0; i < slots.length; i++) {
			slots[i].writeToOutput(out);
		}
	}

}

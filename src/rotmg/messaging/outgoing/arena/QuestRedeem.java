package rotmg.messaging.outgoing.arena;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.SlotObjectData;
import rotmg.messaging.outgoing.OutgoingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class QuestRedeem extends OutgoingMessage {

	String questID;
	SlotObjectData[] slots;
	int item;

	public QuestRedeem(int id, MessageConsumer callback) {
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

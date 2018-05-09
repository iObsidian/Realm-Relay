package realmrelay.packets.client.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.SlotObjectData;

public class QuestRedeemPacket extends Packet {

	String questID;
	SlotObjectData[] slots;
	int item;

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

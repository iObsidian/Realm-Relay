package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.SlotObject;
import realmrelay.packets.Packet;

public class TinkerQuestPacket extends Packet {

	private SlotObject item = new SlotObject();

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		item.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		item.writeToOutput(out);
	}

}

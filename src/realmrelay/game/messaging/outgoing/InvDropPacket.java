package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game.messaging.data.SlotObjectData;
import realmrelay.packets.Packet;

public class InvDropPacket extends Packet {

	private SlotObjectData slotObject;

	public InvDropPacket() {
		slotObject = new SlotObjectData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		slotObject.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		slotObject.writeToOutput(out);
	}

}
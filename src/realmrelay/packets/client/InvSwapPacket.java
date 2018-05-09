package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.SlotObjectData;
import realmrelay.packets.data.WorldPosData;

public class InvSwapPacket extends Packet {

	private int time;
	private WorldPosData position;
	private SlotObjectData slotObject1;
	private SlotObjectData slotObject2;

	public InvSwapPacket() {
		position = new WorldPosData();
		slotObject1 = new SlotObjectData();
		slotObject2 = new SlotObjectData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		position.parseFromInput(in);
		slotObject1.parseFromInput(in);
		slotObject2.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		position.writeToOutput(out);
		slotObject1.writeToOutput(out);
		slotObject2.writeToOutput(out);
	}

}

package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.Location;
import realmrelay.data.SlotObject;
import realmrelay.packets.Packet;


public class InvSwapPacket extends Packet {

	private int time;
	private Location position = new Location();
	private SlotObject slotObject1 = new SlotObject();
	private SlotObject slotObject2 = new SlotObject();

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

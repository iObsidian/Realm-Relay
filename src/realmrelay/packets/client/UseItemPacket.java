package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.Location;
import realmrelay.data.SlotObject;
import realmrelay.packets.Packet;

public class UseItemPacket extends Packet {

	private int time;
	private SlotObject slotObject = new SlotObject();
	private Location itemUsePos = new Location();
	private int useType;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		slotObject.parseFromInput(in);
		itemUsePos.parseFromInput(in);
		useType = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		slotObject.writeToOutput(out);
		itemUsePos.writeToOutput(out);
		out.writeByte(useType);
	}

}

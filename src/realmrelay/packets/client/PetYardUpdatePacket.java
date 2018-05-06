package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.SlotObject;
import realmrelay.packets.Packet;

public class PetYardUpdatePacket extends Packet {

	public final int UPGRADE_PET_YARD = 1;
	public final int FEED_PET = 2;
	public final int FUSE_PET = 3;

	private byte commandId;
	private int petId1;
	private int petId2;
	private int objectId;
	private SlotObject objectSlot;
	private byte currency;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		commandId = in.readByte();
		petId1 = in.readInt();
		petId2 = in.readInt();
		objectId = in.readInt();

		SlotObject e = new SlotObject();
		e.parseFromInput(in);
		objectSlot = e;

		currency = in.readByte();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.write(commandId);
		out.write(petId1);
		out.write(petId2);
		out.write(objectId);
		objectSlot.writeToOutput(out);
		out.write(currency);
	}

}

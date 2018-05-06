package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class PetEvolveResultPacket extends Packet {

	private int petId;
	private int skinId1;
	private int skinId2;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petId = in.readInt();
		skinId1 = in.readInt();
		skinId2 = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(petId);
		out.writeInt(skinId1);
		out.writeInt(skinId2);
	}

}

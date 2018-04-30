package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Hatch_PetPacket extends Packet {

	private String petName;
	private int petSkinId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petName = in.readUTF();
		petSkinId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(petName);
		out.writeInt(petSkinId);

	}

}

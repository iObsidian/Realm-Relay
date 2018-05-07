package realmrelay.packets.server.pets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class DeletePetMessagePacket extends Packet {

	int petID;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petID = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(petID);
	}

}

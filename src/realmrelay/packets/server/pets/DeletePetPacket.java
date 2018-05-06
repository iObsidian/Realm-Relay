package realmrelay.packets.server.pets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class DeletePetPacket extends Packet {

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		int petID = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
	}

}

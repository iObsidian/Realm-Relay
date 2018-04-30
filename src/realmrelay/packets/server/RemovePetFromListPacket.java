package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class RemovePetFromListPacket extends Packet {

	private int petId;

	public void parseFromInput(DataInput in) throws IOException {
		this.petId = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.petId);
	}
}

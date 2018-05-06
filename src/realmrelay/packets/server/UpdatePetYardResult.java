package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class UpdatePetYardResult extends Packet {

	private int typeId;

	public void parseFromInput(DataInput in) throws IOException {
		this.typeId = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.typeId);
	}

}

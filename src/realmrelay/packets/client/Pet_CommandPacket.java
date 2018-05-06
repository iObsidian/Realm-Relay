package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Pet_CommandPacket extends Packet {

	public final int FOLLOW_PET = 1;
	public final int UNFOLLOW_PET = 2;
	public final int RELEASE_PET = 3;

	private int commandId;
	private int petId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		commandId = in.readInt();
		petId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(commandId);
		out.writeInt(petId);
	}

}

package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ActivePetUpdateRequest extends Packet {

	private int commandtype;
	private int instanceid;

	public void parseFromInput(DataInput in) throws IOException {
		commandtype = in.readByte();
		instanceid = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(commandtype);
		out.writeByte(instanceid);
	}
}

package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class InvResultPacket extends Packet {

	public int result;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		result = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(result);
	}

}

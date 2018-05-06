package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class KeyInfoResponsePacket extends Packet {

	private byte[] response;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		response = new byte[in.readInt()];
		in.readFully(response);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(response.length);
		out.write(response);
	}

}

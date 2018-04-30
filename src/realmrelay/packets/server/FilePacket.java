package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class FilePacket extends Packet {

	private String name;
	private byte[] bytes = new byte[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		name = in.readUTF();
		bytes = new byte[in.readInt()];
		in.readFully(bytes);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(bytes.length);
		out.write(bytes);
	}

}

package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Key_Info_RequestPacket extends Packet {

	private byte[] request;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.request = new byte[in.readShort()];
		in.readFully(this.request);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeShort(this.request.length);
		out.write(this.request);
	}

}

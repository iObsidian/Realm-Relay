package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Accept_Arena_DeathPacket extends Packet {

	private int time;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.time = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
	}

}

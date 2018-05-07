package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

import realmrelay.packets.data.WorldPosData;

public class AoeAckPacket extends Packet {

	public int time;
	public WorldPosData position;

	public AoeAckPacket() {
		position = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		position.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		position.writeToOutput(out);
	}

}

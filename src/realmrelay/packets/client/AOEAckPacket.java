package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.Location;
import realmrelay.packets.Packet;

public class AOEAckPacket extends Packet {

	public int time;
	public Location position = new Location();

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

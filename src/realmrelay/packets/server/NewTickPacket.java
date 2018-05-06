package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.Status;

public class NewTickPacket extends Packet {

	public int tickId;
	public int tickTime;
	public Status[] statuses = new Status[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tickId = in.readInt();
		tickTime = in.readInt();
		statuses = new Status[in.readShort()];
		for (int i = 0; i < statuses.length; i++) {
			Status status = new Status();
			status.parseFromInput(in);
			statuses[i] = status;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(tickId);
		out.writeInt(tickTime);
		out.writeShort(statuses.length);
		for (Status status : statuses) {
			status.writeToOutput(out);
		}
	}

}

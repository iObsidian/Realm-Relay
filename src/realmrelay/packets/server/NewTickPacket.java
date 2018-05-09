package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.ObjectStatusData;

public class NewTickPacket extends Packet {

	public int tickId;
	public int tickTime;
	public ObjectStatusData[] statuses = new ObjectStatusData[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tickId = in.readInt();
		tickTime = in.readInt();
		statuses = new ObjectStatusData[in.readShort()];
		for (int i = 0; i < statuses.length; i++) {
			ObjectStatusData ObjectStatusData = new ObjectStatusData();
			ObjectStatusData.parseFromInput(in);
			statuses[i] = ObjectStatusData;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(tickId);
		out.writeInt(tickTime);
		out.writeShort(statuses.length);
		for (ObjectStatusData ObjectStatusData : statuses) {
			ObjectStatusData.writeToOutput(out);
		}
	}

}

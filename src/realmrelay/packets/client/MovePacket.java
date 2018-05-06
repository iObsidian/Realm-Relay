package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.Location;
import realmrelay.data.LocationRecord;
import realmrelay.packets.Packet;

public class MovePacket extends Packet {

	public int tickId;
	public int time;
	public Location newPosition = new Location();
	public LocationRecord[] records = new LocationRecord[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tickId = in.readInt();
		time = in.readInt();
		newPosition.parseFromInput(in);
		records = new LocationRecord[in.readShort()];
		for (int i = 0; i < records.length; i++) {
			LocationRecord record = new LocationRecord();
			record.parseFromInput(in);
			records[i] = record;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(tickId);
		out.writeInt(time);
		newPosition.writeToOutput(out);
		out.writeShort(records.length);
		for (LocationRecord record : records) {
			record.writeToOutput(out);
		}
	}

}

package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

import realmrelay.packets.data.MoveRecord;
import realmrelay.packets.data.WorldPosData;

public class MovePacket extends Packet {

	public int tickId;
	public int time;
	public WorldPosData newPosition = new WorldPosData();
	public MoveRecord[] records = new MoveRecord[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tickId = in.readInt();
		time = in.readInt();
		newPosition.parseFromInput(in);
		records = new MoveRecord[in.readShort()];
		for (int i = 0; i < records.length; i++) {
			MoveRecord record = new MoveRecord();
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
		for (MoveRecord record : records) {
			record.writeToOutput(out);
		}
	}

}

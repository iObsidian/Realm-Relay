package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.WorldPosData;

public class GotoPacket extends Packet {

	public int objectId;
	public WorldPosData pos;

	public GotoPacket() {
		pos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectId = in.readInt();
		pos.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(objectId);
		pos.writeToOutput(out);
	}

}

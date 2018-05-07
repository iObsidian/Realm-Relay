package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class OtherHitPacket extends Packet {

	private int time;
	private int bulletId;
	private int objectId;
	private int targetId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		bulletId = in.readByte();
		objectId = in.readInt();
		targetId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeByte(bulletId);
		out.writeInt(objectId);
		out.writeInt(targetId);
	}

}

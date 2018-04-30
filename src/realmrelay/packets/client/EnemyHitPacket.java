package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class EnemyHitPacket extends Packet {

	private int time;
	private int bulletId;
	private int targetId;
	private boolean killed;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		bulletId = in.readUnsignedByte();
		targetId = in.readInt();
		killed = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeByte(bulletId);
		out.writeInt(targetId);
		out.writeBoolean(killed);
	}

}

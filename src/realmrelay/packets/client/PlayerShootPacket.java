package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.WorldPosData;

public class PlayerShootPacket extends Packet {

	private int time;
	private int bulletId;
	private int containerType;
	private WorldPosData startingPos;
	private float angle;

	public PlayerShootPacket() {
		startingPos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		bulletId = in.readUnsignedByte();
		containerType = in.readUnsignedShort();
		startingPos.parseFromInput(in);
		angle = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeByte(bulletId);
		out.writeShort(containerType);
		startingPos.writeToOutput(out);
		out.writeFloat(angle);
	}

}

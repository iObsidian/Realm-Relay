package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;


public class PlayerHitPacket extends Packet {

	private int bulletId;
	private int objectId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		bulletId = in.readUnsignedByte();
		objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(bulletId);
		out.writeInt(objectId);
	}

}

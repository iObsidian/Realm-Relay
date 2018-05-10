package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game.messaging.data.WorldPosData;
import realmrelay.packets.Packet;

public class ServerPlayerShootPacket extends Packet {

	private int bulletId;
	private int ownerId;
	private int containerType;
	private WorldPosData startingLoc;
	private float angle;
	private short damage;

	public ServerPlayerShootPacket() {
		startingLoc = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		bulletId = in.readUnsignedByte();
		ownerId = in.readInt();
		containerType = in.readInt();
		startingLoc.parseFromInput(in);
		angle = in.readFloat();
		damage = in.readShort();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(bulletId);
		out.writeInt(ownerId);
		out.writeInt(containerType);
		startingLoc.writeToOutput(out);
		out.writeFloat(angle);
		out.writeShort(damage);
	}

}

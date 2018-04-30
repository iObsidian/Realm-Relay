package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class DamagePacket extends Packet {

	private int targetId;
	private int[] effects = new int[0];
	private int damage;
	private boolean killed;
	private int bulletId;
	private int objectId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		targetId = in.readInt();
		effects = new int[in.readUnsignedByte()];
		for (int i = 0; i < effects.length; i++) {
			effects[i] = in.readUnsignedByte();
		}
		damage = in.readUnsignedShort();
		killed = in.readBoolean();
		bulletId = in.readUnsignedByte();
		objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(targetId);
		out.writeByte(effects.length);
		for (int effect : effects) {
			out.writeByte(effect);
		}
		out.writeShort(damage);
		out.writeBoolean(killed);
		out.writeByte(bulletId);
		out.writeInt(objectId);
	}

}

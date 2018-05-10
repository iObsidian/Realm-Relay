package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game.messaging.data.WorldPosData;
import realmrelay.packets.Packet;

public class AoePacket extends Packet {

	public WorldPosData pos;
	public float radius;
	private int damage;
	private int effect;
	private float duration;
	private int origType;
	private int color;

	public AoePacket() {
		pos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		pos.parseFromInput(in);
		radius = in.readFloat();
		damage = in.readUnsignedShort();
		effect = in.readUnsignedByte();
		duration = in.readFloat();
		origType = in.readUnsignedShort();
		color = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		pos.writeToOutput(out);
		out.writeFloat(radius);
		out.writeShort(damage);
		out.writeByte(effect);
		out.writeFloat(duration);
		out.writeShort(origType);
		out.writeInt(color);
	}

}

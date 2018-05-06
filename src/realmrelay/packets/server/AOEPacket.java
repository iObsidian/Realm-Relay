package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.Location;
import realmrelay.packets.Packet;

public class AOEPacket extends Packet {

	public Location location = new Location();
	public float radius;
	private short damage;
	private byte effects;
	private float effectDuration;
	private short originType;
	private int color;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		location.parseFromInput(in);
		radius = in.readFloat();
		damage = in.readShort();
		effects = in.readByte();
		effectDuration = in.readFloat();
		originType = in.readShort();
		color = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		location.writeToOutput(out);
		out.writeFloat(radius);
		out.writeShort(damage);
		out.writeByte(effects);
		out.writeFloat(effectDuration);
		out.writeShort(originType);
		out.writeInt(color);
	}

}

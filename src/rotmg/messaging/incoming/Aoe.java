package rotmg.messaging.incoming;

import rotmg.messaging.data.WorldPosData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class Aoe extends IncomingMessage {

	public Aoe(int id, Consumer callback) {
		super(id, callback);
		pos = new WorldPosData();
	}

	public WorldPosData pos;
	public double radius;
	private int damage;
	private int effect;
	private double duration;
	private int origType;
	private int color;

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
		out.writeDouble(radius);
		out.writeShort(damage);
		out.writeByte(effect);
		out.writeDouble(duration);
		out.writeShort(origType);
		out.writeInt(color);
	}

}

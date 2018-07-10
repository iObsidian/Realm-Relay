package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Damage extends IncomingMessage {

	public int targetId;
	public int[] effects = new int[0];
	public int damageAmount;
	public boolean kill;
	public boolean armorPierce;
	public int bulletId;
	public int objectId;

	public Damage(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		targetId = in.readInt();
		effects = new int[in.readUnsignedByte()];
		for (int i = 0; i < effects.length; i++) {
			effects[i] = in.readUnsignedByte();
		}
		damageAmount = in.readUnsignedShort();
		kill = in.readBoolean();
		armorPierce = in.readBoolean();
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
		out.writeShort(damageAmount);
		out.writeBoolean(kill);
		out.writeBoolean(armorPierce);
		out.writeByte(bulletId);
		out.writeInt(objectId);
	}

}

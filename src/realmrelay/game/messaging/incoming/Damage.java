package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class Damage extends IncomingMessage {

	public Damage(int id, Consumer callback) {
		super(id, callback);
	}

	private int targetId;
	private int[] effects = new int[0];
	private int damageAmount;
	private boolean kill;
	private boolean armorPierce;
	private int bulletId;
	private int objectId;

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

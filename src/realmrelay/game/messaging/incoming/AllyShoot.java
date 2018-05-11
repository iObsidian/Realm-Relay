package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class AllyShoot extends IncomingMessage {

	public AllyShoot(int id, Consumer callback) {
		super(id, callback);
	}

	private int bulletId;
	public int ownerId;
	private short containerType;
	private float angle;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		bulletId = in.readUnsignedByte();
		ownerId = in.readInt();
		containerType = in.readShort();
		angle = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(bulletId);
		out.writeInt(ownerId);
		out.writeShort(containerType);
		out.writeFloat(angle);
	}

}

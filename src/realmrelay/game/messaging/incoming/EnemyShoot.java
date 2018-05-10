package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.data.WorldPosData;

public class EnemyShoot extends IncomingMessage {

	public EnemyShoot(int id, Consumer callback) {
		super(id, callback);
		startingPos = new WorldPosData();
	}

	private int bulletId;
	private int ownerId;
	private int bulletType;
	private WorldPosData startingPos;
	private float angle;
	private short damage;
	private int numShots;
	private float angleInc;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		bulletId = in.readUnsignedByte();
		ownerId = in.readInt();
		bulletType = in.readUnsignedByte();
		startingPos.parseFromInput(in);
		angle = in.readFloat();
		damage = in.readShort();

		try {
			numShots = in.readUnsignedByte();
			angleInc = in.readFloat();
		} catch (IOException e) {
			numShots = 1;
			angleInc = 0;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(bulletId);
		out.writeInt(ownerId);
		out.writeByte(bulletType);
		startingPos.writeToOutput(out);
		out.writeFloat(angle);
		out.writeShort(damage);
		if (numShots != 1) {
			out.writeByte(numShots);
			out.writeFloat(angleInc);
		}
	}

}

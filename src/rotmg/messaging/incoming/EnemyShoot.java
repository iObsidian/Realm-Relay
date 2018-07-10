package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.WorldPosData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EnemyShoot extends IncomingMessage {

	public int bulletId;
	public int ownerId;
	public int bulletType;
	public WorldPosData startingPos;
	public double angle;
	public short damage;
	public int numShots;
	public double angleInc;

	public EnemyShoot(int id, MessageConsumer callback) {
		super(id, callback);
		startingPos = new WorldPosData();
	}

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
		out.writeDouble(angle);
		out.writeShort(damage);
		if (numShots != 1) {
			out.writeByte(numShots);
			out.writeDouble(angleInc);
		}
	}

}

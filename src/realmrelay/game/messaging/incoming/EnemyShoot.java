package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game.messaging.data.WorldPosData;
import realmrelay.packets.Packet;

public class EnemyShoot extends Packet {

	private int bulletId;
	private int ownerId;
	private int bulletType;
	private WorldPosData startingPos;
	private float angle;
	private short damage;
	private int numShots;
	private float angleInc;

	public EnemyShoot() {
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
		out.writeFloat(angle);
		out.writeShort(damage);
		if (numShots != 1) {
			out.writeByte(numShots);
			out.writeFloat(angleInc);
		}
	}

}

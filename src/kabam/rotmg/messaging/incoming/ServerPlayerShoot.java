package kabam.rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.messaging.data.WorldPosData;

public class ServerPlayerShoot extends IncomingMessage {

	public int bulletId;
	public int ownerId;
	public int containerType;
	public WorldPosData startingPos;
	public double angle;
	public short damage;
	public ServerPlayerShoot(int id, Consumer callback) {
		super(id, callback);
		startingPos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		bulletId = in.readUnsignedByte();
		ownerId = in.readInt();
		containerType = in.readInt();
		startingPos.parseFromInput(in);
		angle = in.readFloat();
		damage = in.readShort();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(bulletId);
		out.writeInt(ownerId);
		out.writeInt(containerType);
		startingPos.writeToOutput(out);
		out.writeDouble(angle);
		out.writeShort(damage);
	}

}

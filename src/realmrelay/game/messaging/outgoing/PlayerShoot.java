package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.data.WorldPosData;

public class PlayerShoot extends OutgoingMessage {

	private int time;
	private int bulletId;
	private int containerType;
	private WorldPosData startingPos;
	private float angle;

	public PlayerShoot(int id, Consumer callback) {
		super(id, callback);
		startingPos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		bulletId = in.readUnsignedByte();
		containerType = in.readUnsignedShort();
		startingPos.parseFromInput(in);
		angle = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeByte(bulletId);
		out.writeShort(containerType);
		startingPos.writeToOutput(out);
		out.writeFloat(angle);
	}

}

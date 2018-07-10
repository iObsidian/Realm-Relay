package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.WorldPosData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PlayerShoot extends OutgoingMessage {

	public int time;
	public int bulletId;
	public int containerType;
	public WorldPosData startingPos;
	public double angle;

	public PlayerShoot(int id, MessageConsumer callback) {
		super(id, callback);
		startingPos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		bulletId = in.readUnsignedByte();
		containerType = in.readUnsignedShort();
		startingPos.parseFromInput(in);
		angle = in.readDouble();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeByte(bulletId);
		out.writeShort(containerType);
		startingPos.writeToOutput(out);
		out.writeDouble(angle);
	}

}

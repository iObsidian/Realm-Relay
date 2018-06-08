package rotmg.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class OtherHit extends OutgoingMessage {

	public int time;
	public int bulletId;
	public int objectId;
	public int targetId;

	public OtherHit(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		bulletId = in.readByte();
		objectId = in.readInt();
		targetId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeByte(bulletId);
		out.writeInt(objectId);
		out.writeInt(targetId);
	}

}

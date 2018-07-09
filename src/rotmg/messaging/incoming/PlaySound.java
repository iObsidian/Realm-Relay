package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class PlaySound extends IncomingMessage {

	public int ownerId;
	public int soundId;

	public PlaySound(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		ownerId = in.readInt();
		soundId = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(ownerId);
		out.writeByte(soundId);
	}

}

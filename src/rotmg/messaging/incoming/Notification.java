package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class Notification extends IncomingMessage {

	public Notification(int id, Consumer callback) {
		super(id, callback);
	}

	public int objectId;
	public String message;
	public int color;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectId = in.readInt();
		message = in.readUTF();
		color = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(objectId);
		out.writeUTF(message);
		out.writeInt(color);
	}

}

package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class CreateSuccess extends IncomingMessage {

	public CreateSuccess(int id, Consumer callback) {
		super(id, callback);
	}

	public int objectId;
	public int charId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectId = in.readInt();
		charId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(objectId);
		out.writeInt(charId);
	}

}

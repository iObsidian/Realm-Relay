package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class GlobalNotification extends IncomingMessage {

	public GlobalNotification(int id, Consumer callback) {
		super(id, callback);
	}

	private int type;
	private String text;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		type = in.readInt();
		text = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(type);
		out.writeUTF(text);
	}

}

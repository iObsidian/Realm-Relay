package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class ClientStat extends IncomingMessage {

	public ClientStat(int id, Consumer callback) {
		super(id, callback);
	}

	private String name;
	private int value;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		name = in.readUTF();
		value = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(value);
	}

}

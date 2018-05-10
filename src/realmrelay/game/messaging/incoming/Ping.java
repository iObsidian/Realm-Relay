package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class Ping extends IncomingMessage {

	public Ping(int id, Consumer callback) {
		super(id, callback);
	}

	public int serial;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		serial = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(serial);
	}

}

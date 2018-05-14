package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class GotoAck extends OutgoingMessage {

	public int time;

	public GotoAck(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
	}

}
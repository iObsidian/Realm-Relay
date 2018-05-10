package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class InvResult extends IncomingMessage {

	public InvResult(int id, Consumer callback) {
		super(id, callback);
	}

	public int result;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		result = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(result);
	}

}

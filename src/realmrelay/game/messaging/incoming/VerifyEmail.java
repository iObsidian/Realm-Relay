package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class VerifyEmail extends IncomingMessage {

	public VerifyEmail(int id, Consumer callback) {
		super(id, callback);
	}

	public void parseFromInput(DataInput in) throws IOException {
	}

	public void writeToOutput(DataOutput out) throws IOException {
	}
}
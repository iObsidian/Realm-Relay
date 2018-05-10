package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class NameResult extends IncomingMessage {

	public NameResult(int id, Consumer callback) {
		super(id, callback);
	}

	private boolean success;
	private String errorText;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		success = in.readBoolean();
		errorText = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeBoolean(success);
		out.writeUTF(errorText);
	}

}

package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class Failure extends IncomingMessage {

	public static final int INCORRECT_VERSION = 4;
	public static final int BAD_KEY = 5;
	public static final int INVALID_TELEPORT_TARGET = 6;
	public static final int EMAIL_VERIFICATION_NEEDED = 7;
	public static final int TELEPORT_REALM_BLOCK = 9;
	public int errorId;
	public String errorDescription;
	public Failure(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		errorId = in.readInt();
		errorDescription = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(errorId);
		out.writeUTF(errorDescription);
	}

}

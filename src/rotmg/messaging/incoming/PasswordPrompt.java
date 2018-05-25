package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class PasswordPrompt extends IncomingMessage {

	public final int SIGN_IN = 2;
	public final int SEND_EMAIL_AND_SIGN_IN = 3;
	public final int REGISTER = 4;

	public PasswordPrompt(int id, Consumer callback) {
		super(id, callback);
	}

	private int cleanPasswordStatus;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		cleanPasswordStatus = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(cleanPasswordStatus);
	}

}

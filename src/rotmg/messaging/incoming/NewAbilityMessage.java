package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class NewAbilityMessage extends IncomingMessage {

	public NewAbilityMessage(int id, Consumer callback) {
		super(id, callback);
	}

	private int type;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		type = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(type);
	}

}

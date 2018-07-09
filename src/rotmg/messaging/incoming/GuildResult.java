package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class GuildResult extends IncomingMessage {

	public boolean success;
	public String lineBuilderJSON;
	public GuildResult(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		success = in.readBoolean();
		lineBuilderJSON = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeBoolean(success);
		out.writeUTF(lineBuilderJSON);
	}

}

package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class KeyInfoResponse extends IncomingMessage {

	public KeyInfoResponse(int id, Consumer callback) {
		super(id, callback);
	}

	public String name;
	public String description;
	public String creator;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.description = in.readUTF();
		this.creator = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(description);
		out.writeUTF(creator);
	}

}

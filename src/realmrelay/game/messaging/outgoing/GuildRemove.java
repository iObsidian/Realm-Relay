package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class GuildRemove extends OutgoingMessage {

	public String name;

	public GuildRemove(int id, Consumer callback) {
		super(id, callback);
	}
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
		name = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
	}

}

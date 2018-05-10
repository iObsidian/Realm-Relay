package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class InvitedToGuild extends IncomingMessage {

	public InvitedToGuild(int id, Consumer callback) {
		super(id, callback);
	}

	public String name;
	public String guildName;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.guildName = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(guildName);
	}

}

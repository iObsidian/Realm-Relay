package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class Load extends OutgoingMessage {

	public int charId;
	public boolean isFromArena;

	public Load(int id, Consumer callback) {
		super(id, callback);
	}
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
		charId = in.readInt();
		isFromArena = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(charId);
		out.writeBoolean(isFromArena);
	}

}

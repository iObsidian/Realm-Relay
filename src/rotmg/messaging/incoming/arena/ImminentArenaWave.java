package rotmg.messaging.incoming.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.game.messaging.outgoing.OutgoingMessage;
import rotmg.messaging.outgoing.OutgoingMessage;

public class ImminentArenaWave extends OutgoingMessage {

	private int currentRuntime;

	public ImminentArenaWave(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		currentRuntime = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(currentRuntime);
	}

}

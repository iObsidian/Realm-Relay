package kabam.rotmg.messaging.incoming.arena;

import kabam.rotmg.messaging.outgoing.OutgoingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

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

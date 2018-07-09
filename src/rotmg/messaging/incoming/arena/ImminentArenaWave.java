package rotmg.messaging.incoming.arena;

import kabam.rotmg.messaging.outgoing.OutgoingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

<<<<<<< HEAD:src/kabam/rotmg/messaging/incoming/arena/ImminentArenaWave.java
=======
import rotmg.messaging.outgoing.OutgoingMessage;

>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/messaging/incoming/arena/ImminentArenaWave.java
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

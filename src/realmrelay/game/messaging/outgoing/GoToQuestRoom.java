package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class GoToQuestRoom extends OutgoingMessage {

	public GoToQuestRoom(int id, Consumer callback) {
		super(id, callback);
	}
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
	}

}

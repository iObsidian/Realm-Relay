package rotmg.messaging.outgoing.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.game.messaging.outgoing.OutgoingMessage;

public class EnterArena extends OutgoingMessage {

	private int currency;

	public EnterArena(int id, Consumer callback) {
		super(id, callback);
	}
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
		currency = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(currency);
	}

}

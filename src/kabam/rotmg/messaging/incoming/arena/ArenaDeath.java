package kabam.rotmg.messaging.incoming.arena;

import kabam.rotmg.messaging.outgoing.OutgoingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class ArenaDeath extends OutgoingMessage {

	private int cost;

	public ArenaDeath(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		cost = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(cost);
	}

}

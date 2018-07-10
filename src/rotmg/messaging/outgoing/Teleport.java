package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Teleport extends OutgoingMessage {

	public int objectId;

	public Teleport(int id, MessageConsumer callback) {
		super(id, callback);
	}


	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(objectId);
	}

}

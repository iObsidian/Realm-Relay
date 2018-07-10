package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class KeyInfoRequest extends OutgoingMessage {

	private int itemType;

	public KeyInfoRequest(int id, MessageConsumer callback) {
		super(id, callback);
	}


	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.itemType = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.itemType);
	}

}

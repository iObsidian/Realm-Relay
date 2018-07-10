package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ReskinUnlock extends IncomingMessage {

	private int skinID;

	public ReskinUnlock(int id, MessageConsumer callback) {
		super(id, callback);
	}

	public void parseFromInput(DataInput in) throws IOException {
		this.skinID = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.skinID);
	}
}

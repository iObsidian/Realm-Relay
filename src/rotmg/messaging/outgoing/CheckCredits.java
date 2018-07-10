package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CheckCredits extends OutgoingMessage {

	public CheckCredits(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

	}

}

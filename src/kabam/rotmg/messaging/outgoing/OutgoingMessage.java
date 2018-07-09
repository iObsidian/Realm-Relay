package kabam.rotmg.messaging.outgoing;

import kabam.rotmg.net.impl.Message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class OutgoingMessage extends Message {

	public OutgoingMessage(int param1, Consumer param2) {
		super(param1, param2);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

	}
}

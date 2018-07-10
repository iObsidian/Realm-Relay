package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Create extends OutgoingMessage {

	public int classType;
	public int skinType;

	public Create(int param1, MessageConsumer param2) {
		super(param1, param2);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		classType = in.readUnsignedShort();
		skinType = in.readUnsignedShort();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(classType);
		out.writeShort(skinType);
	}

}

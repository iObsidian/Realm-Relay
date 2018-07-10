package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ActivePetUpdateRequest extends OutgoingMessage {

	private int commandtype;
	private int instanceid;

	public ActivePetUpdateRequest(int id, MessageConsumer callback) {
		super(id, callback);
	}

	public void parseFromInput(DataInput in) throws IOException {
		commandtype = in.readByte();
		instanceid = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(commandtype);
		out.writeByte(instanceid);
	}
}

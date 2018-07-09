package kabam.rotmg.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class ActivePetUpdateRequest extends OutgoingMessage {

	private int commandtype;
	private int instanceid;

	public ActivePetUpdateRequest(int id, Consumer callback) {
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

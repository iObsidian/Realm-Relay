package kabam.rotmg.messaging.incoming.pets;

import kabam.rotmg.messaging.outgoing.OutgoingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class DeletePetMessage extends OutgoingMessage {

	int petID;

	public DeletePetMessage(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petID = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(petID);
	}

}

package rotmg.messaging.incoming.pets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

<<<<<<< HEAD:src/rotmg/messaging/incoming/pets/DeletePetMessage.java
<<<<<<< HEAD:src/kabam/rotmg/messaging/incoming/pets/DeletePetMessage.java
=======
import rotmg.messaging.outgoing.OutgoingMessage;

>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/messaging/incoming/pets/DeletePetMessage.java
=======
import kabam.rotmg.messaging.outgoing.OutgoingMessage;
import rotmg.messaging.outgoing.OutgoingMessage;

>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/messaging/incoming/pets/DeletePetMessage.java
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

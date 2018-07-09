package rotmg.messaging.incoming.pets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

<<<<<<< HEAD:src/rotmg/messaging/incoming/pets/Hatch_PetMessage.java
<<<<<<< HEAD:src/kabam/rotmg/messaging/incoming/pets/Hatch_PetMessage.java
=======
import rotmg.messaging.outgoing.OutgoingMessage;

>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/messaging/incoming/pets/Hatch_PetMessage.java
=======
import kabam.rotmg.messaging.outgoing.OutgoingMessage;
import rotmg.messaging.outgoing.OutgoingMessage;

>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/messaging/incoming/pets/Hatch_PetMessage.java
public class Hatch_PetMessage extends OutgoingMessage {

	private String petName;
	private int petSkinId;

	public Hatch_PetMessage(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petName = in.readUTF();
		petSkinId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(petName);
		out.writeInt(petSkinId);

	}

}

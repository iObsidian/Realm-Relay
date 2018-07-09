package rotmg.messaging.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

<<<<<<< HEAD:src/rotmg/messaging/impl/PetYard.java
<<<<<<< HEAD:src/kabam/rotmg/messaging/impl/PetYard.java
=======
import rotmg.messaging.incoming.IncomingMessage;

>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/messaging/impl/PetYard.java
=======
import kabam.rotmg.messaging.incoming.IncomingMessage;
import rotmg.messaging.incoming.IncomingMessage;

>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/messaging/impl/PetYard.java
public class PetYard extends IncomingMessage {

	public int type;

	public PetYard(int param1, Consumer param2) {
		super(param1, param2);
	}

	public void parseFromInput(DataInput in) throws IOException {
		this.type = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(type);
	}

}

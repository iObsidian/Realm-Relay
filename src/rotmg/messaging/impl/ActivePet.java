package rotmg.messaging.impl;

import kabam.rotmg.messaging.incoming.IncomingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

<<<<<<< HEAD:src/kabam/rotmg/messaging/impl/ActivePet.java
=======
import rotmg.messaging.incoming.IncomingMessage;

>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/messaging/impl/ActivePet.java
public class ActivePet extends IncomingMessage {

	public int instanceID;

	public ActivePet(int param1, Consumer param2) {
		super(param1, param2);
	}

	public void parseFromInput(DataInput in) throws IOException {
		this.instanceID = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(instanceID);
	}
}

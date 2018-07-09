package rotmg.messaging.impl;

import kabam.rotmg.messaging.incoming.IncomingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

<<<<<<< HEAD:src/kabam/rotmg/messaging/impl/HatchPetMessage.java
=======
import rotmg.messaging.incoming.IncomingMessage;

>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/messaging/impl/HatchPetMessage.java
public class HatchPetMessage extends IncomingMessage {

	public String petName;
	public int petSkin;

	public HatchPetMessage(int param1, Consumer param2) {
		super(param1, param2);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.petName = in.readUTF();
		this.petSkin = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(petName);
		out.writeInt(petSkin);
	}
}

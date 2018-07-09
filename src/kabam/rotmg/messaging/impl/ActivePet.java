package kabam.rotmg.messaging.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import kabam.rotmg.messaging.incoming.IncomingMessage;
import rotmg.messaging.incoming.IncomingMessage;

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

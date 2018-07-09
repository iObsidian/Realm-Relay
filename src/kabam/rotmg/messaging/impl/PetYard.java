package kabam.rotmg.messaging.impl;

import kabam.rotmg.messaging.incoming.IncomingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

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

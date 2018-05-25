package rotmg.messaging.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.game.messaging.incoming.IncomingMessage;
import rotmg.messaging.incoming.IncomingMessage;

public class PetYard extends IncomingMessage {

	public PetYard(int param1, Consumer param2) {
		super(param1, param2);
	}

	public int type;

	public void parseFromInput(DataInput in) throws IOException {
		this.type = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(type);
	}

}
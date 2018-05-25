package rotmg.messaging.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.game.messaging.incoming.IncomingMessage;
import rotmg.messaging.incoming.IncomingMessage;

public class HatchPetMessage extends IncomingMessage {

	public HatchPetMessage(int param1, Consumer param2) {
		super(param1, param2);
	}

	public String petName;
	public int petSkin;

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
package realmrelay.game.messaging.impl;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.incoming.IncomingMessage;

public class HatchPetMessage extends IncomingMessage {

	public HatchPetMessage(int param1, Consumer param2) {
		super(param1, param2);
	}

	public String petName;
	public int petSkin;

	@Override
	public void parseFromInput(DataInput param1) throws IOException {
		this.petName = param1.readUTF();
		this.petSkin = param1.readInt();
	}
}

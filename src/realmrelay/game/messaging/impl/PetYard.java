package realmrelay.game.messaging.impl;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.incoming.IncomingMessage;

public class PetYard extends IncomingMessage {

	public PetYard(int param1, Consumer param2) {
		super(param1, param2);
	}

	public int type;

	public void parseFromInput(DataInput param1) throws IOException {
		this.type = param1.readInt();
	}

}

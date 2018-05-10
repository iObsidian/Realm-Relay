package realmrelay.game.messaging.impl;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.incoming.IncomingMessage;

public class ActivePet extends IncomingMessage {

	public ActivePet(int param1, Consumer param2) {
		super(param1, param2);
	}

	public int instanceID;

	public void parseFromInput(DataInput param1) throws IOException {
		this.instanceID = param1.readInt();
	}
}

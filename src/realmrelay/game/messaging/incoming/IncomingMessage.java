package realmrelay.game.messaging.incoming;

import java.util.function.Consumer;

import realmrelay.game.net.impl.Message;

public abstract class IncomingMessage extends Message {

	public IncomingMessage(int param1, Consumer param2) {
		super(param1, param2);
	}

}

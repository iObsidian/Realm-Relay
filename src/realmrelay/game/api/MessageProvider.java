package realmrelay.game.api;

import realmrelay.game.net.impl.Message;

public interface MessageProvider {

	Message require(int param1);
	
}

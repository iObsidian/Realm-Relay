package kabam.lib.net.api;

import realmrelay.game.net.api.MessageMapping;

public interface MessageMap {

	MessageMapping map(int param1);

	void unmap(int param1);
}


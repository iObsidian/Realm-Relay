package rotmg.net.api;

import rotmg.game.net.api.MessageMapping;

public interface MessageMap {

	MessageMapping map(int param1);

	void unmap(int param1);
}


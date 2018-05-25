package rotmg.api;

import rotmg.game.net.impl.Message;

public interface MessageProvider {

	Message require(int param1);
	
}

package realmrelay.game.net.impl;

import java.util.ArrayList;
import java.util.List;

import realmrelay.game.api.MessageProvider;
import realmrelay.game.net.api.MessageMap;
import realmrelay.game.net.api.MessageMapping;

/**
 * The implementation of MessageProvider 
 * used by GameServerConnectionConcrete 
 * to map packets to functions and methods
 */
public class MessageCenter implements MessageProvider, MessageMap {

	private static MessageCenter instance;

	private static final int MAX_ID = 256;
	private final List<MessageCenterMapping> maps = new ArrayList<MessageCenterMapping>(MAX_ID);
	private final List<MessagePool> pools = new ArrayList<MessagePool>(MAX_ID);

	@Override
	public Message require(int param1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageMapping map(int param1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unmap(int param1) {
		// TODO Auto-generated method stub

	}

	// "Injector"
	public static MessageCenter getInstance() {
		if (instance == null) {
			instance = new MessageCenter();
		}
		return instance;
	}

}

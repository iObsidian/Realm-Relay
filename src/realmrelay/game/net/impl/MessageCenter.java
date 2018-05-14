package realmrelay.game.net.impl;

import realmrelay.game.api.MessageProvider;
import realmrelay.game.net.api.MessageMap;
import realmrelay.game.net.api.MessageMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of MessageProvider
 * used by GameServerConnectionConcrete
 * to map packets to functions and methods
 */
public class MessageCenter implements MessageProvider, MessageMap {

	private static MessageCenter instance;

	// "Injector"
	public static MessageCenter getInstance() {
		if (instance == null) {
			instance = new MessageCenter();
		}
		return instance;
	}


	private static final int MAX_ID = 256;
	private final List<MessageCenterMapping> maps = new ArrayList<MessageCenterMapping>(MAX_ID);
	private final List<MessagePool> pools = new ArrayList<MessagePool>(MAX_ID);


	@Override
	public MessageMapping map(int param1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unmap(int param1) {
		// TODO Auto-generated method stub
	}


	@Override
	public Message require(int param1) {
		MessagePool loc2;

		if (this.pools.get(param1) != null) {
			loc2 = this.pools.get(param1);
		} else {
			loc2 = this.makePool(param1);
		}

		return loc2.require();
	}

	private MessagePool makePool(int param1) {
		MessageCenterMapping loc2 = this.maps.get(param1);

		if (loc2 == null) {
			System.err.println("Error : null messageCenterMapping!");
			return null;
		}

		return loc2.makePool();
	}


}

package realmrelay.game.net.impl;


import realmrelay.game.net.api.MessageMapping;
import realmrelay.game.net.api.MessageProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageCenter implements kabam.lib.net.api.MessageMap, MessageProvider {

	private static final int MAXID = 256;

	private final HashMap<Integer, MessageCenterMapping> maps = new HashMap<Integer, MessageCenterMapping>(MAXID);

	private final HashMap<Integer, MessagePool> pools = new HashMap<>(MAXID);

	public MessageCenter() {
		super();
	}

	public MessageMapping map(int param1) {
		if (this.maps.get(param1) == null ) {
			this.maps.put(param1, this.makeMapping(param1));
		}
		return this.maps.get(param1);
	}

	public void unmap(int param1) {
		this.pools.get(param1) && this.pools[param1].dispose();
		this.pools.put(param1, null);
		this.maps.put(param1, null);
	}

	private MessageCenterMapping makeMapping(int param1) {
		return (MessageCenterMapping) new MessageCenterMapping().setID(param1);
	}

	@Override
	public Message require(int param1) {
		if (this.pools.get(param1) == null) {
			this.pools.put(param1, this.makePool(param1));
		}

		MessagePool loc2 =  this.pools.get(param1);
		return loc2.require();
	}

	private MessagePool makePool(int param1) {
		MessageCenterMapping loc2 = this.maps.get(param1);
		return (loc2 != null) ? loc2.makePool() : null;
	}
}
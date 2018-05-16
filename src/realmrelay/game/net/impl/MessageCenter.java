package kabam.lib.net.impl;
//import kabam.lib.net.api.MessageMap;
//import kabam.lib.net.api.MessageMapping;
//import kabam.lib.net.api.MessageProvider;

//import org.swiftsuspenders.Injector;

import realmrelay.game.net.api.MessageMap;
import realmrelay.game.net.api.MessageMapping;
import realmrelay.game.net.api.MessageProvider;
import realmrelay.game.net.impl.Message;
import realmrelay.game.net.impl.MessageCenterMapping;
import realmrelay.game.net.impl.MessagePool;

import java.util.List;

public class MessageCenter implements MessageMap, MessageProvider {

	private static final int MAXID = 256;

	private final List<MessageCenterMapping> maps = new Arraylist<MessageCenterMapping>(MAXID, true);

	private final List<MessagePool> pools = new Arraylist<MessagePool>(MAXID, true);

	public MessageCenter() {
		super();
	}

	public MessageMapping map(int param1) {
		return this.maps[param1] = this.maps[param1] || this.makeMapping(param1);
	}

	public void unmap(int param1) {
		this.pools[param1] && this.pools[param1].dispose();
		this.pools[param1] = null;
		this.maps[param1] = null;
	}

	private MessageCenterMapping makeMapping(int param1) {
		return (MessageCenterMapping) new MessageCenterMapping().setID(param1);
	}

	public Message require(int param1) {
		MessagePool loc2 = this.pools[param1] = this.pools[param1] || this.makePool(param1);
		return loc2.require();
	}

	private MessagePool makePool(int param1) {
		MessageCenterMapping loc2 = this.maps[param1];
		return !!loc2 ? loc2.makePool() : null;
	}
}
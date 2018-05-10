package realmrelay.game.net;

import realmrelay.crypto.RC4;
import realmrelay.game.api.MessageProvider;
import realmrelay.game.net.impl.Message;
import realmrelay.game.net.impl.MessageCenter;

/**
 * This class is a very loose implementation of WildShadow's SocketServer,
 * it is more closely related to The Force 2477's RealmClient
 */
public class SocketServer {

	public static SocketServer instance;

	public static SocketServer getInstance() {
		if (instance == null) {
			instance = new SocketServer();
		}

		return instance;
	}

	public MessageProvider messages = MessageCenter.getInstance();

	private RC4 outgoingCipher; //Renamed from 'ICipher'.
	private RC4 incomingCipher;

	private String server;
	private int port;

	public SocketServer setOutgoingCipher(RC4 param1) {
		this.outgoingCipher = param1;
		return this;
	}

	public SocketServer setIncomingCipher(RC4 param1) {
		this.incomingCipher = param1;
		return this;
	}

	public void connect(String param1, int param2) {
		this.server = param1;
		this.port = param2;

	}

	public void sendMessage(Message message) {

	}

}

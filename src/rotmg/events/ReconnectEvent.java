package rotmg.events;

import flash.events.Event;
import rotmg.net.Server;

public class ReconnectEvent extends Event {

	public static final String RECONNECT = "RECONNECT_EVENT";

	public Server server;

	public int gameId;

	public boolean createCharacter;

	public int charId;

	public int keyTime;

	public byte[] key;

	public boolean isFromArena;

	public ReconnectEvent(Server param1, int param2, boolean param3, int param4, int param5, byte[] param6, boolean param7) {
		super(RECONNECT);
		this.server = param1;
		this.gameId = param2;
		this.createCharacter = param3;
		this.charId = param4;
		this.keyTime = param5;
		this.key = param6;
		this.isFromArena = param7;
	}

	@Override
	public Event clone() {
		return new ReconnectEvent(this.server, this.gameId, this.createCharacter, this.charId, this.keyTime, this.key, this.isFromArena);
	}

}

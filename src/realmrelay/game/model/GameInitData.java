package realmrelay.game.model;

import realmrelay.game.net.Server;

public class GameInitData {

	public Server server;

	public int gameId;

	public boolean createCharacter;

	public int charId;

	public int keyTime;

	public byte[] key;

	public boolean isNewGame;

	public boolean isFromArena;

	public GameInitData() {
		super();
	}

}
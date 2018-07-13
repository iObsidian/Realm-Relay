package rotmg.model;

import rotmg.net.Server;

public class GameInitData {

	public static GameInitData instance;
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

	public static GameInitData getInstance() {
		if (instance == null) {
			instance = new GameInitData();
		}
		return instance;
	}

}

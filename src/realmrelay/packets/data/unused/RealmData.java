package realmrelay.packets.data.unused;

import realmrelay.game.messaging.data.WorldPosData;

final class RealmData {

	public String IP = "";
	public String PORT = "";

	public RealmData(int objectId, String name, WorldPosData loc, String population, String server) {
		String population1 = population;
		int objectId1 = objectId;
		WorldPosData loc1 = loc;
		String name1 = name;
		String server1 = server;
	}

}

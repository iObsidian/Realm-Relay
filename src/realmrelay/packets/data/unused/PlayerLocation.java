package realmrelay.packets.data.unused;

import java.util.ArrayList;

import realmrelay.packets.data.WorldPosData;

final class PlayerWorldPosData {

	private String name;
	private int id;
	private WorldPosData loc;

	public PlayerWorldPosData(String name, int id, WorldPosData loc) {
		this.name = name;
		this.id = id;
		this.loc = loc;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public WorldPosData getWorldPosData() {
		return loc;
	}

	public WorldPosData getAverage(ArrayList<PlayerWorldPosData> players){

		int sumx = 0;
		int sumy = 0;

		for (PlayerWorldPosData s : players){
			sumx += s.loc.x;
			sumy += s.loc.y;
		}

		WorldPosData toGo = new WorldPosData();

		toGo.x = (sumx/players.size());
		toGo.y = (sumy/players.size());

		//log.info("Average : " + toGo.x +"x "+ toGo.y + "y");

		return toGo;

	}


	public int getNearestLoc(ArrayList<PlayerWorldPosData> players, WorldPosData toGo){

		WorldPosData lastDist = new WorldPosData(10000,10000);
		int objectIdTogo = 0;

		for (PlayerWorldPosData s : players){
			if (s.loc.distanceSquaredTo(toGo) < lastDist.distanceSquaredTo(toGo)){
				lastDist = s.loc;
				objectIdTogo = s.id;
			}
		}

		return objectIdTogo;
	}

}

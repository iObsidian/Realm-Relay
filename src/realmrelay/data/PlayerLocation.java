package realmrelay.data;

import java.util.ArrayList;

final class PlayerLocation {

	private String name;
	private int id;
	private Location loc;

	public PlayerLocation(String name, int id, Location loc) {
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

	public Location getLocation() {
		return loc;
	}

	public Location getAverage(ArrayList<PlayerLocation> players){

		int sumx = 0;
		int sumy = 0;

		for (PlayerLocation s : players){
			sumx += s.loc.x;
			sumy += s.loc.y;
		}

		Location toGo = new Location();

		toGo.x = (sumx/players.size());
		toGo.y = (sumy/players.size());

		//log.info("Average : " + toGo.x +"x "+ toGo.y + "y");

		return toGo;

	}


	public int getNearestLoc(ArrayList<PlayerLocation> players, Location toGo){

		Location lastDist = new Location(10000,10000);
		int objectIdTogo = 0;

		for (PlayerLocation s : players){
			if (s.loc.distanceSquaredTo(toGo) < lastDist.distanceSquaredTo(toGo)){
				lastDist = s.loc;
				objectIdTogo = s.id;
			}
		}

		return objectIdTogo;
	}

}

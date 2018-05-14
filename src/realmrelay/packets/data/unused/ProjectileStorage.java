package realmrelay.packets.data.unused;

import realmrelay.game.messaging.data.WorldPosData;

class ProjectileStorage {

	public ProjectileStorage(WorldPosData pos, double angle, double frequency, double amplitude, int speed, int lifetime,
			int starttime, int bulletId) {
		WorldPosData pos1 = pos;
		double angle1 = angle;
		double frequency1 = frequency;
		double amplitude1 = amplitude;
		int speed1 = speed;
		int lifetime1 = lifetime;
		int starttime1 = starttime;
		int bulletId1 = bulletId;
		System.err.println("Built a new ProjectileStorage");
	}
}

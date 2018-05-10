package realmrelay.packets.data.unused;

import realmrelay.game.messaging.data.WorldPosData;

class ProjectileStorage {

	public ProjectileStorage(WorldPosData pos, float angle, float frequency, float amplitude, int speed, int lifetime,
			int starttime, int bulletId) {
		WorldPosData pos1 = pos;
		float angle1 = angle;
		float frequency1 = frequency;
		float amplitude1 = amplitude;
		int speed1 = speed;
		int lifetime1 = lifetime;
		int starttime1 = starttime;
		int bulletId1 = bulletId;
		System.err.println("Built a new ProjectileStorage");
	}
}

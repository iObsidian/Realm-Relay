package realmrelay.game.arena.model;

import realmrelay.game.pets.data.PetVO;
import realmrelay.packets.data.unused.BitmapData;

import java.util.List;

/**
 * 100% match
 */
public class ArenaLeaderboardEntry {

	public BitmapData playerBitmap;

	public String name;

	public PetVO pet;

	public int[] slotTypes;

	public int[] equipment;

	public double runtime;

	public int currentWave;

	public String guildName;

	public int guildRank;

	public int rank = -1;

	public boolean isPersonalRecord = false;

	public ArenaLeaderboardEntry() {
		super();
	}

	public boolean isEqual(ArenaLeaderboardEntry param1) {
		return param1.name.equals(this.name) && this.runtime == param1.runtime && this.currentWave == param1.currentWave;
	}

	public boolean isBetterThan(ArenaLeaderboardEntry param1) {
		return this.currentWave > param1.currentWave || this.currentWave == param1.currentWave && this.runtime < param1.runtime;
	}

}

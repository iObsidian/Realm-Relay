package rotmg.arena.model;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import rotmg.pets.data.PetVO;

/**
 * 100% match
 */
public class ArenaLeaderboardEntry {

	public BitmapData playerBitmap;

	public String name;

	public PetVO pet;

	public Vector<Integer> slotTypes;

	public Vector<Integer> equipment;

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

package rotmg.objects;

import alde.flash.utils.Dictionary;
import rotmg.map.Map;
import rotmg.messaging.incoming.AccountList;
import rotmg.util.PointUtil;

public class Party {

	public static final int NUM_MEMBERS = 6;

	private static final Array SORT_ON_FIELDS = ["starred_","distSqFromThisPlayer_","objectId_"];

	private static final Array SORT_ON_PARAMS = [Array.NUMERIC |Array.DESCENDING,Array.NUMERIC,Array.NUMERIC];

	private static final int PARTY_DISTANCE_SQ = 50 * 50;

	public Map map;

	public Array members;

	private Dictionary starred;

	private Dictionary ignored;

	private int lastUpdate = -2147483648;

	public Party(Map param1) {
		this.members = [];
		this.starred = new Dictionary(true);
		this.ignored = new Dictionary(true);
		super();
		this.map = param1;
	}

	public void update(int param1, int param2) {
		GameObject loc4 = null;
		Player loc5 = null;
		if (param1 < this.lastUpdate + 500) {
			return;
		}
		this.lastUpdate = param1;
		this.members.length = 0;
		Player loc3 = this.map.player;
		if (loc3 == null) {
			return;
		}
		for (loc4:
		     this.map.goDict) {
			loc5 = (Player) loc4;
			if (!(loc5 == null || loc5 == loc3)) {
				loc5.starred = this.starred[loc5.accountId] != undefined;
				loc5.ignored = this.ignored[loc5.accountId] != undefined;
				loc5.distSqFromThisPlayer = PointUtil.distanceSquaredXY(loc3.x, loc3.y, loc5.x, loc5.y);
				if (!(loc5.distSqFromThisPlayer > PARTY_DISTANCE_SQ && !loc5.starred)) {
					this.members.add(loc5);
				}
			}
		}
		this.members.sortOn(SORT_ON_FIELDS, SORT_ON_PARAMS);
		if (this.members.length > NUM_MEMBERS) {
			this.members.length = NUM_MEMBERS;
		}
	}

	public void lockPlayer(Player param1) {
		this.starred[param1.accountId] = 1;
		this.lastUpdate = int.MIN_VALUE;
		this.map.gs.gsc.editAccountList(0, true, param1.objectId);
	}

	public void unlockPlayer(Player param1) {
		delete this.starred[param1.accountId];
		param1.starred = false;
		this.lastUpdate = int.MIN_VALUE;
		this.map.gs.gsc.editAccountList(0, false, param1.objectId);
	}

	public void setStars(AccountList param1) {
		String loc3 = null;
		int loc2 = 0;
		while (loc2 < param1.accountIds.length) {
			loc3 = param1.accountIds[loc2];
			this.starred[loc3] = 1;
			this.lastUpdate = int.MIN_VALUE;
			loc2++;
		}
	}

	public void removeStars(AccountList param1) {
		String loc3 = null;
		int loc2 = 0;
		while (loc2 < param1.accountIds.length) {
			loc3 = param1.accountIds[loc2];
			delete this.starred[loc3];
			this.lastUpdate = int.MIN_VALUE;
			loc2++;
		}
	}

	public void ignorePlayer(Player param1) {
		this.ignored[param1.accountId] = 1;
		this.lastUpdate = int.MIN_VALUE;
		this.map.gs.gsc.editAccountList(1, true, param1.objectId);
	}

	public void unignorePlayer(Player param1) {
		delete this.ignored[param1.accountId];
		param1.ignored = false;
		this.lastUpdate = int.MIN_VALUE;
		this.map.gs.gsc.editAccountList(1, false, param1.objectId);
	}

	public void setIgnores(AccountList param1) {
		String loc3 = null;
		this.ignored = new Dictionary(true);
		int loc2 = 0;
		while (loc2 < param1.accountIds.length) {
			loc3 = param1.accountIds[loc2];
			this.ignored[loc3] = 1;
			this.lastUpdate = int.MIN_VALUE;
			loc2++;
		}
	}


}

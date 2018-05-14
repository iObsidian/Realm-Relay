package realmrelay.game.appengine;

import realmrelay.game._as3.XML;
import realmrelay.game.util.FameUtil;

public class CharacterStats {

	public XML charStatsXML;

	public CharacterStats(XML param1) {
		super();
		this.charStatsXML = param1;
	}

	public int bestLevel() {
		return this.charStatsXML.getIntValue("BestLevel");
	}

	public int bestFame() {
		return this.charStatsXML.getIntValue("BestFame");
	}

	public int numStars() {
		return FameUtil.numStars(this.charStatsXML.getIntValue("BestFame"));
	}

}

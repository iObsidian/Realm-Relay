package rotmg.appengine;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import flash.events.Event;
import flash.utils.Dictionary;
import rotmg.account.core.Account;
import rotmg.account.core.WebAccount;
import rotmg.dialogs.OpenDialogSignal;
import rotmg.net.LatLong;
import rotmg.objects.ObjectLibrary;
import rotmg.objects.Player;
import rotmg.promotions.model.BeginnersPackageModel;
import rotmg.ui.TOSPopup;

public class SavedCharactersList extends Event {

	public static final String SAVED_CHARS_LIST = "SAVED_CHARS_LIST";

	public static final String AVAILABLE = "available";

	public static final String UNAVAILABLE = "unavailable";

	public static final String UNRESTRICTED = "unrestricted";

	private static final LatLong DEFAULT_LATLONG = new LatLong(37.4436, -122.412);

	private static final String DEFAULT_SALESFORCE = "unavailable";
	public String accountId;
	public int nextCharId;
	public int maxNumChars;
	public int numChars = 0;
	public Vector<SavedCharacter> savedChars;
	public Dictionary<Integer, CharacterStats> charStats;
	public int totalFame = 0;
	public int bestCharFame = 0;
	public int fame = 0;
	public int credits = 0;
	public int tokens = 0;
	public int numStars = 0;
	public int nextCharSlotPrice;
	public String guildName;
	public int guildRank;
	public String name = null;
	public boolean nameChosen;
	public boolean converted;
	public boolean isAdmin;
	public boolean canMapEdit;
	public Vector<SavedNewsItem> news;
	public LatLong myPos;
	public String salesForceData = "unavailable";
	public boolean hasPlayerDied = false;
	public Dictionary<Integer, String> classAvailability;
	public boolean isAgeVerified;
	private String origData;
	private XML charsXML;
	private Account account;

	public SavedCharactersList(String param1) {
		super(SAVED_CHARS_LIST);

		Account loc5 = null;
		this.savedChars = new Vector<SavedCharacter>();
		this.charStats = new Dictionary<>();
		this.news = new Vector<SavedNewsItem>();
		this.origData = param1;
		this.charsXML = new XML(this.origData);
		XML loc2 = this.charsXML.child("Account");
		this.parseUserData(loc2);
		this.parseBeginnersPackageData(loc2);
		this.parseGuildData(loc2);
		this.parseCharacterData();
		this.parseCharacterStatsData();
		this.parseNewsData();
		this.parseGeoPositioningData();
		this.parseSalesForceData();
		this.parseTOSPopup();
		this.reportUnlocked();

		loc5 = WebAccount.getInstance();
		loc5.reportIntStat("BestLevel", this.bestOverallLevel());
		loc5.reportIntStat("BestFame", this.bestOverallFame());
		loc5.reportIntStat("NumStars", this.numStars);
		loc5.verify(loc2.hasOwnProperty("VerifiedEmail"));

		this.classAvailability = new Dictionary<>();
		for (XML loc4 : this.charsXML.child("ClassAvailabilityList").children("ClassAvailability")) {
			this.classAvailability.put(loc4.getIntAttribute("id"), loc4.toString());
		}
	}

	public SavedCharacter getCharById(int param1) {
		for (SavedCharacter loc2 : this.savedChars) {
			if (loc2.charId() == param1) {
				return loc2;
			}
		}
		return null;
	}

	private void parseUserData(XML param1) {
		this.accountId = param1.getValue("AccountId");
		this.name = param1.getValue("Name");
		this.nameChosen = param1.hasOwnProperty("NameChosen");
		this.converted = param1.hasOwnProperty("Converted");
		this.isAdmin = param1.hasOwnProperty("Admin");
		Player.isAdmin = this.isAdmin;
		Player.isMod = param1.hasOwnProperty("Mod");
		this.canMapEdit = param1.hasOwnProperty("MapEditor");
		this.totalFame = param1.child("Stats").getIntValue("TotalFame");
		this.bestCharFame = param1.child("Stats").getIntValue("BestCharFame");
		this.fame = param1.child("Stats").getIntValue("Fame");
		this.credits = param1.getIntValue("Credits");
		this.tokens = param1.getIntValue("FortuneToken");
		this.nextCharSlotPrice = param1.getIntValue("NextCharSlotPrice");
		this.isAgeVerified = this.accountId != "" && param1.getIntValue("IsAgeVerified") == 1;
		this.hasPlayerDied = true;
	}

	private void parseBeginnersPackageData(XML param1) {
		double loc2 = 0;
		BeginnersPackageModel loc3 = null;
		if (param1.hasOwnProperty("BeginnerPackageTimeLeft")) {
			loc2 = param1.getDoubleValue("BeginnerPackageTimeLeft");
			loc3 = this.getBeginnerModel();
			loc3.setBeginnersOfferSecondsLeft(loc2);
		}
	}

	private BeginnersPackageModel getBeginnerModel() {
		BeginnersPackageModel loc2 = BeginnersPackageModel.getInstance();
		return loc2;
	}

	private void parseGuildData(XML param1) {
		XML loc2 = null;
		if (param1.hasOwnProperty("Guild")) {
			loc2 = param1.child("Guild");
			this.guildName = loc2.getValue("Name");
			this.guildRank = loc2.getIntValue("Rank");
		}
	}

	private void parseCharacterData() {
		this.nextCharId = this.charsXML.getIntAttribute("nextCharId");
		this.maxNumChars = this.charsXML.getIntAttribute("maxNumChars");
		for (XML loc1 : this.charsXML.children("Char")) {
			this.savedChars.add(new SavedCharacter(loc1, this.name));
			this.numChars++;
		}
		//this.savedChars.sort(SavedCharacter.compare);
	}

	private void parseCharacterStatsData() {
		int loc3 = 0;
		CharacterStats loc4 = null;
		XML loc1 = this.charsXML.child("Account").child("Stats");
		for (XML loc2 : loc1.children("ClassStats")) {
			loc3 = loc2.getIntAttribute("objectType");
			loc4 = new CharacterStats(loc2);
			this.numStars = this.numStars + loc4.numStars();
			this.charStats.put(loc3, loc4);
		}
	}

	private void parseNewsData() {
		XML loc1 = this.charsXML.child("News");
		for (XML loc2 : loc1.children("Item")) {
			this.news.add(new SavedNewsItem(loc2.getValue("Icon"), loc2.getValue("Title"), loc2.getValue("TagLine"), loc2.getValue("Link"), loc2.getIntValue("Date")));
		}
	}

	private void parseGeoPositioningData() {
		if (this.charsXML.hasOwnProperty("Lat") && this.charsXML.hasOwnProperty("Long")) {
			this.myPos = new LatLong(this.charsXML.getDoubleValue("Lat"), this.charsXML.getDoubleValue("Long"));
		} else {
			this.myPos = DEFAULT_LATLONG;
		}
	}

	private void parseSalesForceData() {
		if (this.charsXML.hasOwnProperty("SalesForce") && this.charsXML.hasOwnProperty("SalesForce")) {
			this.salesForceData = this.charsXML.getValue("SalesForce");
		}
	}

	private void parseTOSPopup() {
		if (this.charsXML.hasOwnProperty("TOSPopup")) {
			OpenDialogSignal.getInstance().dispatch(new TOSPopup());
		}
	}

	public boolean isFirstTimeLogin() {
		return !this.charsXML.hasOwnProperty("TOSPopup");
	}

	public int bestLevel(int param1) {
		CharacterStats loc2 = this.charStats.get(param1);
		return loc2 == null ? 0 : loc2.bestLevel();
	}

	public int bestOverallLevel() {
		int loc1 = 0;
		for (CharacterStats loc2 : this.charStats) {
			if (loc2.bestLevel() > loc1) {
				loc1 = loc2.bestLevel();
			}
		}
		return loc1;
	}

	public int bestFame(int param1) {
		CharacterStats loc2 = this.charStats.get(param1);
		return loc2 == null ? 0 : loc2.bestFame();
	}

	public int bestOverallFame() {
		int loc1 = 0;
		for (CharacterStats loc2 : this.charStats) {
			if (loc2.bestFame() > loc1) {
				loc1 = loc2.bestFame();
			}
		}
		return loc1;
	}

	public boolean levelRequirementsMet(int param1) {
		int loc4 = 0;
		XML loc2 = ObjectLibrary.xmlLibrary.get(param1);
		for (XML loc3 : loc2.children("UnlockLevel")) {
			loc4 = ObjectLibrary.idToType.get(loc3.toString());
			if (this.bestLevel(loc4) < loc3.getIntValue("level")) {
				return false;
			}
		}
		return true;
	}

	public int availableCharSlots() {
		return this.maxNumChars - this.numChars;
	}

	public boolean hasAvailableCharSlot() {
		return this.numChars < this.maxNumChars;
	}

	public Vector newUnlocks(int param1, int param2) {
		XML loc5 = null;
		int loc6 = 0;
		boolean loc7 = false;
		boolean loc8 = false;
		int loc10 = 0;
		int loc11 = 0;
		Vector loc3 = new Vector();
		int loc4 = 0;
		while (loc4 < ObjectLibrary.playerChars.size()) {
			loc5 = ObjectLibrary.playerChars.get(loc4);
			loc6 = loc5.getIntAttribute("type");
			if (!this.levelRequirementsMet(loc6)) {
				loc7 = true;
				loc8 = false;
				for (XML loc9 : loc5.children("UnlockLevel")) {
					loc10 = ObjectLibrary.idToType.get(loc9.toString());
					loc11 = loc9.getIntAttribute("level");
					if (this.bestLevel(loc10) < loc11) {
						if (loc10 != param1 || loc11 != param2) {
							loc7 = false;
							break;
						}
						loc8 = true;
					}
				}
				if (loc7 && loc8) {
					loc3.add(loc6);
				}
			}
			loc4++;
		}
		return loc3;
	}

	public Event clone() {
		return new SavedCharactersList(this.origData);
	}

	public String toString() {
		return "[" + " numChars: " + this.numChars + " maxNumChars: " + this.maxNumChars + " ]";
	}

	private void reportUnlocked() {
		this.account = WebAccount.getInstance();
		this.updateAccount();
	}

	private void updateAccount() {
		XML loc3 = null;
		int loc4 = 0;
		int loc1 = 0;
		int loc2 = 0;
		while (loc2 < ObjectLibrary.playerChars.size()) {
			loc3 = ObjectLibrary.playerChars.get(loc2);
			loc4 = loc3.getIntAttribute("type");
			if (this.levelRequirementsMet(loc4)) {
				this.account.reportIntStat(loc3.getIntAttribute("id") + "Unlocked", 1);
				loc1++;
			}
			loc2++;
		}
		this.account.reportIntStat("ClassesUnlocked", loc1);
	}

}

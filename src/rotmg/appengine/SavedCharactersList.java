package rotmg.appengine;

/**
 * Known as 'Charlist' by The Force
 *//*
public class SavedCharactersList extends Event {

	public static final String SAVED_CHARS_LIST = "SAVED_CHARS_LIST";

	public static final String AVAILABLE = "available";

	public static final String UNAVAILABLE = "unavailable";

	public static final String UNRESTRICTED = "unrestricted";

	private static final LatLong DEFAULT_LATLONG = new LatLong(37.4436F, -122.412F);

	private static final String DEFAULT_SALESFORCE = "unavailable";

	private String origData;

	private XML charsXML;

	public String accountId;

	public int nextCharId;

	public int maxNumChars;

	public int numChars;

	public List<SavedCharacter> savedChars;

	public HashMap<Integer, CharacterStats> charStats; //objectType, CharacterStats

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

	public Boolean nameChosen;

	public Boolean converted;

	public Boolean isAdmin;

	public Boolean canMapEdit;

	public List<SavedNewsItem> news;

	public LatLong myPos;

	public String salesForceData = "unavailable";

	public Boolean hasPlayerDied = false;

	public HashMap<Integer, String> classAvailability = new HashMap<>();

	public Boolean isAgeVerified;

	private Account account;

	public SavedCharactersList(String param1) {
		this.savedChars = new ArrayList<SavedCharacter>();
		this.charStats = new HashMap<>();
		this.news = new ArrayList<SavedNewsItem>();
		super(SAVED_CHARS_LIST);
		this.origData = param1;
		this.charsXML = new XML(this.origData);
		XML loc2 = this.charsXML.getChild("Account");
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
		Injector loc3 = StaticInjectorContext.getInjector();
		if (loc3 != null) {
			Account loc5 = loc3.getInstance(Account);
			loc5.reportIntStat("BestLevel", this.bestOverallLevel());
			loc5.reportIntStat("BestFame", this.bestOverallFame());
			loc5.reportIntStat("NumStars", this.numStars);
			loc5.verify(loc2.hasOwnProperty("VerifiedEmail"));
		}
		this.classAvailability = new HashMap<>();
		for (XML loc4 : this.charsXML.getChild("ClassAvailabilityList").getChilds("ClassAvailability")) {
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
		this.totalFame = param1.getChild("Stats").getIntValue("TotalFame");
		this.bestCharFame = param1.getChild("Stats").getIntValue("BestCharFame");
		this.fame = param1.getChild("Stats").getIntValue("Fame");
		this.credits = param1.getIntValue("Credits");
		this.tokens = param1.getIntValue("FortuneToken");
		this.nextCharSlotPrice = param1.getIntAttribute("NextCharSlotPrice");
		this.isAgeVerified = !this.accountId.equals("") && param1.getIntValue("IsAgeVerified") == 1;
		this.hasPlayerDied = true;
	}

	private void parseBeginnersPackageData(XML param1) {
		BeginnersPackageModel loc3 = null;
		if (param1.hasOwnProperty("BeginnerPackageTimeLeft")) {
			double loc2 = param1.getDoubleValue("BeginnerPackageTimeLeft");
			loc3 = this.getBeginnerModel();
			loc3.setBeginnersOfferSecondsLeft(loc2);
		}
	}

	private BeginnersPackageModel getBeginnerModel() {
		return BeginnersPackageModel.getInstance();
	}

	private void parseGuildData(XML param1) {
		XML loc2 = null;
		if (param1.hasOwnProperty("Guild")) {
			loc2 = param1.getChild("Guild");
			this.guildName = loc2.getValue("Name");
			this.guildRank = loc2.getIntValue("Rank");
		}
	}

	private void parseCharacterData() {
		this.nextCharId = this.charsXML.getIntValue("nextCharId");
		this.maxNumChars = this.charsXML.getIntValue("maxNumChars");
		for (XML loc1 : this.charsXML.getChilds("Char")) {
			this.savedChars.add(new SavedCharacter(loc1, this.name));
			this.numChars++;
		}
		this.savedChars.sort(SavedCharacter.compare);
	}

	private void parseCharacterStatsData() {
		XML loc1 = this.charsXML.getChild("Account").getChild("Stats");
		for (XML loc2 : loc1.getChilds("ClassStats")) {
			int loc3 = loc2.getIntAttribute("objectType");
			CharacterStats loc4 = new CharacterStats(loc2);
			this.numStars = this.numStars + loc4.numStars();
			this.charStats.put(loc3, loc4);
		}
	}

	private void parseNewsData() {
		XML loc1 = this.charsXML.getChild("News");
		for (XML loc2 : loc1.getChilds("Item")) {
			this.news.add(new SavedNewsItem(loc2.getValue("Icon"),
					loc2.getValue("Title"),
					loc2.getValue("TagLine"),
					loc2.getValue("Link"),
					loc2.getIntValue("Date")));
		}
	}

	private void parseGeoPositioningData() {
		if (this.charsXML.hasOwnProperty("Lat") && this.charsXML.hasOwnProperty("Long")) {
			this.myPos = new LatLong(this.charsXML.getFloatValue("Lat"), this.charsXML.getFloatValue("Long"));
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

	public Boolean isFirstTimeLogin() {
		return !this.charsXML.hasOwnProperty("TOSPopup");
	}

	public int bestLevel(int param1) {
		CharacterStats loc2 = this.charStats.get(param1);
		return loc2 == null ? 0 : loc2.bestLevel();
	}

	public int bestOverallLevel() {
		int loc1 = 0;
		for (CharacterStats loc2 : this.charStats.values()) {
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
		for (CharacterStats loc2 : this.charStats.values()) {
			if (loc2.bestFame() > loc1) {
				loc1 = loc2.bestFame();
			}
		}
		return loc1;
	}

	public Boolean levelRequirementsMet(int param1) {
		int loc4 = 0;
		XML loc2 = ObjectLibrary.xmlLibrary.get(param1);
		for (XML loc3 : loc2.getChilds("UnlockLevel")) {
			loc4 = ObjectLibrary.idToType.get(loc3.toString());
			if (this.bestLevel(loc4) < loc3.getIntAttribute("level")) {
				return false;
			}
		}
		return true;
	}

	public int availableCharSlots() {
		return this.maxNumChars - this.numChars;
	}

	public Boolean hasAvailableCharSlot() {
		return this.numChars < this.maxNumChars;
	}

	public List newUnlocks(int param1, int param2) {
		int loc10 = 0;
		int loc11 = 0;
		List loc3 = new ArrayList();

		for (XML loc5 : ObjectLibrary.playerChars) {
			int loc6 = loc5.getIntAttribute("type");
			if (!this.levelRequirementsMet(loc6)) {
				boolean loc7 = true;
				boolean loc8 = false;
				for (XML loc9 : loc5.getChilds("UnlockLevel")) {
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
		}
		return loc3;
	}

	@Override
	public Event clone() {
		return new SavedCharactersList(this.origData);
	}

	@Override
	public String toString() {
		return "[" + " numChars: " + this.numChars + " maxNumChars: " + this.maxNumChars + " ]";
	}

	private void reportUnlocked() {
		var loc1:Injector = StaticInjectorContext.getInjector();
		if (loc1) {
			this.account = loc1.getInstance(Account);
			this.account && this.updateAccount();
		}
	}

	private void updateAccount() {
		int loc1 = 0;
		int loc2 = 0;
		for (XML loc3 : ObjectLibrary.playerChars) {
			int loc4 = loc3.getIntAttribute("id");
			if (this.levelRequirementsMet(loc4)) {
				this.account.reportIntStat(loc3.getIntAttribute("id") + "Unlocked", 1);
				loc1++;
			}
			loc2++;
		}
		this.account.reportIntStat("ClassesUnlocked", loc1);
	}


}
**/
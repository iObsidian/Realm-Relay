package realmrelay.game.appengine;

import realmrelay.game.XML;
import realmrelay.game.account.core.Account;
import realmrelay.game.net.LatLong;
import realmrelay.game.objects.ObjectLibrary;
import realmrelay.game.objects.Player;

/**
 * Known as 'Charlist' by The Force
 */
public class SavedCharactersList {

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

	public Object charStats;

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

	public StringsalesForceData ="unavailable";

	public Boolean hasPlayerDied = false;

	public Object classAvailability;

	public Boolean isAgeVerified;

	private Account account;

	public SavedCharactersList(String param1) {
		this.savedChars = new List<SavedCharacter>();
		this.charStats = {};
		this.news = new List<SavedNewsItem>();
		super(SAVED_CHARS_LIST);
		this.origData = param1;
		this.charsXML = new XML(this.origData);
		XML loc2 = XML(this.charsXML.Account);
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
		if (loc3) {
			Account loc5 = loc3.getInstance(Account);
			loc5.reportIntStat("BestLevel", this.bestOverallLevel());
			loc5.reportIntStat("BestFame", this.bestOverallFame());
			loc5.reportIntStat("NumStars", this.numStars);
			loc5.verify(loc2.hasOwnProperty("VerifiedEmail"));
		}
		this.classAvailability = new Object();
		for each(loc4 :this.charsXML.ClassAvailabilityList.ClassAvailability){
			this.classAvailability[loc4. @id.toString()] =loc4.toString();
		}
	}

	public SavedCharacter getCharById(int param1)

	{
		var loc2:SavedCharacter = null;
		for each(loc2 :this.savedChars){
		if (loc2.charId() == param1) {
			return loc2;
		}
	}
		return null;
	}

	private void parseUserData(XML param1) {
		this.accountId = param1.AccountId;
		this.name = param1.Name;
		this.nameChosen = param1.hasOwnProperty("NameChosen");
		this.converted = param1.hasOwnProperty("Converted");
		this.isAdmin = param1.hasOwnProperty("Admin");
		Player.isAdmin = this.isAdmin_;
		Player.isMod = param1.hasOwnProperty("Mod");
		this.canMapEdit = param1.hasOwnProperty("MapEditor");
		this.totalFame = param1.Stats.TotalFame;
		this.bestCharFame = param1.Stats.BestCharFame;
		this.fame = param1.Stats.Fame;
		this.credits = param1.Credits;
		this.tokens = param1.FortuneToken;
		this.nextCharSlotPrice = param1.NextCharSlotPrice;
		this.isAgeVerified = this.accountId_ != "" && param1.IsAgeVerified == 1;
		this.hasPlayerDied = true;
	}

	private void parseBeginnersPackageData(XML param1) {
		var loc2:Number = NaN;
		var loc3:BeginnersPackageModel = null;
		if (param1.hasOwnProperty("BeginnerPackageTimeLeft")) {
			loc2 = param1.BeginnerPackageTimeLeft;
			loc3 = this.getBeginnerModel();
			loc3.setBeginnersOfferSecondsLeft(loc2);
		}
	}

	private BeginnersPackageModel getBeginnerModel() {
		var loc1:Injector = StaticInjectorContext.getInjector();
		var loc2:BeginnersPackageModel = loc1.getInstance(BeginnersPackageModel);
		return loc2;
	}

	private void parseGuildData(XML param1) {
		XML loc2 = null;
		if (param1.hasOwnProperty("Guild")) {
			loc2 = XML(param1.Guild);
			this.guildName = loc2.Name;
			this.guildRank = int(loc2.Rank);
		}
	}

	private void parseCharacterData() {
		this.nextCharId = int(this.charsXML. @nextCharId);
		this.maxNumChars = int(this.charsXML. @maxNumChars);
		for (XML loc1 : this.charsXML.Char) {
			this.savedChars.push(new SavedCharacter(loc1, this.name));
			this.numChars_++;
		}
		this.savedChars.sort(SavedCharacter.compare);
	}

	private void parseCharacterStatsData() {
		var loc2:XML = null;
		var loc3:int =0;
		var loc4:CharacterStats = null;
		var loc1:XML = XML(this.charsXML.Account.Stats);
		for each(loc2 :loc1.ClassStats){
			loc3 = int(loc2. @objectType);
			loc4 = new CharacterStats(loc2);
			this.numStars = this.numStars + loc4.numStars();
			this.charStats_[loc3] = loc4;
		}
	}

	private void parseNewsData() {
		var loc2:XML = null;
		var loc1:XML = XML(this.charsXML.News);
		for each(loc2 :loc1.Item){
			this.news.push(new SavedNewsItem(loc2.Icon, loc2.Title, loc2.TagLine, loc2.Link, int(loc2.Date)));
		}
	}

	private void parseGeoPositioningData() {
		if (this.charsXML.hasOwnProperty("Lat") && this.charsXML.hasOwnProperty("Long")) {
			this.myPos = new LatLong(Number(this.charsXML.Lat), Number(this.charsXML.Long));
		} else {
			this.myPos = DEFAULT_LATLONG;
		}
	}

	private void parseSalesForceData() {
		if (this.charsXML.hasOwnProperty("SalesForce") && this.charsXML.hasOwnProperty("SalesForce")) {
			this.salesForceData = String(this.charsXML.SalesForce);
		}
	}

	private void parseTOSPopup() {
		if (this.charsXML.hasOwnProperty("TOSPopup")) {
			StaticInjectorContext.getInjector().getInstance(OpenDialogSignal).dispatch(new TOSPopup());
		}
	}

	public Boolean isFirstTimeLogin() {
		return !this.charsXML.hasOwnProperty("TOSPopup");
	}

	public void bestLevel(int param1) {
		var loc2:CharacterStats = this.charStats_[param1];
		return loc2 == null ? 0 : int(loc2.bestLevel());
	}

	public int bestOverallLevel() {
		var loc2:CharacterStats = null;
		var loc1:int =0;
		for each(loc2 :this.charStats){
			if (loc2.bestLevel() > loc1) {
				loc1 = loc2.bestLevel();
			}
		}
		return loc1;
	}

	public int bestFame(int param1) {
		var loc2:CharacterStats = this.charStats_[param1];
		return loc2 == null ? 0 : int(loc2.bestFame());
	}

	public int bestOverallFame() {
		var loc2:CharacterStats = null;
		var loc1:int =0;
		for each(loc2 :this.charStats){
			if (loc2.bestFame() > loc1) {
				loc1 = loc2.bestFame();
			}
		}
		return loc1;
	}

	public Boolean levelRequirementsMet(param1:int) {
		var loc3:XML = null;
		var loc4:int =0;
		var loc2:XML = ObjectLibrary.xmlLibrary_[param1];
		for each(loc3 :loc2.UnlockLevel){
			loc4 = ObjectLibrary.idToType_[loc3.toString()];
			if (this.bestLevel(loc4) < int(loc3. @level)){
				return false;
			}
		}
		return true;
	}

	public int availableCharSlots() {
		return this.maxNumChars_ - this.numChars_;
	}

	public Boolean hasAvailableCharSlot() {
		return this.numChars_ < this.maxNumChars_;
	}

	public void newUnlocks(int param1, int param2):Array

	{
		var loc5:XML = null;
		var loc6:int =0;
		var loc7:Boolean = false;
		var loc8:Boolean = false;
		var loc9:XML = null;
		var loc10:int =0;
		var loc11:int =0;
		var loc3:Array = new Array();
		var loc4:int =0;
		while (loc4 < ObjectLibrary.playerChars.length) {
			loc5 = ObjectLibrary.playerChars_[loc4];
			loc6 = int(loc5. @type);
			if (!this.levelRequirementsMet(loc6)) {
				loc7 = true;
				loc8 = false;
				for each(loc9 :loc5.UnlockLevel){
					loc10 = ObjectLibrary.idToType_[loc9.toString()];
					loc11 = int(loc9. @level);
					if (this.bestLevel(loc10) < loc11) {
						if (loc10 != param1 || loc11 != param2) {
							loc7 = false;
							break;
						}
						loc8 = true;
					}
				}
				if (loc7 && loc8) {
					loc3.push(loc6);
				}
			}
			loc4++;
		}
		return loc3;
	}

	@Override
	public Event clone()

	{
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
		var loc1:int =0;
		var loc2:int =0;
		while (loc2 < ObjectLibrary.playerChars.length) {
			XML loc3 = ObjectLibrary.playerChars.get(loc2);
			int loc4 = int(loc3. @type);
			if (this.levelRequirementsMet(loc4)) {
				this.account.reportIntStat(loc3. @id +"Unlocked", 1);
				loc1++;
			}
			loc2++;
		}
		this.account.reportIntStat("ClassesUnlocked", loc1);
	}


}

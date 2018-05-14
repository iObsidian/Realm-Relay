package realmrelay.game.core.model;

import realmrelay.game.Signal;
import realmrelay.game.account.core.Account;
import realmrelay.game.appengine.SavedCharacter;
import realmrelay.game.appengine.SavedCharactersList;
import realmrelay.game.appengine.SavedNewsItem;
import realmrelay.game.net.LatLong;
import realmrelay.game.parameters.Parameters;

import java.util.List;

public class PlayerModel {

	public static final int[] CHARACTER_SLOT_PRICES = new int[]{600, 800, 1000};

	public static Signal creditsChanged = new Signal<Integer>();

	public static Signal fameChanged = new Signal<Integer>();

	public static Signal tokensChanged = new Signal<Integer>();

	public SavedCharactersList charList;

	public boolean isInvalidated;

	public int currentCharId;

	private boolean isAgeVerified;

	public Account account;

	public PlayerModel() {
		super();
		this.isInvalidated = true;
	}

	public void setCurrentCharId(int param1) {
		this.currentCharId = param1;
	}

	public int getCurrentCharId() {
		return this.currentCharId;
	}

	public boolean getHasPlayerDied() {
		return this.charList.hasPlayerDied;
	}

	public void setHasPlayerDied(boolean param1) {
		this.charList.hasPlayerDied = param1;
	}

	public boolean getIsAgeVerified() {
		return (this.isAgeVerified) || this.account instanceof KongregateAccount || this.charList.isAgeVerified;
	}

	public void setIsAgeVerified(boolean param1) {
		this.isAgeVerified = true;
	}

	public boolean isNewPlayer() {
		return Parameters.data.needsTutorial && this.charList.nextCharId == 1;
	}

	public int getMaxCharacters() {
		return this.charList.maxNumChars;
	}

	public void setMaxCharacters(int param1) {
		this.charList.maxNumChars = param1;
	}

	public int getCredits() {
		return this.charList.credits;
	}

	public String getSalesForceData() {
		return this.charList.salesForceData;
	}

	public void changeCredits(int param1) {
		this.charList.credits = this.charList.credits + param1;
		this.creditsChanged.dispatch(this.charList.credits);
	}

	public void setCredits(int param1) {
		if (this.charList.credits != param1) {
			this.charList.credits = param1;
			this.creditsChanged.dispatch(param1);
		}
	}

	public int getFame() {
		return this.charList.fame;
	}

	public void setFame(int param1) {
		if (this.charList.fame != param1) {
			this.charList.fame = param1;
			this.fameChanged.dispatch(param1);
		}
	}

	public int getTokens() {
		return this.charList.tokens;
	}

	public void setTokens(int param1) {
		if (this.charList.tokens != param1) {
			this.charList.tokens = param1;
			this.tokensChanged.dispatch(param1);
		}
	}

	public int getCharacterCount() {
		return this.charList.numChars;
	}

	public SavedCharacter getCharById(int param1) {
		return this.charList.getCharById(param1);
	}

	public void deleteCharacter(int param1) {
		SavedCharacter loc2 = this.charList.getCharById(param1);
		int loc3 = this.charList.savedChars.indexOf(loc2);
		if (loc3 != -1) {
			this.charList.savedChars.remove(loc3);
			this.charList.numChars--;
		}
	}

	public String getAccountId() {
		return this.charList.accountId;
	}

	public boolean hasAccount() {
		return this.charList.accountId != "";
	}

	public int getNumStars() {
		return this.charList.numStars;
	}

	public String getGuildName() {
		return this.charList.guildName;
	}

	public int getGuildRank() {
		return this.charList.guildRank;
	}

	public int getNextCharSlotPrice() {
		int loc1 = Math.min(CHARACTER_SLOT_PRICES.length - 1, this.charList.maxNumChars - 1);
		return CHARACTER_SLOT_PRICES[loc1];
	}

	public int getTotalFame() {
		return this.charList.totalFame;
	}

	public int getNextCharId() {
		return this.charList.nextCharId;
	}

	public SavedCharacter getCharacterById(int param1) {
		for (SavedCharacter loc2 : this.charList.savedChars) {
			if (loc2.charId() == param1) {
				return loc2;
			}
		}
		return null;
	}

	public SavedCharacter getCharacterByIndex(int param1) {
		return this.charList.savedChars.get(param1);
	}

	public boolean isAdmin() {
		return this.charList.isAdmin;
	}

	public boolean mapEditor() {
		return this.charList.canMapEdit;
	}

	public List<SavedNewsItem> getNews() {
		return this.charList.news;
	}

	public LatLong getMyPos() {
		return this.charList.myPos;
	}

	public void setClassAvailability(int param1, String param2) {
		this.charList.classAvailability.put(param1, param2);
	}

	public String getName() {
		return this.charList.name;
	}

	public boolean getConverted() {
		return this.charList.converted;
	}

	public void setName(String param1) {
		this.charList.name = param1;
	}

	public boolean isNameChosen() {
		return this.charList.nameChosen;
	}

	public List getNewUnlocks(int param1, int param2) {
		return this.charList.newUnlocks(param1, param2);
	}

	public boolean hasAvailableCharSlot() {
		return this.charList.hasAvailableCharSlot();
	}

	public int getAvailableCharSlots() {
		return this.charList.availableCharSlots();
	}

	public List<SavedCharacter> getSavedCharacters() {
		return this.charList.savedChars;
	}

	public Object getCharStats() {
		return this.charList.charStats;
	}

	public boolean isClassAvailability(String param1, String param2) {
		String loc3 = this.charList.classAvailability.get(param1);
		return loc3.equals(param2);
	}

	public boolean isLevelRequirementsMet(int param1) {
		return this.charList.levelRequirementsMet(param1);
	}

	public int getBestFame(int param1) {
		return this.charList.bestFame(param1);
	}

	public int getBestLevel(int param1) {
		return this.charList.bestLevel(param1);
	}

	public int getBestCharFame() {
		return this.charList.bestCharFame;
	}

	public void setCharacterList(SavedCharactersList param1) {
		this.charList = param1;
	}

	public boolean isNewToEditing() {
		if (this.charList != null && !this.charList.isFirstTimeLogin()) {
			return false;
		}
		return true;
	}


}

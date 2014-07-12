package realmrelay.data;

public class PlayerData {
	
	public int maxHealth = 100;
	public int health = 100;
	public int maxMana = 100;
	public int mana = 100;
	public int xpGoal;
	public int xp;
	public int level = 1;
	public int[] slot = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	public int[] backPack = { -1, -1, -1, -1, -1, -1, -1, -1 };
	public int attack;
	public int defense;
	public int speed = 5;
	public int vitality = 5;
	public int wisdom = 5;
	public int dexterity = 10;
	public int stars;
	public String name;
	public int realmGold;
	public int price;
	public boolean canEnterPortal;
	public int accountId;
	public int currentFame;
	public int healthBonus;
	public int manaBonus;
	public int attackBonus;
	public int defenseBonus;
	public int speedBonus;
	public int vitalityBonus;
	public int wisdomBonus;
	public int dexterityBonus;
	public int nameChangeRankRequired;
	public boolean nameRegistered;
	public int fame;
	public int fameGoal;
	public int glowingEffect;
	public String guild;
	public int guildRank;
	public int breath;
	public int healthpotCount = -1;
	public int manapotCount = -1;
	public int boolHasbackPack = -1;
	public int petSkinObjectType = -1;
	public Location pos = new Location();
	public String mapName;
	
	public boolean hasInc;
	
	public int id;
	
	public PlayerData() {
	}
	
	public void parseNewTICK(int obf0, int obf1, String obf2) {
		if (obf0 == 0) {
			this.maxHealth = obf1;
		} else if (obf0 == 1) {
			this.health = obf1;
		} else if (obf0 == 3) {
			this.maxMana = obf1;
		} else if (obf0 == 4) {
			this.mana = obf1;
		} else if (obf0 == 5) {
			this.xpGoal = obf1;
		} else if (obf0 == 6) {
			this.xp = obf1;
		} else if (obf0 == 7) {
			this.level = obf1;
		} else if (obf0 == 8) { /* SLOTS */
			this.slot[0] = obf1;
		} else if (obf0 == 9) {
			this.slot[1] = obf1;
		} else if (obf0 == 10) {
			this.slot[2] = obf1;
		} else if (obf0 == 11) {
			this.slot[3] = obf1;
		} else if (obf0 == 12) {
			this.slot[4] = obf1;
		} else if (obf0 == 13) {
			this.slot[5] = obf1;
		} else if (obf0 == 14) {
			this.slot[6] = obf1;
		} else if (obf0 == 15) {
			this.slot[7] = obf1;
		} else if (obf0 == 16) {
			this.slot[8] = obf1;
		} else if (obf0 == 17) {
			this.slot[9] = obf1;
		} else if (obf0 == 18) {
			this.slot[11] = obf1;
		} else if (obf0 == 19) {
			this.slot[10] = obf1;
		} else if (obf0 == 20) {
			this.attack = obf1;
		} else if (obf0 == 21) {
			this.defense = obf1;
		} else if (obf0 == 22) {
			this.speed = obf1;
		} else if (obf0 == 26) {
			this.vitality = obf1;
		} else if (obf0 == 27) {
			this.wisdom = obf1;
		} else if (obf0 == 28) {
			this.dexterity = obf1;
		} else if (obf0 == 30) {
			this.stars = obf1;
		} else if (obf0 == 31) {
			this.name = obf2;
		} else if (obf0 == 35) {
			this.realmGold = obf1;
		} else if (obf0 == 36) {
			this.price = obf1;
		} else if (obf0 == 37) {
			this.canEnterPortal = Boolean.parseBoolean(obf2);
		} else if (obf0 == 38) {
			this.accountId = obf1;
		} else if (obf0 == 39) {
			this.currentFame = obf1; //fame you got when you died
		} else if (obf0 == 46) {
			this.healthBonus = obf1;
		} else if (obf0 == 47) {
			this.manaBonus = obf1;
		} else if (obf0 == 48) {
			this.attackBonus = obf1;
		} else if (obf0 == 49) {
			this.defenseBonus = obf1;
		} else if (obf0 == 50) {
			this.speedBonus = obf1;
		} else if (obf0 == 51) {
			this.vitalityBonus = obf1;
		} else if (obf0 == 52) {
			this.wisdomBonus = obf1;
		} else if (obf0 == 53) {
			this.dexterityBonus = obf1;
		} else if (obf0 == 55) {
			this.nameChangeRankRequired = obf1;
		} else if (obf0 == 56) {
			this.nameRegistered = Boolean.parseBoolean(obf2);
		} else if (obf0 == 57) {
			this.fame = obf1; //fame on this character
		} else if (obf0 == 58) {
			this.fameGoal = obf1;
		} else if (obf0 == 59) {
			this.glowingEffect = obf1;
		} else if (obf0 == 62) {
			this.guild = obf2;
		} else if (obf0 == 63) {
			this.guildRank = obf1;
		} else if (obf0 == 64) {
			this.breath = obf1;
		} else if (obf0 == 69) {
			this.healthpotCount = obf1;
		} else if (obf0 == 70) {
			this.manapotCount = obf1;
		} else if (obf0 == 79) { /* BACKPACK */
			this.backPack[0] = obf1;
		} else if (obf0 == 80) {
			this.backPack[1] = obf1;
		} else if (obf0 == 81) {
			this.backPack[2] = obf1;
		} else if (obf0 == 82) {
			this.backPack[3] = obf1;
		} else if (obf0 == 83) {
			this.backPack[4] = obf1;
		} else if (obf0 == 84) {
			this.backPack[5] = obf1;
		} else if (obf0 == 85) {
			this.backPack[6] = obf1;
		} else if (obf0 == 86) {
			this.backPack[7] = obf1;
		} else if (obf0 == 79) {
			this.boolHasbackPack = obf1;
		} else if (obf0 == 80) {
			this.petSkinObjectType = obf1;
		}
	}
}
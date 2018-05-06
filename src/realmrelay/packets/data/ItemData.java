package realmrelay.packets.data;

public class ItemData {

	public String file;
	public int index;

	public String id = ""; // attribute id
	public int type = -1; // attribute type
	public int slotType = -1; // element SlotType
	public int tier = 0; // element Tier
	public String petFamily = ""; // element PetFamily
	public String rarity = ""; // element Rarity
	public String activate = ""; // element Activate
	public boolean consumable = false; // element Consumable
	public boolean soulbound = false; // element Soulbound
	public boolean usable = false; // element Usable
	public int bagType = -1; // element BagType
	public int feedPower = -1; // element feedPower
	public float rateOfFire = -1; // element RateOfFire
	public int fameBonus = 0; // element FameBonus
	public int mpCost = -1; // element MpCost
	public int mpEndCost = -1; // element MpEndCost
	public boolean multiPhase = false; // element MultiPhase
	public int numProjectiles = -1; // element NumProjectiles
	public Object[] projectiles = new Object[0];

}

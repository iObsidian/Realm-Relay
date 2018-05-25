package rotmg.pets.data;

/**
 * This is a 100% match
 */
public class PetYardEnum {


	public static final PetYardEnum PET_YARD_ONE = new PetYardEnum("Yard Upgrader 1", 1, PetRarityEnum.COMMON);

	public static final PetYardEnum PET_YARD_TWO = new PetYardEnum("Yard Upgrader 2", 2, PetRarityEnum.UNCOMMON);

	public static final PetYardEnum PET_YARD_THREE = new PetYardEnum("Yard Upgrader 3", 3, PetRarityEnum.RARE);

	public static final PetYardEnum PET_YARD_FOUR = new PetYardEnum("Yard Upgrader 4", 4, PetRarityEnum.LEGENDARY);

	public static final PetYardEnum PET_YARD_FIVE = new PetYardEnum("Yard Upgrader 5", 5, PetRarityEnum.DIVINE);

	public static final int MAX_ORDINAL = 5;

	public String value;

	public int ordinal;

	public PetRarityEnum rarity;

	public PetYardEnum(String param1, int param2, PetRarityEnum param3) {
		super();
		this.value = param1;
		this.ordinal = param2;
		this.rarity = param3;
	}

	public static PetYardEnum[] getList() {
		return new PetYardEnum[]{PET_YARD_ONE, PET_YARD_TWO, PET_YARD_THREE, PET_YARD_FOUR, PET_YARD_FIVE};
	}

	public static PetYardEnum selectByValue(String param1) {
		PetYardEnum loc2 = null;
		for (PetYardEnum loc3 : PetYardEnum.getList()) {
			if (param1.equals(loc3.value)) {
				loc2 = loc3;
			}
		}
		return loc2;
	}

	public static PetYardEnum selectByOrdinal(int param1) {
		PetYardEnum loc2 = null;
		for (PetYardEnum loc3 : PetYardEnum.getList()) {
			if (param1 == loc3.ordinal) {
				loc2 = loc3;
			}
		}
		return loc2;
	}


}

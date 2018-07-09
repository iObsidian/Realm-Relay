package rotmg.pets.data;


/**
 * This is a 100% match
 */
public class PetRarityEnum {

	public static final PetRarityEnum COMMON = new PetRarityEnum("Pets.common", 0);

	public static final PetRarityEnum UNCOMMON = new PetRarityEnum("Pets.uncommon", 1);

	public static final PetRarityEnum RARE = new PetRarityEnum("Pets.rare", 2);

	public static final PetRarityEnum LEGENDARY = new PetRarityEnum("Pets.legendary", 3);

	public static final PetRarityEnum DIVINE = new PetRarityEnum("Pets.divine", 4);

	public static final int MAX_ORDINAL = 4;

	public String value;

	public int ordinal;

	public PetRarityEnum(String value, int ordinal) {
		super();
		this.value = value;
		this.ordinal = ordinal;
	}

	public static PetRarityEnum[] getList() {
		return new PetRarityEnum[]{COMMON, UNCOMMON, RARE, LEGENDARY, DIVINE};
	}

	public static PetRarityEnum selectByValue(String param1) {
		PetRarityEnum loc2 = null;
		for (PetRarityEnum loc3 : PetRarityEnum.getList()) {
			if (param1.equals(loc3.value)) {
				loc2 = loc3;
			}
		}
		return loc2;
	}

	public static PetRarityEnum selectByOrdinal(int param1) {
		PetRarityEnum loc2 = null;
		for (PetRarityEnum loc3 : PetRarityEnum.getList()) {
			if (param1 == loc3.ordinal) {
				loc2 = loc3;
			}
		}
		return loc2;
	}

}

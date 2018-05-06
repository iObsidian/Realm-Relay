package realmrelay.data;

class SlotTypeToSlotId {

	public static int NO_ITEM = -1;
	public static int ALL_TYPE = 0;
	public static int SWORD_TYPE = 1;
	public static int DAGGER_TYPE = 2;
	public static int BOW_TYPE = 3;
	public static int TOME_TYPE = 4;
	public static int SHIELD_TYPE = 5;
	public static int LEATHER_TYPE = 6;
	public static int PLATE_TYPE = 7;
	public static int WAND_TYPE = 8;
	public static int RING_TYPE = 9;
	public static int POTION_TYPE = 10;
	public static int SPELL_TYPE = 11;
	public static int SEAL_TYPE = 12;
	public static int CLOAK_TYPE = 13;
	public static int ROBE_TYPE = 14;
	public static int QUIVER_TYPE = 15;
	public static int HELM_TYPE = 16;
	public static int STAFF_TYPE = 17;
	public static int POISON_TYPE = 18;
	public static int SKULL_TYPE = 19;
	public static int TRAP_TYPE = 20;
	public static int ORB_TYPE = 21;
	public static int PRISM_TYPE = 22;
	public static int SCEPTER_TYPE = 23;
	public static int KATANA_TYPE = 24;
	public static int SHURIKEN_TYPE = 25;
	public static int EGG_TYPE = 26;

	/**
	 *
	 * SlotTypes are used by the server to know if you can put items to a
	 * certain slot.
	 *
	 * Here is some rework of the server mechanism : {slotType,slotId}
	 *
	 */

	private static final int[][] slotTypeAndSlotId = new int[][] {

			{ 1, 0 }, // sword
			{ 2, 0 }, // dagger
			{ 3, 0 }, // bow
			{ 4, 1 }, // tome
			{ 5, 1 }, // shield
			{ 6, 2 }, // archer armor leather
			{ 7, 2 }, // heavy armor plate
			{ 8, 0 }, // wand
			{ 9, 3 }, // ring
			{ 11, 1 }, // wizard spell
			{ 12, 1 }, // seal
			{ 13, 1 }, // cloak
			{ 14, 2 }, // robe
			{ 15, 1 }, // quiver
			{ 16, 1 }, // helm
			{ 17, 0 }, // staff
			{ 18, 1 }, // poison
			{ 19, 1 }, // skull
			{ 20, 1 }, // trap
			{ 21, 1 }, // orb
			{ 22, 1 }, // prism
			{ 23, 1 }, // scepter
			{ 24, 0 }, // katana
			{ 25, 1 } // shuriken

	};

	public static int slotTypeToSlotId(int slotType) {

		for (int i = 0; i < slotTypeAndSlotId.length; i++) {
			if (slotTypeAndSlotId[i][0] == slotType) {
				return slotTypeAndSlotId[i][1];
			}
		}
		return -1;
	}

}

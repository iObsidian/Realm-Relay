package realmrelay.packets.data.unused;

/* Thanks to ▲Pepsi */

class SlotTypeToClass {

	/*

	768: "Rogue", X
	775: "Archer",s X
	782: "Wizard", X
	784: "Priest", X
	797: "Warrior", X
	798: "Knight", X
	799: "Paladin", X
	800: "Assassin", X
	801: "Necromancer",
	802: "Hunter", X
	803: "Mystic",
	804: "Trickster",
	805: "Sorceror",
	806: "Ninja"

	*/

	/*
	 	1, //sword
		2, //dagger
		3 //bow
		4 //tome
		5 //shield
		6 //archer armor
		7 //heavy armor
		8 //wand
		9 //ring
		11 //wizard spell
		12 //seal
		13 //cloak
		14 //robe
		15 //quiver
		16 //helm
		17 //staff
		18 //poison
		19 //skull
		20 //trap
		21 //orb
		22 //prism
		23 //scepter
		24 //katana
		25 //shuriken
		26

	 */

	/**
	 * @param slotType
	 * @param classId
	 * @return true if the item can be wearen in the slot (given with the
	 * slotype with slotTypeToSlotId)
	 */
	public static boolean canWear(int slotType, int classId) {
		switch (classId) {

			case 797: // warrior

				//Simplified from if (slotType == 1 || slotType == 16 || slotType == 7 || slotType == 9) {


				// sword    helm    heavy_armor   ring
				if (slotType == 1 || slotType == 16 || slotType == 7 || slotType == 9) {
					return true;
				} else {
					return false;
				}

			case 798: // knight

				// sword    shield    heavy_armor   ring
				return slotType == 1 || slotType == 5 || slotType == 7 || slotType == 9;

			case 799: // paladin

				// sword    seal    heavy_armor   ring
				return slotType == 1 || slotType == 12 || slotType == 7 || slotType == 9;

			case 803: // mystic

				// staff    orb    robe   ring
				return slotType == 17 || slotType == 21 || slotType == 14 || slotType == 9;

			case 782: // wizard

				// staff    spell    robe   ring
				return slotType == 17 || slotType == 11 || slotType == 14 || slotType == 9;

			case 801: // necromancer

				// staff    spell    robe   ring
				return slotType == 17 || slotType == 19 || slotType == 14 || slotType == 9;

			case 784: // priest

				// wand    tome    robe   ring
				return slotType == 8 || slotType == 4 || slotType == 14 || slotType == 9;

			case 768: // rogue

				// dagger    cloak    light armor   ring
				return slotType == 2 || slotType == 13 || slotType == 6 || slotType == 9;

			case 800: // assassin

				// dagger    poison    light armor   ring
				return slotType == 2 || slotType == 18 || slotType == 6 || slotType == 9;

			case 775: // archer

				// bow    quiver    light armor   ring
				return slotType == 3 || slotType == 15 || slotType == 6 || slotType == 9;

			case 802: // huntress

				// bow    trap    light armor   ring
				return slotType == 3 || slotType == 20 || slotType == 6 || slotType == 9;

		}

		return false;
	}

}

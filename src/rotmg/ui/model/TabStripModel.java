package rotmg.ui.model;

import rotmg.model.PotionInventoryModel;

public class TabStripModel {

	public static TabStripModel instance;

	public static TabStripModel getInstance() {
		if (instance == null) {
			instance = new TabStripModel();
		}

		return instance;
	}


	public static final String MAIN_INVENTORY = "Main Inventory";

	public static final String STATS = "Stats";

	public static final String BACKPACK = "Backpack";

	public static final String PETS = "Pets";


	public String currentSelection;

	public TabStripModel() {
		super();
	}

}

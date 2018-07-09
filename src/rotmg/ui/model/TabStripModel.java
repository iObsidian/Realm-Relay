package rotmg.ui.model;

public class TabStripModel {

	public static final String MAIN_INVENTORY = "Main Inventory";
	public static final String STATS = "Stats";
	public static final String BACKPACK = "Backpack";
	public static final String PETS = "Pets";
	public static TabStripModel instance;
	public String currentSelection;


	public TabStripModel() {
		super();
	}

	public static TabStripModel getInstance() {
		if (instance == null) {
			instance = new TabStripModel();
		}

		return instance;
	}

}

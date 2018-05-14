package realmrelay.game.pets.data;

import realmrelay.game.Signal;
import realmrelay.game.XML;
import realmrelay.game.objects.ObjectLibrary;

/**
 * This is a 90% match, some uncertainty with 'this.name' (setType)
 */
public class AbilityVO {

	private int type;

	private XML staticData;

	public static Signal updated = new Signal<AbilityVO>();

	public int level;

	public int points;

	public String name;

	public String description;

	private boolean unlocked;

	public AbilityVO() {
		super();
	}

	// Not sure about the this.name line
	public void setType(int param1) {
		this.type = param1;
		this.staticData = ObjectLibrary.getPetDataXMLByType(this.type);
		this.name = this.staticData.hasOwnProperty("DisplayId") ? this.staticData.getValue("id") : this.staticData.getValue("DisplayId");
		this.description = this.staticData.getValue("Description");
	}

	public void setUnlocked(boolean param1) {
		this.unlocked = param1;
	}

	public boolean getUnlocked() {
		return this.unlocked;
	}


}

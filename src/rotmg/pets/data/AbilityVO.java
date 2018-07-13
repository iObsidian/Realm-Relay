package rotmg.pets.data;

import alde.flash.utils.XML;
import org.osflash.signals.Signal;
import rotmg.objects.ObjectLibrary;

/**
 * This is a 90% match, some uncertainty with 'this.name' (setType)
 */
public class AbilityVO {

	public static Signal updated = new Signal<AbilityVO>();
	public int level;
	public int points;
	public String name;
	public String description;
	private int type;
	private XML staticData;
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

	public boolean getUnlocked() {
		return this.unlocked;
	}

	public void setUnlocked(boolean param1) {
		this.unlocked = param1;
	}


}

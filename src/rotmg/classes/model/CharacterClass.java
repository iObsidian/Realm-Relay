package rotmg.classes.model;

import org.osflash.signals.Signal;

import java.util.ArrayList;
import java.util.List;

public class CharacterClass {

	public final Signal<CharacterClass> selected = new Signal<>();

	public final List<CharacterClassUnlock> unlocks = new ArrayList<CharacterClassUnlock>(0);
	public final CharacterSkins skins = new CharacterSkins();
	public int id;
	public String name;
	public String description;
	public String hitSound;
	public String deathSound;
	public double bloodProb;
	public int[] slotTypes;
	public int[] defaultEquipment;
	public CharacterClassStat hp;
	public CharacterClassStat mp;
	public CharacterClassStat attack;
	public CharacterClassStat defense;
	public CharacterClassStat speed;
	public CharacterClassStat dexterity;
	public CharacterClassStat hpRegeneration;
	public CharacterClassStat mpRegeneration;
	public int unlockCost;
	private int maxLevelAchieved;
	private boolean isSelected;

	public CharacterClass() {
		super();
	}

	public boolean getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(boolean param1) {
		if (this.isSelected != param1) {
			this.isSelected = param1;
			this.selected.dispatch(this);
		}
	}

	public int getMaxLevelAchieved() {
		return this.maxLevelAchieved;
	}

	public void setMaxLevelAchieved(int param1) {
		this.maxLevelAchieved = param1;
		this.skins.updateSkins(this.maxLevelAchieved);
	}

}

package realmrelay.game.classes.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterClass {

	public final List<CharacterClassUnlock> unlocks = new ArrayList<CharacterClassUnlock>(0);
	public final CharacterSkins skins = new CharacterSkins();

	public CharacterClass() {
		super();
	}

	public int id;
	public String name;
	public String description;
	public String hitSound;
	public String deathSound;
	public float bloodProb;
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

}

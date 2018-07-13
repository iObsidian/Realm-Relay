package rotmg.pets.data;

import alde.flash.utils.SignalConsumer;
import alde.flash.utils.XML;
import flash.display.Bitmap;
import flash.display.BitmapData;
import org.osflash.signals.Signal;
import rotmg.objects.ObjectLibrary;
import rotmg.objects.animation.AnimatedChar;
import rotmg.util.AnimatedChars;
import rotmg.util.MaskedImage;
import rotmg.util.TextureRedrawer;
import rotmg.util.redrawers.GlowRedrawer;

public class PetVO {

	public static Signal updated = new Signal();
	public AbilityVO[] abilityList;
	private XML staticData;
	private int id;
	private int type;
	private String rarity;
	private String name;
	private int maxAbilityPower;
	private int skinID;
	private AnimatedChar skin;

	public PetVO(int param1) {
		this.abilityList = new AbilityVO[]{new AbilityVO(), new AbilityVO(), new AbilityVO()};
		this.id = param1;
		this.staticData = null;
		this.listenToAbilities();
	}

	private static String getPetDataDescription(int param1) {
		return ObjectLibrary.getPetDataXMLByType(param1).getValue("Description");
	}

	private static String getPetDataDisplayId(int param1) {
		return ObjectLibrary.getPetDataXMLByType(param1).getAttribute("id");
	}

	public static PetVO clone(PetVO param1) {
		PetVO loc2 = new PetVO(param1.id);
		return loc2;
	}

	private void listenToAbilities() {
		for (AbilityVO abilityVO : this.abilityList) {
			abilityVO.updated.add(new SignalConsumer<>(this::onAbilityUpdate));
		}
	}

	public boolean maxedAllAbilities() {
		int loc1 = 0;
		for (AbilityVO loc2 : this.abilityList) {
			if (loc2.level == 100) {
				loc1++;
			}
		}
		return loc1 == this.abilityList.length;
	}

	public void onAbilityUpdate(AbilityVO param1) {
		this.updated.dispatch();
	}

	public void apply(XML param1) {
		this.extractBasicData(param1);
		this.extractAbilityData(param1);
	}

	/**
	 * This method may not be accurate
	 */
	private void extractBasicData(XML param1) {
		this.setID(param1.getIntAttribute("instanceId"));
		this.setType(param1.getIntAttribute("type"));
		this.setName(param1.getAttribute("name"));
		this.setSkin(param1.getIntAttribute("skin"));
		this.setRarity(param1.getIntAttribute("rarity"));
	}

	public void extractAbilityData(XML param1) {
		for (int i = 0; i < this.abilityList.length; i++) {
			AbilityVO abilityVO = this.abilityList[i];

			int objectType = param1.child("Abilities").children("Ability").get(i).getIntAttribute("type");
			abilityVO.name = getPetDataDisplayId(objectType);
			abilityVO.description = getPetDataDescription(objectType);
			abilityVO.level = param1.child("Abilities").children("Ability").get(i).getIntAttribute("power");
			abilityVO.points = param1.child("Abilities").children("Ability").get(i).getIntAttribute("points");
		}
	}

	public String getFamily() {
		return this.staticData.getValue("Family");
	}

	public int getID() {
		return this.id;
	}

	public void setID(int param1) {
		this.id = param1;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int param1) {
		this.type = param1;
		this.staticData = ObjectLibrary.xmlLibrary.get(this.type);
	}

	private void unlockAbilitiesBasedOnPetRarity(int param1) {
		this.abilityList[0].setUnlocked(true);
		this.abilityList[1].setUnlocked(param1 >= PetRarityEnum.UNCOMMON.ordinal);
		this.abilityList[2].setUnlocked(param1 >= PetRarityEnum.LEGENDARY.ordinal);
	}

	public String getRarity() {
		return this.rarity;
	}

	public void setRarity(int param1) {
		this.rarity = PetRarityEnum.selectByOrdinal(param1).value;
		this.unlockAbilitiesBasedOnPetRarity(param1);
		this.updated.dispatch();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String param1) {
		this.name = ObjectLibrary.typeToDisplayId.get(this.skinID);
		if (this.name == null || this.name.equals("")) {
			this.name = ObjectLibrary.typeToDisplayId.get(this.getType());
		}
		this.updated.dispatch(null);
	}

	public int getMaxAbilityPower() {
		return this.maxAbilityPower;
	}

	public void setMaxAbilityPower(int param1) {
		this.maxAbilityPower = param1;
		this.updated.dispatch();
	}

	public int getSkinID() {
		return this.skinID;
	}

	public Bitmap getSkin() {
		this.makeSkin();
		if (this.skin == null) {
			return null;
		}
		MaskedImage loc1 = this.skin.imageFromDir(0, AnimatedChar.STAND, 0);
		int loc2 = this.skin.getHeight() == 16 ? 40 : 80;
		BitmapData loc3 = TextureRedrawer.resize(loc1.image, loc1.mask, loc2, true, 0, 0);
		loc3 = GlowRedrawer.outlineGlow(loc3, 0);
		return new Bitmap(loc3);
	}

	public void setSkin(int param1) {
		this.skinID = param1;
		this.updated.dispatch();
	}

	public MaskedImage getSkinMaskedImage() {
		this.makeSkin();
		return (this.skin != null) ? this.skin.imageFromDir(0, AnimatedChar.STAND, 0) : null;
	}

	private void makeSkin() {
		XML loc1 = ObjectLibrary.getXMLfromId(ObjectLibrary.getIdFromType(this.skinID));
		if (loc1 == null) {
			return;
		}
		String loc2 = loc1.child("AnimatedTexture").getValue("File");
		int loc3 = loc1.child("AnimatedTexture").getIntValue("Index");
		this.skin = AnimatedChars.getAnimatedChar(loc2, loc3);
	}


}

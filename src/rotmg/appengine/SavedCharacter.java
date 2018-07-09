package rotmg.appengine;

import alde.flash.utils.XML;
import flash.display.BitmapData;
import flash.geom.ColorTransform;
import rotmg.assets.services.CharacterFactory;
import rotmg.classes.model.CharacterClass;
import rotmg.classes.model.CharacterSkin;
import rotmg.classes.model.ClassesModel;
import rotmg.constants.GeneralConstants;
import rotmg.objects.ObjectLibrary;
import rotmg.objects.Player;
import rotmg.objects.animation.AnimatedChar;
import rotmg.parameters.Parameters;
import rotmg.pets.data.PetVO;
import rotmg.pets.data.PetsModel;
import rotmg.util.AnimatedChars;
import rotmg.util.CachingColorTransformer;
import rotmg.util.MaskedImage;
import rotmg.util.TextureRedrawer;
import rotmg.util.redrawers.GlowRedrawer;

public class SavedCharacter {

	public XML charXML;

	public String name = null; // Player name

	private PetVO pet;

	public SavedCharacter(XML param1, String param2) {
		XML loc3 = null;
		int loc4 = 0;
		PetVO loc5 = null;
		this.charXML = param1;
		this.name = param2;
		if (this.charXML.hasOwnProperty("Pet")) {
			loc3 = this.charXML.child("Pet");
			loc4 = loc3.getIntAttribute("instanceId");
			loc5 = PetsModel.getInstance().getPetVO(loc4);
			loc5.apply(loc3);
			this.setPetVO(loc5);
		}
	}

	public static BitmapData getImage(SavedCharacter param1, XML param2, int param3, int param4, double param5,
	                                  boolean param6, boolean param7) {
		AnimatedChar loc8 = AnimatedChars.getAnimatedChar(param2.child("AnimatedTexture").getValue("File"),
				param2.child("AnimatedTexture").getIntValue("Index"));
		MaskedImage loc9 = loc8.imageFromDir(param3, param4, param5);
		int loc10 = param1 != null ? param1.tex1() : null;
		int loc11 = param1 != null ? param1.tex2() : null;
		BitmapData loc12 = TextureRedrawer.resize(loc9.image, loc9.mask, 100, false, loc10, loc11);
		loc12 = GlowRedrawer.outlineGlow(loc12, 0);
		if (!param6) {
			loc12 = CachingColorTransformer.transformBitmapData(loc12, new ColorTransform(0, 0, 0, 0.5, 0, 0, 0, 0));
		} else if (!param7) {
			loc12 = CachingColorTransformer.transformBitmapData(loc12,
					new ColorTransform(0.75, 0.75, 0.75, 1, 0, 0, 0, 0));
		}
		return loc12;
	}

	public static double compare(SavedCharacter param1, SavedCharacter param2) {
		double loc3 = !!Parameters.data.charIdUseMap.hasOwnProperty(param1.charId())
				? Parameters.data.charIdUseMap.get(param1.charId())
				: 0F;
		double loc4 = !!Parameters.data.charIdUseMap.hasOwnProperty(param2.charId())
				? Parameters.data.charIdUseMap.get(param2.charId())
				: 0F;
		if (loc3 != loc4) {
			return loc4 - loc3;
		}
		return param2.xp() - param1.xp();
	}

	public int charId() {
		return this.charXML.getIntAttribute("id");
	}

	public int fameBonus() {
		int loc4 = 0;
		XML loc5 = null;
		Player loc1 = Player.fromPlayerXML("", this.charXML);
		int loc2 = 0;
		int loc3 = 0;
		while (loc3 < GeneralConstants.NUM_EQUIPMENT_SLOTS) {
			if (loc1.equipment != null && loc1.equipment.length > loc3) {
				loc4 = loc1.equipment.get(loc3);
				if (loc4 != -1) {
					loc5 = ObjectLibrary.xmlLibrary.get(loc4);
					if (loc5 != null && loc5.hasOwnProperty("FameBonus")) {
						loc2 = loc2 + loc5.getIntValue("FameBonus");
					}
				}
			}
			loc3++;
		}
		return loc2;
	}

	public String name() {
		return this.name;
	}

	public int objectType() {
		return this.charXML.getIntValue("ObjectType");
	}

	public int skinType() {
		return this.charXML.getIntValue("Texture");
	}

	public int level() {
		return this.charXML.getIntValue("Level");
	}

	public int tex1() {
		return this.charXML.getIntValue("Tex1");
	}

	public int tex2() {
		return this.charXML.getIntValue("Tex2");
	}

	public int xp() {
		return this.charXML.getIntValue("Exp");
	}

	public int fame() {
		return this.charXML.getIntValue("CurrentFame");
	}

	public int hp() {
		return this.charXML.getIntValue("MaxHitPoints");
	}

	public int mp() {
		return this.charXML.getIntValue("MaxMagicPoints");
	}

	public int att() {
		return this.charXML.getIntValue("Attack");
	}

	public int def() {
		return this.charXML.getIntValue("Defense");
	}

	public int spd() {
		return this.charXML.getIntValue("Speed");
	}

	public int dex() {
		return this.charXML.getIntValue("Dexterity");
	}

	public int vit() {
		return this.charXML.getIntValue("HpRegen");
	}

	public int wis() {
		return this.charXML.getIntValue("MpRegen");
	}

	public String displayId() {
		return ObjectLibrary.typeToDisplayId.get(this.objectType());
	}

	public BitmapData getIcon(int param1) { // default = 100
		ClassesModel loc3 = ClassesModel.getInstance();
		CharacterFactory loc4 = CharacterFactory.getInstance();
		CharacterClass loc5 = loc3.getCharacterClass(this.objectType());

		CharacterSkin loc6;

		if (loc5.skins.getSkin(this.skinType()) != null) {
			loc6 = loc5.skins.getSkin(this.skinType());
		} else {
			loc6 = loc5.skins.getDefaultSkin();
		}

		BitmapData loc7 = loc4.makeIcon(loc6.template, param1, this.tex1(), this.tex2());
		return loc7;
	}

	public String bornOn() {
		if (!this.charXML.hasOwnProperty("CreationDate")) {
			return "Unknown";
		}
		return this.charXML.getValue("CreationDate");
	}

	public PetVO getPetVO() {
		return this.pet;
	}

	public void setPetVO(PetVO param1) {
		this.pet = param1;
	}

}

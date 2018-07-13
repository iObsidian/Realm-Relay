package rotmg.util;

import alde.flash.utils.Vector;
import flash.airglobal.BitmapFilterQuality;
import flash.display.BitmapData;
import flash.geom.Matrix;
import flash.utils.Dictionary;
import rotmg.util.redrawers.GlowRedrawer;
import spark.filters.GlowFilter;

import java.util.Arrays;

/**
 * This is a very close match to the original source code.
 * <p>
 * Revision of the type of the constructor third parameter may be required.
 */
public class ConditionEffect {

	public static final int NOTHING = 0;
	public static final int DEAD = 1;
	public static final int QUIET = 2;
	public static final int WEAK = 3;
	public static final int SLOWED = 4;
	public static final int SICK = 5;
	public static final int DAZED = 6;
	public static final int STUNNED = 7;
	public static final int BLIND = 8;
	public static final int HALLUCINATING = 9;
	public static final int DRUNK = 10;
	public static final int CONFUSED = 11;
	public static final int STUN_IMMUNE = 12;
	public static final int INVISIBLE = 13;
	public static final int PARALYZED = 14;
	public static final int SPEEDY = 15;
	public static final int BLEEDING = 16;
	public static final int ARMORBROKENIMMUNE = 17;
	public static final int HEALING = 18;
	public static final int DAMAGING = 19;
	public static final int BERSERK = 20;
	public static final int PAUSED = 21;
	public static final int STASIS = 22;
	public static final int STASIS_IMMUNE = 23;
	public static final int INVINCIBLE = 24;
	public static final int INVULNERABLE = 25;
	public static final int ARMORED = 26;
	public static final int ARMORBROKEN = 27;
	public static final int HEXED = 28;
	public static final int NINJA_SPEEDY = 29;
	public static final int UNSTABLE = 30;
	public static final int DARKNESS = 31;
	public static final int SLOWED_IMMUNE = 32;
	public static final int DAZED_IMMUNE = 33;
	public static final int PARALYZED_IMMUNE = 34;
	public static final int PETRIFIED = 35;
	public static final int PETRIFIED_IMMUNE = 36;
	public static final int PET_EFFECT_ICON = 37;
	public static final int CURSE = 38;
	public static final int CURSE_IMMUNE = 39;
	public static final int HP_BOOST = 40;
	public static final int MP_BOOST = 41;
	public static final int ATT_BOOST = 42;
	public static final int DEF_BOOST = 43;
	public static final int SPD_BOOST = 44;
	public static final int VIT_BOOST = 45;
	public static final int WIS_BOOST = 46;
	public static final int DEX_BOOST = 47;
	public static final int SILENCED = 48;
	public static final int EXPOSED = 49;
	public static final int GROUND_DAMAGE = 99;
	public static final int DEAD_BIT = 1 << DEAD - 1;
	public static final int QUIET_BIT = 1 << QUIET - 1;
	public static final int WEAK_BIT = 1 << WEAK - 1;
	public static final int SLOWED_BIT = 1 << SLOWED - 1;
	public static final int SICK_BIT = 1 << SICK - 1;
	public static final int DAZED_BIT = 1 << DAZED - 1;
	public static final int STUNNED_BIT = 1 << STUNNED - 1;
	public static final int BLIND_BIT = 1 << BLIND - 1;
	public static final int HALLUCINATING_BIT = 1 << HALLUCINATING - 1;
	public static final int DRUNK_BIT = 1 << DRUNK - 1;
	public static final int CONFUSED_BIT = 1 << CONFUSED - 1;
	public static final int STUN_IMMUNE_BIT = 1 << STUN_IMMUNE - 1;
	public static final int INVISIBLE_BIT = 1 << INVISIBLE - 1;
	public static final int PARALYZED_BIT = 1 << PARALYZED - 1;
	public static final int SPEEDY_BIT = 1 << SPEEDY - 1;
	public static final int BLEEDING_BIT = 1 << BLEEDING - 1;
	public static final int ARMORBROKEN_IMMUNE_BIT = 1 << ARMORBROKENIMMUNE - 1;
	public static final int HEALING_BIT = 1 << HEALING - 1;
	public static final int DAMAGING_BIT = 1 << DAMAGING - 1;
	public static final int BERSERK_BIT = 1 << BERSERK - 1;
	public static final int PAUSED_BIT = 1 << PAUSED - 1;
	public static final int STASIS_BIT = 1 << STASIS - 1;
	public static final int STASIS_IMMUNE_BIT = 1 << STASIS_IMMUNE - 1;
	public static final int INVINCIBLE_BIT = 1 << INVINCIBLE - 1;
	public static final int INVULNERABLE_BIT = 1 << INVULNERABLE - 1;
	public static final int ARMORED_BIT = 1 << ARMORED - 1;
	public static final int ARMORBROKEN_BIT = 1 << ARMORBROKEN - 1;
	public static final int HEXED_BIT = 1 << HEXED - 1;
	public static final int NINJA_SPEEDY_BIT = 1 << NINJA_SPEEDY - 1;
	public static final int UNSTABLE_BIT = 1 << UNSTABLE - 1;
	public static final int DARKNESS_BIT = 1 << DARKNESS - 1;
	public static final int NEW_CON_THREASHOLD = 32;
	public static final int SLOWED_IMMUNE_BIT = 1 << SLOWED_IMMUNE - NEW_CON_THREASHOLD;
	public static final int DAZED_IMMUNE_BIT = 1 << DAZED_IMMUNE - NEW_CON_THREASHOLD;
	public static final int PARALYZED_IMMUNE_BIT = 1 << PARALYZED_IMMUNE - NEW_CON_THREASHOLD;
	public static final int PETRIFIED_BIT = 1 << PETRIFIED - NEW_CON_THREASHOLD;
	public static final int PETRIFIED_IMMUNE_BIT = 1 << PETRIFIED_IMMUNE - NEW_CON_THREASHOLD;
	public static final int PET_EFFECT_ICON_BIT = 1 << PET_EFFECT_ICON - NEW_CON_THREASHOLD;
	public static final int CURSE_BIT = 1 << CURSE - NEW_CON_THREASHOLD;
	public static final int CURSE_IMMUNE_BIT = 1 << CURSE_IMMUNE - NEW_CON_THREASHOLD;
	public static final int HP_BOOST_BIT = 1 << HP_BOOST - NEW_CON_THREASHOLD;
	public static final int MP_BOOST_BIT = 1 << MP_BOOST - NEW_CON_THREASHOLD;
	public static final int ATT_BOOST_BIT = 1 << ATT_BOOST - NEW_CON_THREASHOLD;
	public static final int DEF_BOOST_BIT = 1 << DEF_BOOST - NEW_CON_THREASHOLD;
	public static final int SPD_BOOST_BIT = 1 << SPD_BOOST - NEW_CON_THREASHOLD;
	public static final int VIT_BOOST_BIT = 1 << VIT_BOOST - NEW_CON_THREASHOLD;
	public static final int WIS_BOOST_BIT = 1 << WIS_BOOST - NEW_CON_THREASHOLD;
	public static final int DEX_BOOST_BIT = 1 << DEX_BOOST - NEW_CON_THREASHOLD;
	public static final int SILENCED_BIT = 1 << SILENCED - NEW_CON_THREASHOLD;
	public static final int EXPOSED_BIT = 1 << EXPOSED - NEW_CON_THREASHOLD;
	public static final int MAP_FILTER_BITMASK = DRUNK_BIT | BLIND_BIT | PAUSED_BIT;
	public static final int CE_FIRST_BATCH = 0;
	public static final int CE_SECOND_BATCH = 1;
	public static final int NUMBER_CE_BATCHES = 2;
	private static final GlowFilter GLOW_FILTER = new GlowFilter(0, 0.3, 6, 6, 2, BitmapFilterQuality.LOW, false, false);
	public static Vector<ConditionEffect> effects = new Vector<>(
			new ConditionEffect("Nothing", 0, null, TextKey.CONDITIONEFFECT_NOTHING),
			new ConditionEffect("Dead", DEAD_BIT, null, TextKey.CONDITIONEFFECT_DEAD),
			new ConditionEffect("Quiet", QUIET_BIT, new int[]{32}, TextKey.CONDITIONEFFECT_QUIET),
			new ConditionEffect("Weak", WEAK_BIT, new int[]{34, 35, 36, 37}, TextKey.CONDITIONEFFECT_WEAK),
			new ConditionEffect("Slowed", SLOWED_BIT, new int[]{1}, TextKey.CONDITION_EFFECT_SLOWED),
			new ConditionEffect("Sick", SICK_BIT, new int[]{39}, TextKey.CONDITIONEFFECT_SICK),
			new ConditionEffect("Dazed", DAZED_BIT, new int[]{44}, TextKey.CONDITION_EFFECT_DAZED),
			new ConditionEffect("Stunned", STUNNED_BIT, new int[]{45}, TextKey.CONDITIONEFFECT_STUNNED),
			new ConditionEffect("Blind", BLIND_BIT, new int[]{41}, TextKey.CONDITIONEFFECT_BLIND),
			new ConditionEffect("Hallucinating", HALLUCINATING_BIT, new int[]{42}, TextKey.CONDITIONEFFECT_HALLUCINATING),
			new ConditionEffect("Drunk", DRUNK_BIT, new int[]{43}, TextKey.CONDITIONEFFECT_DRUNK),
			new ConditionEffect("Confused", CONFUSED_BIT, new int[]{2}, TextKey.CONDITIONEFFECT_CONFUSED),
			new ConditionEffect("Stun Immune", STUN_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_STUN_IMMUNE),
			new ConditionEffect("Invisible", INVISIBLE_BIT, null, TextKey.CONDITIONEFFECT_INVISIBLE),
			new ConditionEffect("Paralyzed", PARALYZED_BIT, new int[]{53, 54}, TextKey.CONDITION_EFFECT_PARALYZED),
			new ConditionEffect("Speedy", SPEEDY_BIT, new int[]{0}, TextKey.CONDITIONEFFECT_SPEEDY),
			new ConditionEffect("Bleeding", BLEEDING_BIT, new int[]{46}, TextKey.CONDITIONEFFECT_BLEEDING),
			new ConditionEffect("Armor Broken Immune", ARMORBROKEN_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_ARMOR_BROKEN_IMMUNE),
			new ConditionEffect("Healing", HEALING_BIT, new int[]{47}, TextKey.CONDITIONEFFECT_HEALING),
			new ConditionEffect("Damaging", DAMAGING_BIT, new int[]{49}, TextKey.CONDITIONEFFECT_DAMAGING),
			new ConditionEffect("Berserk", BERSERK_BIT, new int[]{50}, TextKey.CONDITIONEFFECT_BERSERK),
			new ConditionEffect("Paused", PAUSED_BIT, null, TextKey.CONDITIONEFFECT_PAUSED),
			new ConditionEffect("Stasis", STASIS_BIT, null, TextKey.CONDITIONEFFECT_STASIS),
			new ConditionEffect("Stasis Immune", STASIS_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_STASIS_IMMUNE),
			new ConditionEffect("Invincible", INVINCIBLE_BIT, null, TextKey.CONDITIONEFFECT_INVINCIBLE),
			new ConditionEffect("Invulnerable", INVULNERABLE_BIT, new int[]{17}, TextKey.CONDITIONEFFECT_INVULNERABLE),
			new ConditionEffect("Armored", ARMORED_BIT, new int[]{16}, TextKey.CONDITIONEFFECT_ARMORED),
			new ConditionEffect("Armor Broken", ARMORBROKEN_BIT, new int[]{55}, TextKey.CONDITIONEFFECT_ARMOR_BROKEN),
			new ConditionEffect("Hexed", HEXED_BIT, new int[]{42}, TextKey.CONDITIONEFFECT_HEXED),
			new ConditionEffect("Ninja Speedy", NINJA_SPEEDY_BIT, new int[]{0}, TextKey.CONDITIONEFFECT_NINJA_SPEEDY),
			new ConditionEffect("Unstable", UNSTABLE_BIT, new int[]{56}, TextKey.CONDITIONEFFECT_UNSTABLE),
			new ConditionEffect("Darkness", DARKNESS_BIT, new int[]{57}, TextKey.CONDITIONEFFECT_DARKNESS),
			new ConditionEffect("Slowed Immune", SLOWED_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_SLOWIMMUNE),
			new ConditionEffect("Dazed Immune", DAZED_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_DAZEDIMMUNE),
			new ConditionEffect("Paralyzed Immune", PARALYZED_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_PARALYZEDIMMUNE),
			new ConditionEffect("Petrify", PETRIFIED_BIT, null, TextKey.CONDITIONEFFECT_PETRIFIED),
			new ConditionEffect("Petrify Immune", PETRIFIED_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_PETRIFY_IMMUNE),
			new ConditionEffect("Pet Disable", PET_EFFECT_ICON_BIT, new int[]{27}, TextKey.CONDITIONEFFECT_STASIS, true),
			new ConditionEffect("Curse", CURSE_BIT, new int[]{58}, TextKey.CONDITIONEFFECT_CURSE),
			new ConditionEffect("Curse Immune", CURSE_IMMUNE_BIT, null, TextKey.CONDITIONEFFECT_CURSE_IMMUNE),
			new ConditionEffect("HP Boost", HP_BOOST_BIT, new int[]{32}, "HP Boost", true),
			new ConditionEffect("MP Boost", MP_BOOST_BIT, new int[]{33}, "MP Boost", true),
			new ConditionEffect("Att Boost", ATT_BOOST_BIT, new int[]{34}, "Att Boost", true),
			new ConditionEffect("Def Boost", DEF_BOOST_BIT, new int[]{35}, "Def Boost", true),
			new ConditionEffect("Spd Boost", SPD_BOOST_BIT, new int[]{36}, "Spd Boost", true),
			new ConditionEffect("Vit Boost", VIT_BOOST_BIT, new int[]{38}, "Vit Boost", true),
			new ConditionEffect("Wis Boost", WIS_BOOST_BIT, new int[]{39}, "Wis Boost", true),
			new ConditionEffect("Dex Boost", DEX_BOOST_BIT, new int[]{37}, "Dex Boost", true),
			new ConditionEffect("Silenced", SILENCED_BIT, new int[]{33}, "Silenced"),
			new ConditionEffect("Exposed", EXPOSED_BIT, new int[]{41}, "Exposed", true)
	);
	private static Dictionary<String, Integer> conditionEffectFromName = null;
	private static Vector<BitmapData> effectIconCache = null;
	private static Vector<Vector<BitmapData>> bitToIcon = null;
	private static Vector<Vector<BitmapData>> bitToIcon2 = null;

	public String name;

	public int bit;

	public int[] iconOffsets;

	public String localizationKey;

	public boolean icon16Bit;


	public ConditionEffect(String param1, int param2, int[] param3, String param4) {
		this(param1, param2, param3, param4, false);
	}

	public ConditionEffect(String name, int bit, int[] iconOffsets, String param4, boolean param5) {
		this.name = name;
		this.bit = bit;
		this.iconOffsets = iconOffsets;
		this.localizationKey = param4;
		this.icon16Bit = param5;
	}

	public static int getConditionEffectFromName(String param1) {

		if (conditionEffectFromName == null) {
			conditionEffectFromName = new Dictionary<>();

			for (ConditionEffect effect : effects) {
				conditionEffectFromName.put(effect.name, effects.indexOf(effect));
			}
		}

		if (conditionEffectFromName.get(param1) == null) {
			System.err.println("DEBUG : ERROR WITH '" + param1 + "', EFFECT DOES NOT EXIST.");
		}

		return conditionEffectFromName.get(param1);
	}

	public static ConditionEffect getConditionEffectEnumFromName(String param1) {
		for (ConditionEffect loc2 : effects) {
			if (loc2.name.equals(param1)) {
				return loc2;
			}
		}
		return null;
	}

	public static void getConditionEffectIcons(int param1, Vector<BitmapData> param2, int param3) {
		int loc4 = 0;
		int loc5 = 0;
		Vector<BitmapData> loc6 = null;
		while (param1 != 0) {
			loc4 = param1 & param1 - 1;
			loc5 = param1 ^ loc4;
			loc6 = getIconsFromBit(loc5);
			if (loc6 != null) {
				param2.add(loc6.get(param3 % loc6.length));
			}
			param1 = loc4;
		}
	}

	public static void getConditionEffectIcons2(int param1, Vector<BitmapData> param2, int param3) {
		int loc4 = 0;
		int loc5 = 0;
		Vector<BitmapData> loc6 = null;
		while (param1 != 0) {
			loc4 = param1 & param1 - 1;
			loc5 = param1 ^ loc4;
			loc6 = getIconsFromBit2(loc5);
			if (loc6 != null) {
				param2.add(loc6.get(param3 % loc6.length));
			}
			param1 = loc4;
		}
	}

	public static void addConditionEffectIcon(Vector<BitmapData> param1, int param2, boolean param3) {
		BitmapData loc4 = null;
		Matrix loc5 = null;
		Matrix loc6 = null;
		if (effectIconCache == null) {
			effectIconCache = new Vector<>();
		}
		if (effectIconCache.get(param2) != null) {
			loc4 = effectIconCache.get(param2);
		} else {
			loc5 = new Matrix();
			loc5.translate(4, 4);
			loc6 = new Matrix();
			loc6.translate(1.5, 1.5);
			if (param3) {
				loc4 = new BitmapDataSpy(18, 18, true, 0);
				loc4.draw(AssetLibrary.getImageFromSet("lofiInterfaceBig", param2), loc6);
			} else {
				loc4 = new BitmapDataSpy(16, 16, true, 0);
				loc4.draw(AssetLibrary.getImageFromSet("lofiInterface2", param2), loc5);
			}
			loc4 = GlowRedrawer.outlineGlow(loc4, 4294967295.0);
			loc4.applyFilter(loc4, loc4.rect, PointUtil.ORIGIN, GLOW_FILTER);
			effectIconCache.put(param2, loc4);
		}
		param1.add(loc4);
	}

	private static Vector<BitmapData> getIconsFromBit(int param1) {
		Matrix loc2 = null;
		int loc3 = 0;
		Vector<BitmapData> loc4 = null;
		int loc5 = 0;
		BitmapData loc6 = null;
		if (bitToIcon == null) {
			bitToIcon = new Vector<>();
			loc2 = new Matrix();
			loc2.translate(4, 4);
			loc3 = 0;
			while (loc3 < 32) {
				loc4 = null;
				if (effects.get(loc3).iconOffsets != null) {
					loc4 = new Vector<BitmapData>();
					loc5 = 0;
					while (loc5 < effects.get(loc3).iconOffsets.length) {
						loc6 = new BitmapDataSpy(16, 16, true, 0);
						loc6.draw(AssetLibrary.getImageFromSet("lofiInterface2", effects.get(loc3).iconOffsets[loc5]), loc2);
						loc6 = GlowRedrawer.outlineGlow(loc6, 4294967295.0);
						loc6.applyFilter(loc6, loc6.rect, PointUtil.ORIGIN, GLOW_FILTER);
						loc4.add(loc6);
						loc5++;
					}
				}
				bitToIcon.put(effects.get(loc3).bit, loc4);
				loc3++;
			}
		}
		return bitToIcon.get(param1);
	}

	private static Vector<BitmapData> getIconsFromBit2(int param1) {
		Vector<BitmapData> loc2 = null;
		BitmapData loc3 = null;
		Matrix loc4 = null;
		Matrix loc5 = null;
		int loc6 = 0;
		int loc7 = 0;
		if (bitToIcon2 == null) {
			bitToIcon2 = new Vector<>();
			loc2 = new Vector<BitmapData>();
			loc4 = new Matrix();
			loc4.translate(4, 4);
			loc5 = new Matrix();
			loc5.translate(1.5, 1.5);
			loc6 = 32;
			while (loc6 < effects.length) {
				loc2 = null;
				if (effects.get(loc6).iconOffsets != null) {
					loc2 = new Vector<BitmapData>();
					loc7 = 0;
					while (loc7 < effects.get(loc6).iconOffsets.length) {
						if (effects.get(loc6).icon16Bit) {
							loc3 = new BitmapDataSpy(18, 18, true, 0);
							loc3.draw(AssetLibrary.getImageFromSet("lofiInterfaceBig", effects.get(loc6).iconOffsets[loc7]), loc5);
						} else {
							loc3 = new BitmapDataSpy(16, 16, true, 0);
							loc3.draw(AssetLibrary.getImageFromSet("lofiInterface2", effects.get(loc6).iconOffsets[loc7]), loc4);
						}
						loc3 = GlowRedrawer.outlineGlow(loc3, 4294967295.0);
						loc3.applyFilter(loc3, loc3.rect, PointUtil.ORIGIN, GLOW_FILTER);
						loc2.add(loc3);
						loc7++;
					}
				}
				bitToIcon2.put(effects.get(loc6).bit, loc2);
				loc6++;
			}
		}
		if (bitToIcon2 != null && bitToIcon2.get(param1) != null) {
			return bitToIcon2.get(param1);
		}
		return null;
	}

	@Override
	public String toString() {
		return "ConditionEffect{" +
				"name='" + name + '\'' +
				", bit=" + bit +
				", iconOffsets=" + Arrays.toString(iconOffsets) +
				", localizationKey='" + localizationKey + '\'' +
				", icon16Bit=" + icon16Bit +
				'}';
	}


}

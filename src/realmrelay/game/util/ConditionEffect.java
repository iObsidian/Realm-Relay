package realmrelay.game.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a very close match to the original source code.
 *
 * Revision of the type of the constructor third parameter may be required.
 *
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
    public static final int MAP_FILTER_BITMASK = DRUNK_BIT | BLIND_BIT | PAUSED_BIT;
    public static final int CE_FIRST_BATCH = 0;
    public static final int CE_SECOND_BATCH = 1;
    public static final int NUMBER_CE_BATCHES = 2;

    public ConditionEffect(String param1, int param2, int[] param3, String param4) {
        this(param1, param2, param3, param4, false);
    }

    public ConditionEffect(String param1, int param2, int[] param3, String param4, boolean param5) {
        super();
        this.name = param1;
        this.bit = param2;
        this.iconOffsets = param3;
        this.localizationKey = param4;
        this.icon16Bit = param5;
    }

    public String name;
    public int bit;
    public int[] iconOffsets;
    public String localizationKey;
    public Boolean icon16Bit;


    //@formatter:off
    public static ConditionEffect[] effects = new ConditionEffect[]{
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
            new ConditionEffect("Silenced", SILENCED_BIT, new int[]{33}, "Silenced")};

    private static Map<String, Integer> conditionEffectFromName = new HashMap<>();
    private static Object effectIconCache = null;
    private static Object bitToIcon = null;
    private static Object bitToIcon2 = null;

    public static int getConditionEffectFromName(String param1) {
        if (conditionEffectFromName == null) {
            conditionEffectFromName = new HashMap<>();
            int _loc2 = 0;
            while (_loc2 < effects.length) {
                conditionEffectFromName.put(effects[_loc2].name, _loc2);
                _loc2++;
            }
        }
        return conditionEffectFromName.get(param1);
    }

}

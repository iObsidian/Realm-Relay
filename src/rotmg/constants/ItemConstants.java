package rotmg.constants;

import flash.display.BitmapData;
import rotmg.util.AssetLibrary;

public class ItemConstants {

    public static final int NO_ITEM = -1;
    public static final int ALL_TYPE = 0;
    public static final int SWORD_TYPE = 1;
    public static final int DAGGER_TYPE = 2;
    public static final int BOW_TYPE = 3;
    public static final int TOME_TYPE = 4;
    public static final int SHIELD_TYPE = 5;
    public static final int LEATHER_TYPE = 6;
    public static final int PLATE_TYPE = 7;
    public static final int WAND_TYPE = 8;
    public static final int RING_TYPE = 9;
    public static final int POTION_TYPE = 10;
    public static final int SPELL_TYPE = 11;
    public static final int SEAL_TYPE = 12;
    public static final int CLOAK_TYPE = 13;
    public static final int ROBE_TYPE = 14;
    public static final int QUIVER_TYPE = 15;
    public static final int HELM_TYPE = 16;
    public static final int STAFF_TYPE = 17;
    public static final int POISON_TYPE = 18;
    public static final int SKULL_TYPE = 19;
    public static final int TRAP_TYPE = 20;
    public static final int ORB_TYPE = 21;
    public static final int PRISM_TYPE = 22;
    public static final int SCEPTER_TYPE = 23;
    public static final int KATANA_TYPE = 24;
    public static final int SHURIKEN_TYPE = 25;
    public static final int EGG_TYPE = 26;

    public static String itemTypeToName(int param1) {
        switch (param1) {
            case ALL_TYPE:
                return "EquipmentType.Any";
            case SWORD_TYPE:
                return "EquipmentType.Sword";
            case DAGGER_TYPE:
                return "EquipmentType.Dagger";
            case BOW_TYPE:
                return "EquipmentType.Bow";
            case TOME_TYPE:
                return "EquipmentType.Tome";
            case SHIELD_TYPE:
                return "EquipmentType.Shield";
            case LEATHER_TYPE:
                return "EquipmentType.LeatherArmor";
            case PLATE_TYPE:
                return "EquipmentType.Armor";
            case WAND_TYPE:
                return "EquipmentType.Wand";
            case RING_TYPE:
                return "EquipmentType.Accessory";
            case POTION_TYPE:
                return "EquipmentType.Potion";
            case SPELL_TYPE:
                return "EquipmentType.Spell";
            case SEAL_TYPE:
                return "EquipmentType.HolySeal";
            case CLOAK_TYPE:
                return "EquipmentType.Cloak";
            case ROBE_TYPE:
                return "EquipmentType.Robe";
            case QUIVER_TYPE:
                return "EquipmentType.Quiver";
            case HELM_TYPE:
                return "EquipmentType.Helm";
            case STAFF_TYPE:
                return "EquipmentType.Staff";
            case POISON_TYPE:
                return "EquipmentType.Poison";
            case SKULL_TYPE:
                return "EquipmentType.Skull";
            case TRAP_TYPE:
                return "EquipmentType.Trap";
            case ORB_TYPE:
                return "EquipmentType.Orb";
            case PRISM_TYPE:
                return "EquipmentType.Prism";
            case SCEPTER_TYPE:
                return "EquipmentType.Scepter";
            case KATANA_TYPE:
                return "EquipmentType.Katana";
            case SHURIKEN_TYPE:
                return "EquipmentType.Shuriken";
            case EGG_TYPE:
                return "EquipmentType.Any";
            default:
                return "EquipmentType.InvalidType";
        }
    }

    public static BitmapData itemTypeToBaseSprite(int param1) {
        BitmapData loc2 = null;
        switch (param1) {
            case ALL_TYPE:
                break;
            case SWORD_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 48);
                break;
            case DAGGER_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 96);
                break;
            case BOW_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 80);
                break;
            case TOME_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 80);
                break;
            case SHIELD_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 112);
                break;
            case LEATHER_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 0);
                break;
            case PLATE_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 32);
                break;
            case WAND_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 64);
                break;
            case RING_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj", 44);
                break;
            case SPELL_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 64);
                break;
            case SEAL_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 160);
                break;
            case CLOAK_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 32);
                break;
            case ROBE_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 16);
                break;
            case QUIVER_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 48);
                break;
            case HELM_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 96);
                break;
            case STAFF_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj5", 112);
                break;
            case POISON_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 128);
                break;
            case SKULL_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 0);
                break;
            case TRAP_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 16);
                break;
            case ORB_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 144);
                break;
            case PRISM_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 176);
                break;
            case SCEPTER_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj6", 192);
                break;
            case KATANA_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj3", 540);
                break;
            case SHURIKEN_TYPE:
                loc2 = AssetLibrary.getImageFromSet("lofiObj3", 555);
        }
        return loc2;
    }


}

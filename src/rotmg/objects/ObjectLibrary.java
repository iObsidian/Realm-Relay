package rotmg.objects;

import flash.display.BitmapData;
import rotmg.constants.GeneralConstants;
import rotmg.constants.ItemConstants;
import rotmg.game._as3.XML;
import rotmg.game.constants.GeneralConstants;
import rotmg.game.constants.ItemConstants;
import rotmg.game.messaging.data.StatData;
import rotmg.game.net.impl.Message;
import rotmg.game.objects.animation.AnimationsData;
import rotmg.game.util.AssetLibrary;
import rotmg.game.util.ConversionUtil;
import rotmg.game._as3.BitmapData;
import rotmg.messaging.data.StatData;
import rotmg.objects.animation.AnimationsData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is 80% complete
 * <p>
 * A lot of objects are missing and the makeClass() function isnt implemented.
 */
public class ObjectLibrary {

	public static TextureDataFactory textureDataFactory = new TextureDataFactory();
	public static final String IMAGE_SET_NAME = "lofiObj3";
	public static final int IMAGE_ID = 255;
	public static List<XML> playerChars = new ArrayList<XML>();
	public static List<XML> hexTransforms = new ArrayList<XML>();
	public static Map<Object, Object> playerClassAbbr = new HashMap<>();
	public static final Map<Integer, ObjectProperties> propsLibrary = new HashMap<>(); //objectType
	public static final Map<Object, XML> xmlLibrary = new HashMap<>(); //objectType, objectXML
	public static final Map<Integer, XML> setLibrary = new HashMap<>(); //type, set
	public static final Map<String, Integer> idToType = new HashMap<>(); //id, objectType
	public static final Map<Integer, String> typeToDisplayId = new HashMap<>(); //objectType, displayId
	public static final Map<Integer, TextureData> typeToTextureData = new HashMap<>();
	public static final Map<Integer, TextureData> typeToTopTextureData = new HashMap<>(); //ObjectType, TextureData
	public static final Map<Integer, AnimationsData> typeToAnimationsData = new HashMap<>();
	public static final Map<Integer, XML> petXMLDataLibrary = new HashMap<>(); //ObjectType, XML
	public static final Map<Object, Object> skinSetXMLDataLibrary = new HashMap<>();
	public static final Map<String, HashMap<Integer, XML>> dungeonsXMLLibrary = new HashMap<>();
	public static final String ENEMY_FILTER_LIST[] = new String[] { "None", "Hp", "Defense" };
	public static final String TILE_FILTER_LIST[] = new String[] { "ALL", "Walkable", "Unwalkable", "Slow", "Speed=1" };
	public static final ObjectProperties defaultProps = new ObjectProperties(null);

	public static Map<String, Class> TYPE_MAP = new HashMap<String, Class>();

	static {
		// TYPE_MAP.put("ArenaGuard", ArenaGuard.class);
		// TYPE_MAP.put("ArenaPortal", ArenaPortal);
		// TYPE_MAP.put("CaveWall", CaveWall);
		TYPE_MAP.put("Character", Character.class);
		// TYPE_MAP.put("CharacterChanger", CharacterChanger);
		// TYPE_MAP.put("ClosedGiftChest", ClosedGiftChest);
		// TYPE_MAP.put("ClosedVaultChest", ClosedVaultChest);
		// TYPE_MAP.put("ConnectedWall", ConnectedWall);
		// TYPE_MAP.put("Container", Container);
		//TYPE_MAP.put("DoubleWall", DoubleWall);
		// TYPE_MAP.put("FortuneGround", FortuneGround);
		// TYPE_MAP.put("FortuneTeller", FortuneTeller);
		// TYPE_MAP.put("GameObject", GameObject.class);
		// TYPE_MAP.put("GuildBoard", GuildBoard);
		// TYPE_MAP.put("GuildChronicle", GuildChronicle);
		// TYPE_MAP.put("GuildHallPortal", GuildHallPortal);
		// TYPE_MAP.put("GuildMerchant", GuildMerchant);
		//TYPE_MAP.put("GuildRegister", GuildRegister);
		//TYPE_MAP.put("Merchant", Merchant);
		// TYPE_MAP.put("MoneyChanger", MoneyChanger);
		// TYPE_MAP.put("MysteryBoxGround", MysteryBoxGround);
		// TYPE_MAP.put("NameChanger", NameChanger);
		// TYPE_MAP.put("ReskinVendor", ReskinVendor);
		// TYPE_MAP.put("OneWayContainer", OneWayContainer);
		TYPE_MAP.put("Player", Player.class);
		// TYPE_MAP.put("Portal", Portal);
		// TYPE_MAP.put("Projectile", Projectile);
		// TYPE_MAP.put("QuestRewards", QuestRewards);
		// TYPE_MAP.put("DailyLoginRewards", DailyLoginRewards);
		// TYPE_MAP.put("Sign", Sign);
		// TYPE_MAP.put("SpiderWeb", SpiderWeb);
		// TYPE_MAP.put("Stalagmite", Stalagmite);
		// TYPE_MAP.put("Wall", Wall);
		// TYPE_MAP.put("Pet", Pet);
		// TYPE_MAP.put("PetUpgrader", PetUpgrader);
		// TYPE_MAP.put("YardUpgrader", YardUpgrader);
	}

	private static String currentDungeon = "";

	public static void parseDungeonXML(String param1, XML objectXML) {
		currentDungeon = param1;

		dungeonsXMLLibrary.put(currentDungeon, new HashMap<>());
		parseFromXML(objectXML);
	}

	public static void parseFromXML(XML objectXML) {
		String id = objectXML.getAttribute("id");
		String displayId = id;
		if (objectXML.hasOwnProperty("DisplayId")) {
			displayId = objectXML.getValue("DisplayId");
		}
		if (objectXML.hasOwnProperty("Group")) {
			if (objectXML.getValue("Group").equals("Hexable")) {
				hexTransforms.add(objectXML);
			}
		}
		int objectType = objectXML.getIntAttribute("type");

		if (objectXML.hasOwnProperty("PetBehavior") || objectXML.hasOwnProperty("PetAbility")) {
			petXMLDataLibrary.put(objectType, objectXML);
		} else {

			propsLibrary.put(objectType, new ObjectProperties(objectXML));
			xmlLibrary.put(objectType, objectXML);
			idToType.put(id, objectType);
			typeToDisplayId.put(objectType, displayId);

			if (!currentDungeon.equals("") && dungeonsXMLLibrary.get(currentDungeon) != null) {
				dungeonsXMLLibrary.get(currentDungeon).put(objectType, objectXML);
				propsLibrary.get(objectType).belonedDungeon = currentDungeon;
			}

			if (objectXML.hasOwnProperty("Class") && objectXML.getValue("Class").equals("Player")) {
				playerClassAbbr.put(objectType, objectXML.getAttribute("id").substring(0, 2));
				boolean found = false;

				for (XML player : playerChars) {
					if (player.getIntAttribute("type") == objectType) {
						playerChars.add(objectXML);
						found = true;
					}
				}

				if (!found) {
					playerChars.add(objectXML);
				}
			}

			if (objectXML.hasOwnProperty("Animation")) {
				typeToAnimationsData.put(objectType, new AnimationsData(objectXML));
			}

			typeToTextureData.put(objectType, textureDataFactory.create(objectXML));
			if (objectXML.hasOwnProperty("Top")) {
				typeToTopTextureData.put(objectType, textureDataFactory.create(objectXML.getChild("Top")));
			}

		}

	}

	public static String getIdFromType(int type) {
		XML _loc2 = xmlLibrary.get(type);
		if (_loc2 == null) {
			return null;
		}
		return _loc2.getAttribute("id");
	}

	public static XML getSetXMLFromType(int param1) {
		XML loc2 = null;
		int loc3 = 0;
		if (setLibrary.get(param1) != null) {
			return setLibrary.get(param1);
		}
		/**for (loc2 in EmbeddedData.skinsEquipmentSetsXML.EquipmentSet) {
		 loc3 = loc2.getValueAsInt("type");
		 setLibrary.put(loc3, loc2);
		 }*/
		return setLibrary.get(param1);
	}

	public static ObjectProperties getPropsFromId(String param1) {
		int objectType = idToType.get(param1);
		return propsLibrary.get(objectType);
	}

	public static XML getXMLfromId(String param1) {
		int objectType = idToType.get(param1);
		return xmlLibrary.get(objectType);
	}

	public static GameObject getObjectFromType(int param1) {
		XML objectXML = null;
		String typeReference = null;
		int objectType = param1;
		try {
			objectXML = xmlLibrary.get(objectType);
			typeReference = objectXML.getValue("Class");

			System.out.println("Class : " + " Object type : " + objectType + "typeReference : " + typeReference);
			System.out.println(xmlLibrary.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * Things get funky here.
		 * We're not using reflection here.
		 */

		Class typeClass;

		if (TYPE_MAP.get(typeReference) != null) {
			typeClass = TYPE_MAP.get(typeReference);
		} else {
			typeClass = makeClass(typeReference);
		}

		if (typeClass != null) {
			 
		} else {
			System.out.println("FATAL : Null typeClass!");
		}

		System.err.println("Error with instantiation of object class '" + typeReference + "'.");

		return null;
	}

	private static Class makeClass(String className) {
		return TYPE_MAP.get(className);
	}

	public static BitmapData getTextureFromType(int param1) {
		TextureData _loc2 = typeToTextureData.get(param1);
		if (_loc2 == null) {
			return null;
		}
		return _loc2.getTexture();
	}

	public static BitmapData getBitmapData(int param1) {
		TextureData _loc2 = typeToTextureData.get(param1);
		BitmapData loc3 = _loc2.getTexture();
		if (loc3 != null) {
			return loc3;
		}
		return AssetLibrary.getImageFromSet(IMAGE_SET_NAME, IMAGE_ID);
	}

	/*public static BitmapData getRedrawnTextureFromType(int param1, int param2, boolean param3, boolean =true param4, double =5param5) {
	    BitmapData _loc6 = getBitmapData(param1);
	    if (Parameters.itemTypes16.indexOf(param1) != -1 || _loc6_.height == 16) {
	        param2 = param2 * 0.5;
	    }
	    TextureData _loc7 = typeToTextureData[param1];
	    var _loc8:BitmapData = !!_loc7_ ? _loc7_.mask_ : null;
	    if (_loc8_ == null) {
	        return TextureRedrawer.redraw(_loc6_, param2, param3, 0, param4, param5);
	    }
	    XML _loc9 = xmlLibrary[param1];
	    var _loc10:int =!!_loc9_.hasOwnProperty("Tex1") ? int(int(_loc9_.Tex1)) :0;
	    var _loc11:int =!!_loc9_.hasOwnProperty("Tex2") ? int(int(_loc9_.Tex2)) :0;
	    _loc6_ = TextureRedrawer.resize(_loc6_, _loc8_, param2, param3, _loc10_, _loc11_, param5);
	    _loc6_ = GlowRedrawer.outlineGlow(_loc6_, 0);
	    return _loc6_;
	}**/

	public static int getSizeFromType(int param1) {
		XML _loc2 = xmlLibrary.get(param1);
		if (!_loc2.hasOwnProperty("Size")) {
			return 100;
		}
		return _loc2.getIntValue("Size");
	}

	public static int getSlotTypeFromType(int param1) {
		XML _loc2 = xmlLibrary.get(param1);
		if (!_loc2.hasOwnProperty("SlotType")) {
			return -1;
		}
		return _loc2.getIntValue("SlotType");
	}

	public static boolean isEquippableByPlayer(int param1, Player param2) {
		if (param1 == ItemConstants.NO_ITEM) {
			return false;
		}
		XML xml = xmlLibrary.get(param1);
		int slotType = xml.getIntValue("SlotType");
		int i = 0;
		while (i < GeneralConstants.NUM_EQUIPMENT_SLOTS) {
			if (param2.slotTypes[i] == slotType) {
				return true;
			}
			i++;
		}
		return false;
	}

	public static int getMatchingSlotIndex(int param1, Player player) {
		XML _loc3;
		int _loc4;
		int _loc5;
		if (param1 != ItemConstants.NO_ITEM) {
			_loc3 = xmlLibrary.get(param1);
			_loc4 = _loc3.getIntValue("SlotType");
			_loc5 = 0;
			while (_loc5 < GeneralConstants.NUM_EQUIPMENT_SLOTS) {
				if (player.slotTypes[_loc5] == _loc4) {
					return _loc5;
				}
				_loc5++;
			}
		}
		return -1;
	}

	public static boolean isUsableByPlayer(int objectType, Player player) {
		if (player == null || player.slotTypes == null) {
			return true;
		}
		XML objectXML = xmlLibrary.get(objectType);
		if (objectXML == null || !objectXML.hasOwnProperty("SlotType")) {
			return false;
		}
		int slotType = objectXML.getIntValue("SlotType");
		if (slotType == ItemConstants.POTION_TYPE || slotType == ItemConstants.EGG_TYPE) {
			return true;
		}

		for (int i : player.slotTypes) {
			if (i == slotType) {
				return true;
			}
		}

		return false;
	}

	public static boolean isDropTradable(int objectType) {
		XML objectXML = xmlLibrary.get(objectType);
		return objectXML != null && objectXML.hasOwnProperty("DropTradable");
	}

	public static boolean isSoulbound(int objectType) {
		XML objectXML = xmlLibrary.get(objectType);
		return objectXML != null && objectXML.hasOwnProperty("Soulbound");
	}

	/**
	 * Players that can use the object
	 */
	public static List<String> usableBy(int objectType) {
		int i = 0;
		XML objectXML = xmlLibrary.get(objectType);
		if (objectXML == null || !objectXML.hasOwnProperty("SlotType")) {
			return null;
		}
		int slotType = objectXML.getIntValue("SlotType");
		if (slotType == ItemConstants.POTION_TYPE || slotType == ItemConstants.RING_TYPE) {
			return null;
		}
		List<String> usable = new ArrayList<String>();
		for (XML playerXML : playerChars) {
			for (int s : ConversionUtil.toIntVector(playerXML.getValue("SlotTypes"))) {
				if (s == slotType) {
					usable.add(typeToDisplayId.get(playerXML.getIntAttribute("type")));
				}
				break;
			}
		}
		return usable;
	}

	public static boolean playerMeetsRequirements(int objectType, Player param2) {
		if (param2 == null) {
			return true;
		}
		XML objectXML = xmlLibrary.get(objectType);
		for (XML xml : objectXML.getChilds("EquipRequirement")) {
			if (!playerMeetsRequirement(xml, param2)) {
				return false;
			}
		}
		return true;
	}

	public static boolean playerMeetsRequirement(XML reqXML, Player player) {
		int val = 0;
		if (reqXML.toString().equals("Stat")) {
			val = reqXML.getIntAttribute("value");
			switch (reqXML.getIntAttribute("stat")) {
			case StatData.MAX_HP_STAT:
				return player.maxHP >= val;
			case StatData.MAX_MP_STAT:
				return player.maxMP >= val;
			case StatData.LEVEL_STAT:
				return player.level >= val;
			case StatData.ATTACK_STAT:
				return player.attack >= val;
			case StatData.DEFENSE_STAT:
				return player.defense >= val;
			case StatData.SPEED_STAT:
				return player.speed >= val;
			case StatData.VITALITY_STAT:
				return player.vitality >= val;
			case StatData.WISDOM_STAT:
				return player.wisdom >= val;
			case StatData.DEXTERITY_STAT:
				return player.dexterity >= val;
			}
		}
		return false;
	}

	public static XML getPetDataXMLByType(int param1) {
		return petXMLDataLibrary.get(param1);
	}
}
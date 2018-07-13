package rotmg.objects;

import alde.flash.utils.XML;
import flash.display.BitmapData;
import flash.utils.Dictionary;
import rotmg.constants.GeneralConstants;
import rotmg.constants.ItemConstants;
import rotmg.messaging.data.StatData;
import rotmg.objects.animation.AnimationsData;
import rotmg.parameters.Parameters;
import rotmg.util.AssetLibrary;
import rotmg.util.ConversionUtil;
import rotmg.util.TextureRedrawer;
import rotmg.util.redrawers.GlowRedrawer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * This is 80% complete
 * <p>
 * A lot of objects are missing and the makeClass() function isnt implemented.
 */
public class ObjectLibrary {

	public static final String IMAGE_SET_NAME = "lofiObj3";
	public static final int IMAGE_ID = 255;
	public static final Dictionary<Integer, ObjectProperties> propsLibrary = new Dictionary<>(); //objectType
	public static final Dictionary<Object, XML> xmlLibrary = new Dictionary<>(); //objectType, objectXML
	public static final Dictionary<Integer, XML> setLibrary = new Dictionary<>(); //type, set
	public static final Dictionary<String, Integer> idToType = new Dictionary<>(); //id, objectType
	public static final Dictionary<Integer, String> typeToDisplayId = new Dictionary<>(); //objectType, displayId
	public static final Dictionary<Integer, TextureData> typeToTextureData = new Dictionary<>();
	public static final Dictionary<Integer, TextureData> typeToTopTextureData = new Dictionary<>(); //ObjectType, TextureData
	public static final Dictionary<Integer, AnimationsData> typeToAnimationsData = new Dictionary<>();
	public static final Dictionary<Integer, XML> petXMLDataLibrary = new Dictionary<>(); //ObjectType, XML
	public static final Dictionary<Object, Object> skinSetXMLDataLibrary = new Dictionary<>();
	public static final Dictionary<String, Dictionary<Integer, XML>> dungeonsXMLLibrary = new Dictionary<>();
	public static final String ENEMY_FILTER_LIST[] = new String[]{"None", "Hp", "Defense"};
	public static final String TILE_FILTER_LIST[] = new String[]{"ALL", "Walkable", "Unwalkable", "Slow", "Speed=1"};
	public static final ObjectProperties defaultProps = new ObjectProperties(null);
	public static TextureDataFactory textureDataFactory = new TextureDataFactory();
	public static List<XML> playerChars = new ArrayList<XML>();
	public static List<XML> hexTransforms = new ArrayList<XML>();
	public static Dictionary<Object, Object> playerClassAbbr = new Dictionary<>();
	public static Dictionary<String, Class> TYPE_MAP = new Dictionary<String, Class>();
	private static String currentDungeon = "";

	static {
		/*TYPE_MAP.put("ArenaGuard", ArenaGuard.class);
		TYPE_MAP.put("ArenaPortal", ArenaPortal.class);
		TYPE_MAP.put("CaveWall", CaveWall.class);*/
		TYPE_MAP.put("Character", Character.class);
		/*TYPE_MAP.put("CharacterChanger", CharacterChanger.class);
		TYPE_MAP.put("ClosedGiftChest", ClosedGiftChest.class);
		TYPE_MAP.put("ClosedVaultChest", ClosedVaultChest.class);
		TYPE_MAP.put("ConnectedWall", ConnectedWall.class);*/
		TYPE_MAP.put("Container", Container.class);
		/*TYPE_MAP.put("DoubleWall", DoubleWall.class);
		TYPE_MAP.put("FortuneGround", FortuneGround.class);
		TYPE_MAP.put("FortuneTeller", FortuneTeller);*/
		TYPE_MAP.put("GameObject", GameObject.class);
		/*TYPE_MAP.put("GuildBoard", GuildBoard.class);
		TYPE_MAP.put("GuildChronicle", GuildChronicle.class);
		TYPE_MAP.put("GuildHallPortal", GuildHallPortal.class);
		TYPE_MAP.put("GuildMerchant", GuildMerchant.class);
		TYPE_MAP.put("GuildRegister", GuildRegister.class);*/
		TYPE_MAP.put("Merchant", Merchant.class);
		/*TYPE_MAP.put("MoneyChanger", MoneyChanger.class);
		TYPE_MAP.put("MysteryBoxGround", MysteryBoxGround.class);*/
		TYPE_MAP.put("NameChanger", NameChanger.class);
		/*TYPE_MAP.put("ReskinVendor", ReskinVendor.class);
		TYPE_MAP.put("OneWayContainer", OneWayContainer.class);*/
		TYPE_MAP.put("Player", Player.class);
		TYPE_MAP.put("Portal", Portal.class);
		TYPE_MAP.put("Projectile", Projectile.class);
		/*TYPE_MAP.put("QuestRewards", QuestRewards.class);
		TYPE_MAP.put("DailyLoginRewards", DailyLoginRewards.class);
		TYPE_MAP.put("Sign", Sign.class);
		TYPE_MAP.put("SpiderWeb", SpiderWeb.class);
		TYPE_MAP.put("Stalagmite", Stalagmite.class);*/
		TYPE_MAP.put("Wall", Wall.class);
		TYPE_MAP.put("Pet", Pet.class);
		/*TYPE_MAP.put("PetUpgrader", PetUpgrader.class);
		TYPE_MAP.put("YardUpgrader", YardUpgrader.class);*/
	}

	public static void parseDungeonXML(String param1, XML objectXML) {
		currentDungeon = param1;

		dungeonsXMLLibrary.put(currentDungeon, new Dictionary<>());
		parseFromXML(objectXML);
	}

	public static void parseFromXML(XML objects) {

		for (XML objectXML : objects.children("Object")) {

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

				System.out.println("ObjectType : " + objectType);

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

				typeToTextureData.put(objectType, textureDataFactory.create(objectXML));
				if (objectXML.hasOwnProperty("Top")) {
					typeToTopTextureData.put(objectType, textureDataFactory.create(objectXML.child("Top")));
				}

				if (objectXML.hasOwnProperty("Animation")) {
					typeToAnimationsData.put(objectType, new AnimationsData(objectXML));
				}
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
		/*for (loc2 in EmbeddedData.skinsEquipmentSetsXML.EquipmentSet) {
		 loc3 = loc2.getValueAsInt("type");
		 setLibrary.put(loc3, loc2);
		 }*/
		return setLibrary.get(param1);
	}

	public static ObjectProperties getPropsFromId(String param1) {
		int objectType = idToType.get(param1);
		System.out.println(objectType + ", " + param1);

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

			/*System.out.println("Class : '" + typeReference + "' Object type : '" + objectType + "'.");
			System.out.println(xmlLibrary.size());*/

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
			Class[] cArg = new Class[1]; //Our constructor has 3 arguments
			cArg[0] = XML.class; //First argument is of *object* type Long

			try {
				return (GameObject) typeClass.getDeclaredConstructor(cArg).newInstance(objectXML);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();

			}
		} else {
			System.out.println("Error with creating class " + typeReference + "...");
		}

		System.err.println("Error with instantiation of object class '" + typeReference + "', '" + typeClass + "'.");

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


	public static BitmapData getRedrawnTextureFromType(int param1, int param2, boolean param3, boolean param4) {
		return getRedrawnTextureFromType(param1, param2, param3, param4);
	}

	public static BitmapData getRedrawnTextureFromType(int param1, int param2, boolean param3) {
		return getRedrawnTextureFromType(param1, param2, param3, true, 5);
	}

	public static BitmapData getRedrawnTextureFromType(int param1, int param2, boolean param3, boolean param4, double param5) {
		BitmapData loc6 = getBitmapData(param1);
		if (Parameters.itemTypes16.contains(param1) || loc6.height == 16) {
			param2 = (int) (param2 * 0.5);
		}
		TextureData loc7 = typeToTextureData.get(param1);
		BitmapData loc8 = loc7 != null ? loc7.mask : null;
		if (loc8 == null) {
			return TextureRedrawer.redraw(loc6, param2, param3, 0);
		}
		XML loc9 = xmlLibrary.get(param1);
		int loc10 = loc9.hasOwnProperty("Tex1") ? loc9.getIntValue("Tex1") : 0;
		int loc11 = loc9.hasOwnProperty("Tex2") ? loc9.getIntValue("Tex2") : 0;
		loc6 = TextureRedrawer.resize(loc6, loc8, param2, param3, loc10, loc11, param5);
		loc6 = GlowRedrawer.outlineGlow(loc6, 0);
		return loc6;
	}

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
			if (param2.slotTypes.get(i) == slotType) {
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
				if (player.slotTypes.get(_loc5) == _loc4) {
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
		for (XML xml : objectXML.children("EquipRequirement")) {
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
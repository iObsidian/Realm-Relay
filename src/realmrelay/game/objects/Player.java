package realmrelay.game.objects;

import realmrelay.game._as3.Signal;
import realmrelay.game._as3.XML;
import realmrelay.game.assets.services.CharacterFactory;
import realmrelay.game.constants.GeneralConstants;
import realmrelay.game.messaging.data.StatData;
import realmrelay.game.objects.animation.AnimatedChar;
import realmrelay.game.particles.HealingEffect;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.util.ConversionUtil;
import realmrelay.game.util.IntPoint;
import realmrelay.packets.data.unused.BitmapData;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class Player extends Character {

	public static final int MS_BETWEEN_TELEPORT = 10000;
	public static final int MS_REALM_TELEPORT = 120000;

	private static final double MOVE_THRESHOLD = 0.4;
	private static final Point[] NEARBY = new Point[]{new Point(0, 0), new Point(1, 0), new Point(0, 1),
			new Point(1, 1)};
	private static final int[] RANK_OFFSET_MATRIX = new int[]{1, 0, 0, 1, 2, 2};
	private static final int[] NAME_OFFSET_MATRIX = new int[]{1, 0, 0, 1, 20, 1};
	private static final double MIN_MOVE_SPEED = 0.004;
	private static final double MAX_MOVE_SPEED = 0.0096;
	private static final double MIN_ATTACK_FREQ = 0.0015;
	private static final double MAX_ATTACK_FREQ = 0.008;
	private static final double MIN_ATTACK_MULT = 0.5;
	private static final double MAX_ATTACK_MULT = 2;
	public static boolean isAdmin = false;
	public static boolean isMod = false;
	private static Point newP = new Point();

	public static final int SEARCH_LOOT_FREQ = 20;
	public static final double MAX_LOOT_DIST = 1;
	public static final int VAULT_CHEST = 1284;
	public static final int HEALTH_POT = 2594;
	public static final int MAGIC_POT = 2595;
	public static final int MAX_STACK_POTS = 6;
	public static final int MIN_WAIT_TIME = 550;
	public static final int MAX_WAIT_TIME = 1300;
	public static final int ABILITY_MIN_TIER = 5;
	public static final int RING_MIN_TIER = 4;
	public static final int WEAP_ARMOR_MIN_TIER = 10;
	public static final int HEALTH_SLOT = 254;
	public static final int MAGIC_SLOT = 255;
	public static List<Integer> wantedList = null;
	public static int lastSearchTime;
	public static int lastLootTime;
	public static int last_hpStack = -1;
	public static int last_mpStack = -1;
	public static List<Integer> last_pInv;
	public static boolean enableAutoLoot = true;
	public static boolean enableTeleportLoot = false;
	public static boolean enableSafeLoot = true;

	public int xpTimer;
	public int skinId;
	public AnimatedChar skin;
	public boolean isShooting;
	public Signal creditsWereChanged;
	public Signal fameWasChanged;
	private BitmapData famePortrait = null;
	public int lastSwap = -1;
	public String accountId = "";
	public int credits = 0;
	public int tokens = 0;
	public int numStars = 0;
	public int fame = 0;
	public boolean nameChosen = false;
	public int currFame = -1;
	public int nextClassQuestFame = -1;
	public int legendaryRank = -1;
	public String guildName = null;
	public int guildRank = -1;
	public boolean isFellowGuild = false;
	public int breath = -1;
	public int maxMP = 200;
	public double mp = 0;
	public int nextLevelExp = 1000;
	public int exp = 0;
	public int attack = 0;
	public int speed = 0;
	public int dexterity = 0;
	public int vitality = 0;
	public int wisdom = 0;
	public int maxHPBoost = 0;
	public int maxMPBoost = 0;
	public int attackBoost = 0;
	public int defenseBoost = 0;
	public int speedBoost = 0;
	public int vitalityBoost = 0;
	public int wisdomBoost = 0;
	public int dexterityBoost = 0;
	public int xpBoost = 0;
	public int healthPotionCount = 0;
	public int magicPotionCount = 0;
	public int attackMax = 0;
	public int defenseMax = 0;
	public int speedMax = 0;
	public int dexterityMax = 0;
	public int vitalityMax = 0;
	public int wisdomMax = 0;
	public int maxHPMax = 0;
	public int maxMPMax = 0;
	public boolean hasBackpack = false;
	public boolean starred = false;
	public boolean ignored = false;
	public double distSqFromThisPlayer = 0;
	public int attackPeriod = 0;
	public int nextAltAttack = 0;
	public int nextTeleportAt = 0;
	public int dropBoost = 0;
	public int tierBoost = 0;
	public boolean isDefaultAnimatedChar = true;
	public String projectileIdSetOverrideNew = "";
	public String projectileIdSetOverrideOld = "";
	protected double rotate = 0;
	protected Point relMoveVec = null;
	protected double moveMultiplier = 1;


	protected HealingEffect healingEffect = null;
	public Merchant nearestMerchant = null;
	private AddTextLineSignal addTextLine;
	private CharacterFactory factory;

	/**
	 * private IntPoint ip;
	 * private GraphicsSolidFill breathBackFill = null;
	 * private GraphicsPath breathBackPath = null;
	 * private GraphicsSolidFill breathFill = null;
	 * private GraphicsPath breathPath = null;
	 */


	public static Player fromPlayerXML(String name, XML objectXML) {
		int objectType = objectXML.getIntValue("ObjectType");
		XML playerObject = ObjectLibrary.xmlLibrary.get(objectType);
		Player playerXML = new Player(playerObject);
		playerXML.name = name;
		playerXML.level = objectXML.getIntValue("Level");
		playerXML.exp = objectXML.getIntValue("Exp");
		playerXML.equipment = ConversionUtil.toIntVector(objectXML.getValue("Equipment"));
		playerXML.calculateStatBoosts();
		playerXML.lockedSlot = new int[playerXML.equipment.length];
		playerXML.maxHP = playerXML.maxHPBoost + objectXML.getIntValue("MaxHitPoints");
		playerXML.hp = objectXML.getIntValue("HitPoints");
		playerXML.maxMP = playerXML.maxMPBoost + objectXML.getIntValue("MaxMagicPoints");
		playerXML.mp = objectXML.getIntValue("MagicPoints");
		playerXML.attack = playerXML.attackBoost + objectXML.getIntValue("Attack");
		playerXML.defense = playerXML.defenseBoost + objectXML.getIntValue("Defense");
		playerXML.speed = playerXML.speedBoost + objectXML.getIntValue("Speed");
		playerXML.dexterity = playerXML.dexterityBoost + objectXML.getIntValue("Dexterity");
		playerXML.vitality = playerXML.vitalityBoost + objectXML.getIntValue("HpRegen");
		playerXML.wisdom = playerXML.wisdomBoost + objectXML.getIntValue("MpRegen");
		playerXML.tex1Id = objectXML.getIntValue("Tex1");
		playerXML.tex2Id = objectXML.getIntValue("Tex2");
		playerXML.hasBackpack = objectXML.getBooleanValue("HasBackpack");
		return playerXML;
	}

	public void calculateStatBoosts() {
		this.maxHPBoost = 0;
		this.maxMPBoost = 0;
		this.attackBoost = 0;
		this.defenseBoost = 0;
		this.speedBoost = 0;
		this.vitalityBoost = 0;
		this.wisdomBoost = 0;
		this.dexterityBoost = 0;
		int i = 0;
		while (i < GeneralConstants.NUM_EQUIPMENT_SLOTS) {
			int equipment = this.equipment[i];
			if (equipment != -1) {
				XML objectXML = ObjectLibrary.xmlLibrary.get(equipment);
				if (objectXML != null && objectXML.hasOwnProperty("ActivateOnEquip")) {
					for (XML loc4 : objectXML.getChilds("ActivateOnEquip")) {
						if (loc4.name().equals("IncrementStat")) {
							int loc5 = loc4.getIntAttribute("stat");
							int loc6 = loc4.getIntAttribute("amount");
							switch (loc5) {
								case StatData.MAX_HP_STAT:
									this.maxHPBoost = this.maxHPBoost + loc6;
									continue;
								case StatData.MAX_MP_STAT:
									this.maxMPBoost = this.maxMPBoost + loc6;
									continue;
								case StatData.ATTACK_STAT:
									this.attackBoost = this.attackBoost + loc6;
									continue;
								case StatData.DEFENSE_STAT:
									this.defenseBoost = this.defenseBoost + loc6;
									continue;
								case StatData.SPEED_STAT:
									this.speedBoost = this.speedBoost + loc6;
									continue;
								case StatData.VITALITY_STAT:
									this.vitalityBoost = this.vitalityBoost + loc6;
									continue;
								case StatData.WISDOM_STAT:
									this.wisdomBoost = this.wisdomBoost + loc6;
									continue;
								case StatData.DEXTERITY_STAT:
									this.dexterityBoost = this.dexterityBoost + loc6;
									continue;
								default:
									continue;
							}
						} else {
							continue;
						}
					}
				}
			}
			i++;
		}
	}

	public Player(XML param1) {
		super(param1);
		this.creditsWereChanged = new Signal();
		this.fameWasChanged = new Signal();
		this.ip = new IntPoint();
		this.addTextLine = AddTextLineSignal.getInstance();
		this.factory = CharacterFactory.getInstance();
		this.attackMax = param1.getChild("Attack").getIntAttribute("max");
		this.defenseMax = param1.getChild("Defense").getIntAttribute("max");
		this.speedMax = param1.getChild("Speed").getIntAttribute("max");
		this.dexterityMax = param1.getChild("Dexterity").getIntAttribute("max");
		this.vitalityMax = param1.getChild("HpRegen").getIntAttribute("max");
		this.wisdomMax = param1.getChild("MpRegen").getIntAttribute("max");
		this.maxHPMax = param1.getChild("MaxHitPoints").getIntAttribute("max");
		this.maxMPMax = param1.getChild("MaxMagicPoints").getIntAttribute("max");
		texturingCache = new HashMap<>();
	}

	public int getTex1() {
		return tex1Id;
	}

	public int getTex2() {
		return tex2Id;
	}


}

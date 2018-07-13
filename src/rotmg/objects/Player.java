package rotmg.objects;

import alde.flash.utils.XML;
import flash.display.BitmapData;
import flash.geom.Point;
import flash.utils.Dictionary;
import org.osflash.signals.Signal;
import rotmg.assets.services.CharacterFactory;
import rotmg.constants.GeneralConstants;
import rotmg.messaging.data.StatData;
import rotmg.objects.animation.AnimatedChar;
import rotmg.parameters.Parameters;
import rotmg.particles.HealingEffect;
import rotmg.signals.AddTextLineSignal;
import rotmg.util.ConversionUtil;
import rotmg.util.IntPoint;

import java.util.List;

import static flash.utils.timer.getTimer.getTimer;


public class Player extends Character {

	public static final int MS_BETWEEN_TELEPORT = 10000;
	public static final int MS_REALM_TELEPORT = 120000;
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
	public static List<Integer> wantedList = null;
	public static int lastSearchTime;
	public static int lastLootTime;
	public static int last_hpStack = -1;
	public static int last_mpStack = -1;
	public static List<Integer> last_pInv;
	public static boolean enableAutoLoot = true;
	public static boolean enableTeleportLoot = false;
	public static boolean enableSafeLoot = true;
	private static Point newP = new Point();
	public int xpTimer;
	public int skinId;
	public AnimatedChar skin;
	public boolean isShooting;
	public Signal creditsWereChanged;
	public Signal fameWasChanged;
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
	public Merchant nearestMerchant = null;
	protected double rotate = 0;
	protected Point relMoveVec = null;
	protected double moveMultiplier = 1;

	protected HealingEffect healingEffect = null;
	private BitmapData famePortrait = null;
	private AddTextLineSignal addTextLine;
	private CharacterFactory factory;

	private IntPoint ip;

	public Player(XML param1) {
		super(param1);
		this.creditsWereChanged = new Signal();
		this.fameWasChanged = new Signal();
		this.ip = new IntPoint();
		this.addTextLine = AddTextLineSignal.getInstance();
		this.factory = CharacterFactory.getInstance();
		this.attackMax = param1.child("Attack").getIntAttribute("max");
		this.defenseMax = param1.child("Defense").getIntAttribute("max");
		this.speedMax = param1.child("Speed").getIntAttribute("max");
		this.dexterityMax = param1.child("Dexterity").getIntAttribute("max");
		this.vitalityMax = param1.child("HpRegen").getIntAttribute("max");
		this.wisdomMax = param1.child("MpRegen").getIntAttribute("max");
		this.maxHPMax = param1.child("MaxHitPoints").getIntAttribute("max");
		this.maxMPMax = param1.child("MaxMagicPoints").getIntAttribute("max");
		texturingCache = new Dictionary<>();
	}

	/**
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
		//playerXML.lockedSlot = new int[playerXML.equipment.length];
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

	public int getFameBonus() {
		int loc3 = 0;
		XML loc4 = null;
		int loc1 = 0;
		int loc2 = 0;
		while (loc2 < GeneralConstants.NUM_EQUIPMENT_SLOTS) {
			if (equipment != null && equipment.length > loc2) {
				loc3 = equipment.get(loc2);
				if (loc3 != -1) {
					loc4 = ObjectLibrary.xmlLibrary.get(loc3);
					if (loc4 != null && loc4.hasOwnProperty("FameBonus")) {
						loc1 = loc1 + loc4.getIntValue("FameBonus");
					}
				}
			}
			loc2++;
		}
		return loc1;
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
			int equipment = this.equipment.get(i);
			if (equipment != -1) {
				XML objectXML = ObjectLibrary.xmlLibrary.get(equipment);
				if (objectXML != null && objectXML.hasOwnProperty("ActivateOnEquip")) {
					for (XML loc4 : objectXML.children("ActivateOnEquip")) {
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

	public void setRelativeMovement(double param1, double param2, double param3) {
		double loc4 = 0;
		if (this.relMoveVec == null) {
			this.relMoveVec = new Point();
		}
		this.rotate = param1;
		this.relMoveVec.x = param2;
		this.relMoveVec.y = param3;
		if (isConfused()) {
			loc4 = this.relMoveVec.x;
			this.relMoveVec.x = -this.relMoveVec.y;
			this.relMoveVec.y = -loc4;
			this.rotate = -this.rotate;
		}
	}

	public void setCredits(int param1) {
		this.credits = param1;
		this.creditsWereChanged.dispatch();
	}

	public void setFame(int param1) {
		this.fame = param1;
		this.fameWasChanged.dispatch();
	}

	public void setTokens(int param1) {
		this.tokens = param1;
	}

	public void setGuildName(String param1) {
		//System.out.println("Guild name : " + param1);
	}

	public boolean isTeleportEligible(Player param1) {
		return !(param1.dead || param1.isPaused() || param1.isInvisible());
	}

	@Override
	public boolean moveTo(double param1, double param2) {
		boolean loc3 = super.moveTo(param1, param2);
		return loc3;
	}

	private double getMoveSpeed() {
		if (isSlowed()) {
			return MIN_MOVE_SPEED * this.moveMultiplier;
		}
		double loc1 = MIN_MOVE_SPEED + this.speed / 75 * (MAX_MOVE_SPEED - MIN_MOVE_SPEED);
		if (isSpeedy() || isNinjaSpeedy()) {
			loc1 = loc1 * 1.5;
		}
		loc1 = loc1 * this.moveMultiplier;
		return loc1;
	}

	public double attackFrequency() {
		if (isDazed()) {
			return MIN_ATTACK_FREQ;
		}
		double loc1 = MIN_ATTACK_FREQ + this.dexterity / 75 * (MAX_ATTACK_FREQ - MIN_ATTACK_FREQ);
		if (isBerserk()) {
			loc1 = loc1 * 1.5;
		}
		return loc1;
	}

	private double attackMultiplier() {
		if (isWeak()) {
			return MIN_ATTACK_MULT;
		}
		double loc1 = MIN_ATTACK_MULT + this.attack / 75 * (MAX_ATTACK_MULT - MIN_ATTACK_MULT);
		if (isDamaging()) {
			loc1 = loc1 * 1.5;
		}
		return loc1;
	}

	public int getTex1() {
		return tex1Id;
	}

	public int getTex2() {
		return tex2Id;
	}

	public void onMove() {
		if (map == null) {
			return;
		}
		Square loc1 = map.getSquare(x, y);
		if (loc1.props.sinking) {
			sinkLevel = (int) Math.min(sinkLevel + 1, Parameters.MAX_SINK_LEVEL);
			this.moveMultiplier = 0.1 + (1 - sinkLevel / Parameters.MAX_SINK_LEVEL) * (loc1.props.speed - 0.1);
		} else {
			sinkLevel = 0;
			this.moveMultiplier = loc1.props.speed;
		}

	}

	public boolean useAltWeapon(double loc8, double loc9, int startUse) {
		return true;
	}

	public void attemptAttackAngle(double v) {
	}

	public int msUtilTeleport() {
		int loc1 = getTimer();
		return Math.max(0, this.nextTeleportAt - loc1);
	}

	public boolean teleportTo(Player param1) {
		/*if (isPaused()) {
			this.addTextLine.dispatch(this.makeErrorMessage(TextKey.PLAYER_NOTELEPORTWHILEPAUSED));
			return false;
		}
		int loc2 = this.msUtilTeleport();
		if (loc2 > 0) {
			if (!(loc2 > MS_BETWEEN_TELEPORT && param1.isFellowGuild)) {
				this.addTextLine.dispatch(this.makeErrorMessage(TextKey.PLAYER_TELEPORT_COOLDOWN, {"seconds": int
				(loc2 / 1000 + 1)}));
				return false;
			}
		}
		if (!this.isTeleportEligible(param1)) {
			if (param1.isInvisible()) {
				this.addTextLine.dispatch(this.makeErrorMessage(TextKey.TELEPORT_INVISIBLE_PLAYER, {"player":
				param1.name_}));
			} else {
				this.addTextLine.dispatch(this.makeErrorMessage(TextKey.PLAYER_TELEPORT_TO_PLAYER, {"player":
				param1.name_}));
			}
			return false;
		}
		map.gs.gsc.teleport(param1.objectId);
		this.nextTeleportAt = getTimer() + MS_BETWEEN_TELEPORT;*/
		return true;
	}

}

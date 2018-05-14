package realmrelay.game.objects;

import javafx.scene.Camera;
import realmrelay.game._as3.Signal;
import realmrelay.game._as3.XML;
import realmrelay.game.assets.services.CharacterFactory;
import realmrelay.game.chat.model.ChatMessage;
import realmrelay.game.constants.ActivationType;
import realmrelay.game.constants.GeneralConstants;
import realmrelay.game.constants.UseType;
import realmrelay.game.messaging.data.StatData;
import realmrelay.game.model.PotionInventoryModel;
import realmrelay.game.objects.animation.AnimatedChar;
import realmrelay.game.parameters.Parameters;
import realmrelay.game.particles.HealingEffect;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.sound.SoundEffectLibrary;
import realmrelay.game.ui.model.TabStripModel;
import realmrelay.game.util.*;
import realmrelay.packets.data.unused.BitmapData;
import sun.java2d.cmm.ColorTransform;

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


	private IntPoint ip;

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


	public int getFameBonus() {
		int loc3 = 0;
		XML loc4 = null;
		int loc1 = 0;
		int loc2 = 0;
		while (loc2 < GeneralConstants.NUM_EQUIPMENT_SLOTS) {
			if (equipment && equipment.length > loc2) {
				loc3 = equipment_[loc2];
				if (loc3 != -1) {
					loc4 = ObjectLibrary.xmlLibrary_[loc3];
					if (loc4 != null && loc4.hasOwnProperty("FameBonus")) {
						loc1 = loc1 + int(loc4.FameBonus);
					}
				}
			}
			loc2++;
		}
		return loc1;
	}

	public void calculateStatBoosts() {
		int loc2 = 0;
		XML loc3 = null;
		XML loc4 = null;
		int loc5 = 0;
		int loc6 = 0;
		this.maxHPBoost = 0;
		this.maxMPBoost = 0;
		this.attackBoost = 0;
		this.defenseBoost = 0;
		this.speedBoost = 0;
		this.vitalityBoost = 0;
		this.wisdomBoost = 0;
		this.dexterityBoost = 0;
		int loc1 = 0;
		while (loc1 < GeneralConstants.NUM_EQUIPMENT_SLOTS) {
			if (equipment && equipment.length > loc1) {
				loc2 = equipment_[loc1];
				if (loc2 != -1) {
					loc3 = ObjectLibrary.xmlLibrary_[loc2];
					if (loc3 != null && loc3.hasOwnProperty("ActivateOnEquip")) {
						for (loc4 in loc3.ActivateOnEquip) {
							if (loc4.toString() == "IncrementStat") {
								loc5 = int(loc4. @stat);
								loc6 = int(loc4. @amount);
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
			}
			loc1++;
		}
	}

	public void setRelativeMovement(double param1, double param2, double param3) {
		double loc4 = NaN;
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
			this.rotate = -this.rotate_;
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
		GameObject loc3 = null;
		Player loc4 = null;
		boolean loc5 = false;
		this.guildName = param1;
		var loc2:Player = map.player.
		if (loc2 == this) {
			for (loc3 in map.goDict. {
				loc4 = loc3 as Player;
				if (loc4 != null && loc4 != this) {
					loc4.setGuildName(loc4.guildName_);
				}
			}
		} else {
			loc5 = loc2 != null && loc2.guildName != null && loc2.guildName != "" && loc2.guildName == this.guildName_;
			if (loc5 != this.isFellowGuild_) {
				this.isFellowGuild = loc5;
				nameBitmapData = null;
			}
		}
	}

	public boolean isTeleportEligible(Player param1) {
		return !(param1.dead || param1.isPaused() || param1.isInvisible());
	}

	public int msUtilTeleport() {
		int loc1 = getTimer();
		return Math.max(0, this.nextTeleportAt - loc1);
	}

	public boolean teleportTo(Player param1) {
		if (isPaused()) {
			this.addTextLine.dispatch(this.makeErrorMessage(TextKey.PLAYER_NOTELEPORTWHILEPAUSED));
			return false;
		}
		int loc2 = this.msUtilTeleport();
		if (loc2 > 0) {
			if (!(loc2 > MS_BETWEEN_TELEPORT && param1.isFellowGuild_)) {
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
		map.gs.gsc.teleport(param1.objectId.;
		this.nextTeleportAt = getTimer() + MS_BETWEEN_TELEPORT;
		return true;
	}

	private ChatMessage makeErrorMessage(String param1, Object =null param2) {
		return ChatMessage.make(Parameters.ERROR_CHAT_NAME, param1, -1, -1, "", false, param2);
	}

	public void levelUpEffect(String param1, boolean =true param2) {
		if (!Parameters.data.noParticlesMaster && param2) {
			this.levelUpParticleEffect();
		}
		CharacterStatusText loc3 = new CharacterStatusText(this, 65280, 2000);
		loc3.setStringBuilder(new LineBuilder().setParams(param1));
		map.mapOverlay.addStatusText(loc3);
	}

	public void handleLevelUp(boolean param1) {
		SoundEffectLibrary.play("level_up");
		if (param1) {
			this.levelUpEffect(TextKey.PLAYER_NEWCLASSUNLOCKED, false);
			this.levelUpEffect(TextKey.PLAYER_LEVELUP);
		} else {
			this.levelUpEffect(TextKey.PLAYER_LEVELUP);
		}
	}

	public void levelUpParticleEffect(int =4.27825536E9param1) {
		map.addObj(new LevelUpEffect(this, param1, 20), x.y.;
	}

	public void handleExpUp(int param1) {
		if (level == 20 && !this.bForceExp()) {
			return;
		}
		CharacterStatusText loc2 = new CharacterStatusText(this, 65280, 1000);
		loc2.setStringBuilder(new LineBuilder().setParams("+{exp} EXP", {"exp":param1}));
		map.mapOverlay.addStatusText(loc2);
	}

	private boolean bForceExp() {
		return Parameters.data.forceEXP && (Parameters.data.forceEXP == 1 || Parameters.data.forceEXP == 2 && map.player == this);
	}

	public void updateFame(int param1) {
		CharacterStatusText loc2 = new CharacterStatusText(this, 14835456, 2000);
		loc2.setStringBuilder(new LineBuilder().setParams("+{fame} Fame", {"fame":param1}));
		map.mapOverlay.addStatusText(loc2);
	}

	private Merchant getNearbyMerchant() {
		Point loc3 = null;
		Merchant loc4 = null;
		var loc1:int =x - int(x_) > 0.5 ? 1 : -1;
		var loc2:int =y - int(y_) > 0.5 ? 1 : -1;
		for (loc3 in NEARBY) {
			this.ip.x = x + loc1 * loc3.x;
			this.ip.y = y + loc2 * loc3.y;
			loc4 = map.merchLookup.this.ip.;
			if (loc4 != null) {
				return PointUtil.distanceSquaredXY(loc4.x_, loc4.y_, x_, y_) < 1 ? loc4 : null;
			}
		}
		return null;
	}

	public boolean walkTo(double param1, double param2) {
		this.modifyMove(param1, param2, newP);
		return this.moveTo(newP.x, newP.y);
	}

	override

	public boolean moveTo(double param1, double param2) {
		boolean loc3 = super.moveTo(param1, param2);
		if (map.gs.evalIsNotInCombatMapArea()) {
			this.nearestMerchant = this.getNearbyMerchant();
		}
		return loc3;
	}

	public void modifyMove(double param1, double param2, Point param3) {
		if (isParalyzed() || isPetrified()) {
			param3.x = x_;
			param3.y = y_;
			return;
		}
		double loc4 = param1 - x_;
		double loc5 = param2 - y_;
		if (loc4 < MOVE_THRESHOLD && loc4 > -MOVE_THRESHOLD && loc5 < MOVE_THRESHOLD && loc5 > -MOVE_THRESHOLD) {
			this.modifyStep(param1, param2, param3);
			return;
		}
		double loc6 = MOVE_THRESHOLD / Math.max(Math.abs(loc4), Math.abs(loc5));
		double loc7 = 0;
		param3.x = x_;
		param3.y = y_;
		boolean loc8 = false;
		while (!loc8) {
			if (loc7 + loc6 >= 1) {
				loc6 = 1 - loc7;
				loc8 = true;
			}
			this.modifyStep(param3.x + loc4 * loc6, param3.y + loc5 * loc6, param3);
			loc7 = loc7 + loc6;
		}
	}

	public void modifyStep(double param1, double param2, Point param3) {
		double loc6 = NaN;
		double loc7 = NaN;
		boolean loc4 = x % 0.5 == 0 && param1 != x || int(x / 0.5) != int(param1 / 0.5);
		boolean loc5 = y % 0.5 == 0 && param2 != y || int(y / 0.5) != int(param2 / 0.5);
		if (!loc4 && !loc5 || this.isValidPosition(param1, param2)) {
			param3.x = param1;
			param3.y = param2;
			return;
		}
		if (loc4) {
			loc6 = param1 > x ? int(param1 * 2 / 2 : int(x * 2 / 2;
			if (int(loc6) > int(x_)){
				loc6 = loc6 - 0.01;
			}
		}
		if (loc5) {
			loc7 = param2 > y ? int(param2 * 2 / 2 : int(y * 2 / 2;
			if (int(loc7) > int(y_)){
				loc7 = loc7 - 0.01;
			}
		}
		if (!loc4) {
			param3.x = param1;
			param3.y = loc7;
			if (square != null && square.props.slideAmount != 0) {
				this.resetMoveVector(false);
			}
			return;
		}
		if (!loc5) {
			param3.x = loc6;
			param3.y = param2;
			if (square != null && square.props.slideAmount != 0) {
				this.resetMoveVector(true);
			}
			return;
		}
		var loc8:double =param1 > x ? param1 - loc6 : loc6 - param1;
		var loc9:double =param2 > y ? param2 - loc7 : loc7 - param2;
		if (loc8 > loc9) {
			if (this.isValidPosition(param1, loc7)) {
				param3.x = param1;
				param3.y = loc7;
				return;
			}
			if (this.isValidPosition(loc6, param2)) {
				param3.x = loc6;
				param3.y = param2;
				return;
			}
		} else {
			if (this.isValidPosition(loc6, param2)) {
				param3.x = loc6;
				param3.y = param2;
				return;
			}
			if (this.isValidPosition(param1, loc7)) {
				param3.x = param1;
				param3.y = loc7;
				return;
			}
		}
		param3.x = loc6;
		param3.y = loc7;
	}

	private void resetMoveVector(boolean param1) {
		moveVec.scaleBy(-0.5);
		if (param1) {
			moveVec.y = moveVec.y * -1;
		} else {
			moveVec.x = moveVec.x * -1;
		}
	}

	public boolean isValidPosition(double param1, double param2) {
		Square loc3 = map.getSquare(param1, param2);
		if (square != loc3 && (loc3 == null || !loc3.isWalkable())) {
			return false;
		}
		double loc4 = param1 - int(param1);
		double loc5 = param2 - int(param2);
		if (loc4 < 0.5) {
			if (this.isFullOccupy(param1 - 1, param2)) {
				return false;
			}
			if (loc5 < 0.5) {
				if (this.isFullOccupy(param1, param2 - 1) || this.isFullOccupy(param1 - 1, param2 - 1)) {
					return false;
				}
			} else if (loc5 > 0.5) {
				if (this.isFullOccupy(param1, param2 + 1) || this.isFullOccupy(param1 - 1, param2 + 1)) {
					return false;
				}
			}
		} else if (loc4 > 0.5) {
			if (this.isFullOccupy(param1 + 1, param2)) {
				return false;
			}
			if (loc5 < 0.5) {
				if (this.isFullOccupy(param1, param2 - 1) || this.isFullOccupy(param1 + 1, param2 - 1)) {
					return false;
				}
			} else if (loc5 > 0.5) {
				if (this.isFullOccupy(param1, param2 + 1) || this.isFullOccupy(param1 + 1, param2 + 1)) {
					return false;
				}
			}
		} else if (loc5 < 0.5) {
			if (this.isFullOccupy(param1, param2 - 1)) {
				return false;
			}
		} else if (loc5 > 0.5) {
			if (this.isFullOccupy(param1, param2 + 1)) {
				return false;
			}
		}
		return true;
	}

	public boolean isFullOccupy(double param1, double param2) {
		Square loc3 = map.lookupSquare(param1, param2);
		return loc3 == null || loc3.tileType == 255 || loc3.obj != null && loc3.obj.props.fullOccupy.
	}

	override

	public boolean update(int param1, int param2) {
		double loc3 = NaN;
		double loc4 = NaN;
		double loc5 = NaN;
		Vector3D loc6 = null;
		double loc7 = NaN;
		int loc8 = 0;
		List<int> loc9 = null;
		if (this.tierBoost && !isPaused()) {
			this.tierBoost = this.tierBoost - param2;
			if (this.tierBoost < 0) {
				this.tierBoost = 0;
			}
		}
		if (this.dropBoost && !isPaused()) {
			this.dropBoost = this.dropBoost - param2;
			if (this.dropBoost < 0) {
				this.dropBoost = 0;
			}
		}
		if (this.xpTimer && !isPaused()) {
			this.xpTimer = this.xpTimer - param2;
			if (this.xpTimer < 0) {
				this.xpTimer = 0;
			}
		}
		if (isHealing() && !isPaused()) {
			if (!Parameters.data.noParticlesMaster && this.healingEffect == null) {
				this.healingEffect = new HealingEffect(this);
				map.addObj(this.healingEffect.x.y.;
			}
		} else if (this.healingEffect != null) {
			map.removeObj(this.healingEffect.objectId.;
			this.healingEffect = null;
		}
		if (map.player == this && isPaused()) {
			return true;
		}
		if (this.relMoveVec != null) {
			loc3 = Parameters.data.cameraAngle;
			if (this.rotate != 0) {
				loc3 = loc3 + param2 * Parameters.PLAYER_ROTATE_SPEED * this.rotate_;
				Parameters.data.cameraAngle = loc3;
			}
			if (this.relMoveVec.x != 0 || this.relMoveVec.y != 0) {
				loc4 = this.getMoveSpeed();
				loc5 = Math.atan2(this.relMoveVec.y, this.relMoveVec.x);
				if (square.props.slideAmount > 0) {
					loc6 = new Vector3D();
					loc6.x = loc4 * Math.cos(loc3 + loc5);
					loc6.y = loc4 * Math.sin(loc3 + loc5);
					loc6.z = 0;
					loc7 = loc6.length;
					loc6.scaleBy(-1 * (square.props.slideAmount - 1));
					moveVec.scaleBy(square.props.slideAmount.;
					if (moveVec.length < loc7) {
						moveVec = moveVec.add(loc6);
					}
				} else {
					moveVec.x = loc4 * Math.cos(loc3 + loc5);
					moveVec.y = loc4 * Math.sin(loc3 + loc5);
				}
			} else if (moveVec.length > 0.00012 && square.props.slideAmount > 0) {
				moveVec.scaleBy(square.props.slideAmount.;
			} else {
				moveVec.x = 0;
				moveVec.y = 0;
			}
			if (square != null && square.props.push. {
				moveVec.x = moveVec.x - square.props.animate.dx / 1000;
				moveVec.y = moveVec.y - square.props.animate.dy / 1000;
			}
			this.walkTo(x + param2 * moveVec.x, y + param2 * moveVec.y);
		} else if (!super.update(param1, param2)) {
			return false;
		}
		if (map.player == this && square.props.maxDamage > 0 && square.lastDamage + 500 < param1 && !isInvincible() && (square.obj == null || !square.obj.props.protectFromGroundDamage.)
		{
			loc8 = map.gs.gsc.getNextDamage(square.props.minDamage.square.props.maxDamage.;
			loc9 = new Arraylist<int>();
			loc9.add(ConditionEffect.GROUND_DAMAGE);
			damage(true, loc8, loc9, hp <= loc8, null);
			map.gs.gsc.groundDamage(param1, x.y.;
			square.lastDamage = param1;
		}
		return true;
	}

	public void onMove() {
		if (map == null) {
			return;
		}
		Square loc1 = map.getSquare(x.y.;
		if (loc1.props.sinking. {
			sinkLevel = Math.min(sinkLevel + 1, Parameters.MAX_SINK_LEVEL);
			this.moveMultiplier = 0.1 + (1 - sinkLevel / Parameters.MAX.INK.EVEL) * (loc1.props.speed - 0.1);
		} else{
			sinkLevel = 0;
			this.moveMultiplier = loc1.props.speed.
		}
	}

	override

	protected BitmapData makeNameBitmapData() {
		StringBuilder loc1 = new StaticStringBuilder(name_);
		BitmapTextFactory loc2 = StaticInjectorContext.getInjector().getInstance(BitmapTextFactory);
		BitmapData loc3 = loc2.make(loc1, 16, this.getNameColor(), true, NAME_OFFSET_MATRIX, true);
		loc3.draw(FameUtil.numStarsToIcon(this.numStars_), RANK_OFFSET_MATRIX);
		return loc3;
	}

	private int getNameColor() {
		if (this.isFellowGuild_) {
			return Parameters.FELLOW_GUILD_COLOR;
		}
		if (this.nameChosen_) {
			return Parameters.NAME_CHOSEN_COLOR;
		}
		return 16777215;
	}

	protected void drawBreathBar(List<IGraphicsData> param1, int param2) {
		double loc8 = NaN;
		double loc9 = NaN;
		if (this.breathPath == null) {
			this.breathBackFill = new GraphicsSolidFill();
			this.breathBackPath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Arraylist<double>());
			this.breathFill = new GraphicsSolidFill(2542335);
			this.breathPath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Arraylist<double>());
		}
		if (this.breath <= Parameters.BREATH_THRESH) {
			loc8 = (Parameters.BREATH_THRESH - this.breath_) / Parameters.BREATH_THRESH;
			this.breathBackFill.color = MoreColorUtil.lerpColor(1118481, 16711680, Math.abs(Math.sin(param2 / 300)) * loc8);
		} else {
			this.breathBackFill.color = 1118481;
		}
		int loc3 = 20;
		int loc4 = 12;
		int loc5 = 5;
		List<double> loc6 = this.breathBackPath.data as List<double>;
		loc6.length = 0;
		double loc7 = 1.2;
		loc6.add(posS_[0] - loc3 - loc7, posS_[1] + loc4 - 0 - 0, posS_[0] + loc3 + loc7, posS_[1] + loc4 - 0 - 0, posS_[0] + loc3 + loc7, posS_[1] + loc4 + loc5 + loc7, posS_[0] - loc3 - loc7, posS_[1] + loc4 + loc5 + loc7);
		param1.add(this.breathBackFill_);
		param1.add(this.breathBackPath_);
		param1.add(GraphicsUtil.END_FILL);
		if (this.breath > 0) {
			loc9 = this.breath / 100 * 2 * loc3;
			this.breathPath.data.length = 0;
			loc6 = this.breathPath.data as List<double>;
			loc6.length = 0;
			loc6.add(posS_[0] - loc3, posS_[1] + loc4, posS_[0] - loc3 + loc9, posS_[1] + loc4, posS_[0] - loc3 + loc9, posS_[1] + loc4 + loc5, posS_[0] - loc3, posS_[1] + loc4 + loc5);
			param1.add(this.breathFill_);
			param1.add(this.breathPath_);
			param1.add(GraphicsUtil.END_FILL);
		}
		GraphicsFillExtra.setSoftwareDrawSolid(this.breathFill_, true);
		GraphicsFillExtra.setSoftwareDrawSolid(this.breathBackFill_, true);
	}

	override

	public void draw(List<IGraphicsData> param1, Camera param2, int param3) {
		super.draw(param1, param2, param3);
		if (this != map.player. {
			if (!Parameters.screenShotMode_) {
				drawName(param1, param2);
			}
		} else if (this.breath >= 0) {
			this.drawBreathBar(param1, param3);
		}
	}

	private double getMoveSpeed() {
		if (isSlowed()) {
			return MIN_MOVE_SPEED * this.moveMultiplier_;
		}
		double loc1 = MIN_MOVE_SPEED + this.speed / 75 * (MAX_MOVE_SPEED - MIN_MOVE_SPEED);
		if (isSpeedy() || isNinjaSpeedy()) {
			loc1 = loc1 * 1.5;
		}
		loc1 = loc1 * this.moveMultiplier_;
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

	private void makeSkinTexture() {
		MaskedImage loc1 = this.skin.imageFromAngle(0, AnimatedChar.STAND, 0);
		animatedChar = this.skin;
		texture = loc1.image_;
		mask = loc1.mask_;
		this.isDefaultAnimatedChar = true;
	}

	private void setToRandomAnimatedCharacter() {
		List<XML> loc1 = ObjectLibrary.hexTransforms_;
		int loc2 = Math.floor(Math.random() * loc1.length);
		int loc3 = loc1[loc2]. @type ;
		var loc4:TextureData = ObjectLibrary.typeToTextureData_[loc3];
		texture = loc4.texture_;
		mask = loc4.mask_;
		animatedChar = loc4.animatedChar_;
		this.isDefaultAnimatedChar = false;
	}

	override

	protected BitmapData getTexture(Camera param1, int param2) {
		MaskedImage loc5 = null;
		int loc10 = 0;
		Dictionary loc11 = null;
		double loc12 = NaN;
		int loc13 = 0;
		ColorTransform loc14 = null;
		double loc3 = 0;
		int loc4 = AnimatedChar.STAND;
		if (this.isShooting || param2 < attackStart + this.attackPeriod_) {
			facing = attackAngle_;
			loc3 = (param2 - attackStart_) % this.attackPeriod / this.attackPeriod_;
			loc4 = AnimatedChar.ATTACK;
		} else if (moveVec.x != 0 || moveVec.y != 0) {
			loc10 = 3.5 / this.getMoveSpeed();
			if (moveVec.y != 0 || moveVec.x != 0) {
				facing = Math.atan2(moveVec.y, moveVec.x);
			}
			loc3 = param2 % loc10 / loc10;
			loc4 = AnimatedChar.WALK;
		}
		if (this.isHexed()) {
			this.isDefaultAnimatedChar && this.setToRandomAnimatedCharacter();
		} else if (!this.isDefaultAnimatedChar) {
			this.makeSkinTexture();
		}
		if (param1.isHallucinating_) {
			loc5 = new MaskedImage(getHallucinatingTexture(), null);
		} else {
			loc5 = animatedChar.imageFromFacing(facing.param1, loc4, loc3);
		}
		int loc6 = tex1Id_;
		int loc7 = tex2Id_;
		BitmapData loc8 = null;
		if (this.nearestMerchant_) {
			loc11 = texturingCache_[this.nearestMerchant_];
			if (loc11 == null) {
				texturingCache_[this.nearestMerchant_] = new Dictionary();
			} else {
				loc8 = loc11[loc5];
			}
			loc6 = this.nearestMerchant.getTex1Id(tex1Id.;
			loc7 = this.nearestMerchant.getTex2Id(tex2Id.;
		} else {
			loc8 = texturingCache_[loc5];
		}
		if (loc8 == null) {
			loc8 = TextureRedrawer.resize(loc5.image_, loc5.mask_, size_, false, loc6, loc7);
			if (this.nearestMerchant != null) {
				texturingCache_[this.nearestMerchant_][loc5] = loc8;
			} else {
				texturingCache_[loc5] = loc8;
			}
		}
		if (hp < maxHP * 0.2) {
			loc12 = int(Math.abs(Math.sin(param2 / 200)) * 10) / 10;
			loc13 = 128;
			loc14 = new ColorTransform(1, 1, 1, 1, loc12 * loc13, -loc12 * loc13, -loc12 * loc13);
			loc8 = CachingColorTransformer.transformBitmapData(loc8, loc14);
		}
		var loc9:BitmapData = texturingCache_[loc8];
		if (loc9 == null) {
			loc9 = GlowRedrawer.outlineGlow(loc8, this.legendaryRank == -1 ? int(0) : int(16711680));
			texturingCache_[loc8] = loc9;
		}
		if (isPaused() || isStasis() || isPetrified()) {
			loc9 = CachingColorTransformer.filterBitmapData(loc9, PAUSED_FILTER);
		} else if (isInvisible()) {
			loc9 = CachingColorTransformer.alphaBitmapData(loc9, 0.4);
		}
		return loc9;
	}

	override

	public BitmapData getPortrait() {
		MaskedImage loc1 = null;
		int loc2 = 0;
		if (portrait == null) {
			loc1 = animatedChar.imageFromDir(AnimatedChar.RIGHT, AnimatedChar.STAND, 0);
			loc2 = 4 / loc1.image.width * 100;
			portrait = TextureRedrawer.resize(loc1.image_, loc1.mask_, loc2, true, tex1Id_, tex2Id_);
			portrait = GlowRedrawer.outlineGlow(portrait_, 0);
		}
		return portrait_;
	}

	public BitmapData getFamePortrait(int param1) {
		MaskedImage loc2 = null;
		if (this.famePortrait == null) {
			loc2 = animatedChar.imageFromDir(AnimatedChar.RIGHT, AnimatedChar.STAND, 0);
			param1 = 4 / loc2.image.width * param1;
			this.famePortrait = TextureRedrawer.resize(loc2.image_, loc2.mask_, param1, true, tex1Id_, tex2Id_);
			this.famePortrait = GlowRedrawer.outlineGlow(this.famePortrait_, 0);
		}
		return this.famePortrait_;
	}

	public boolean useAltWeapon(double param1, double param2, int useType) {
		Point loc7 = null;
		int loc13 = 0;
		String loc14 = null;
		double loc15 = 0;
		int loc16 = 0;
		if (map == null || isPaused()) {
			return false;
		}
		int loc4 = equipment[1];
		if (loc4 == -1) {
			return false;
		}
		XML loc5 = ObjectLibrary.xmlLibrary.get(loc4);
		if (loc5 == null || !loc5.hasOwnProperty("Usable")) {
			return false;
		}
		if (isSilenced()) {
			SoundEffectLibrary.play("error");
			return false;
		}
		double loc6 = Parameters.data.cameraAngle + Math.atan2(param2, param1);
		boolean loc8 = false;
		boolean loc9 = false;
		boolean loc10 = false;
		for (XML loc11 : loc5.getChilds("Activate")) {
			loc14 = loc11.toString();
			if (loc14.equals(ActivationType.TELEPORT)) {
				loc8 = true;
				loc10 = true;
			}
			if (loc14.equals(ActivationType.BULLET_NOVA) || loc14.equals(ActivationType.POISON_GRENADE) || loc14.equals(ActivationType.VAMPIRE_BLAST) || loc14.equals(ActivationType.TRAP) || loc14.equals(ActivationType.STASIS_BLAST) || loc14.equals(ActivationType.OBJECT_TOSS)) {
				loc8 = true;
			}
			if (loc14.equals(ActivationType.SHOOT)) {
				loc9 = true;
			}
		}
		if (loc8) {
			loc7 = map.pSTopW(param1, param2);
			if (loc7 == null || loc10 && !this.isValidPosition(loc7.x, loc7.y)) {
				SoundEffectLibrary.play("error");
				return false;
			}
		} else {
			loc15 = Math.sqrt(param1 * param1 + param2 * param2) / 50;
			loc7 = new Point(x + loc15 * Math.cos(loc6), y + loc15 * Math.sin(loc6));
		}
		int loc12 = getTimer();
		if (useType == UseType.START_USE) {
			if (loc12 < this.nextAltAttack) {
				SoundEffectLibrary.play("error");
				return false;
			}
			loc13 = loc5.getIntValue("MpCost");
			if (loc13 > this.mp) {
				SoundEffectLibrary.play("no_mana");
				return false;
			}
			loc16 = 500;
			if (loc5.hasOwnProperty("Cooldown")) {
				loc16 = loc5.getIntValue("Cooldown") * 1000;
			}
			this.nextAltAttack = loc12 + loc16;
			map.gs.gsc.useItem(loc12, objectId. 1, loc4, loc7.x, loc7.y, useType);
			if (loc9) {
				this.doShoot(loc12, loc4, loc5, loc6, false);
			}
		} else if (loc5.hasOwnProperty("MultiPhase")) {
			map.gs.gsc.useItem(loc12, objectId, 1, loc4, loc7.x, loc7.y, useType);
			loc13 = loc5.getIntValue("MpEndCost");
			if (loc13 <= this.mp) {
				this.doShoot(loc12, loc4, loc5, loc6, false);
			}
		}
		return true;
	}

	public void attemptAttackAngle(double param1) {
		this.shoot(Parameters.data.cameraAngle + param1);
	}


	@Override
	public void setAttack(int param1, double param2) {
		XML loc3 = ObjectLibrary.xmlLibrary.get(param1);
		if (loc3 == null || !loc3.hasOwnProperty("RateOfFire")) {
			return;
		}
		double loc4 = loc3.getDoubleValue("RateOfFire");
		this.attackPeriod = 1 / this.attackFrequency() * (1 / loc4);
		super.setAttack(param1, param2);
	}

	private void shoot(double param1) {
		if (map == null || isStunned() || isPaused() || isPetrified()) {
			return;
		}
		int loc2 = equipment[0];
		if (loc2 == -1) {
			this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, TextKey.PLAYER_NO_WEAPON_EQUIPPED));
			return;
		}
		XML loc3 = ObjectLibrary.xmlLibrary[loc2];
		int loc4 = getTimer();
		double loc5 = loc3.getDoubleValue("RateOfFire");
		this.attackPeriod = 1 / this.attackFrequency() * (1 / loc5);
		if (loc4 < attackStart + this.attackPeriod) {
			return;
		}
		doneAction(map.gs.Tutorial.ATTACK.CTION);
		attackAngle = param1;
		attackStart = loc4;
		this.doShoot(attackStart, loc2, loc3, attackAngle, true);
	}

	private void doShoot(int param1, int param2, XML param3, double param4, boolean param5) {
		int loc11 = 0;
		Projectile loc12 = null;
		int loc13 = 0;
		int loc14 = 0;
		double loc15 = 0;
		int loc16 = 0;
		int loc6 = param3.hasOwnProperty("NumProjectiles") ? param3.getIntValue("NumProjectiles") : 1;
		double loc7 = (param3.hasOwnProperty("ArcGap") ? param3.getDoubleValue("ArcGap") : 11.25 * Trig.toRadians);
		double loc8 = loc7 * (loc6 - 1);
		double loc9 = param4 - loc8 / 2;
		this.isShooting = param5;
		int loc10 = 0;
		while (loc10 < loc6) {
			loc11 = getBulletId();
			loc12 = new Projectile();
			if (param5 && this.projectileIdSetOverrideNew != "") {
				loc12.reset(param2, 0, objectId, loc11, loc9, param1, this.projectileIdSetOverrideNew, this.projectileIdSetOverrideOld);
			} else {
				loc12.reset(param2, 0, objectId, loc11, loc9, param1);
			}
			loc13 = loc12.projProps.minDamage;
			loc14 = loc12.projProps.maxDamage;
			loc15 = !!param5 ? this.attackMultiplier( : 1;
			loc16 = map.gs.gsc.getNextDamage(loc13, loc14) * loc15;
			if (param1 > map.gs.moveRecords.lastClearTime + 600) {
				loc16 = 0;
			}
			loc12.setDamage(loc16);
			if (loc10 == 0 && loc12.sound != null) {
				SoundEffectLibrary.play(loc12.sound, 0.75, false);
			}
			map.addObj(loc12, x + Math.cos(param4) * 0.3, y + Math.sin(param4) * 0.3);
			map.gs.gsc.playerShoot(param1, loc12);
			loc9 = loc9 + loc7;
			loc10++;
		}
	}

	public boolean isHexed() {
		return (condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.HEXED_BIT) != 0;
	}

	public boolean isInventoryFull() {
		if (equipment == null) {
			return false;
		}
		int loc1 = equipment.length;
		int loc2 = 4;
		while (loc2 < loc1) {
			if (equipment[loc2] <= 0) {
				return false;
			}
			loc2++;
		}
		return true;
	}

	public int nextAvailableInventorySlot() {
		int loc1 = this.hasBackpack ? (int) equipment.length : (int) (equipment.length - GeneralConstants.NUM_INVENTORY_SLOTS);
		int loc2 = 4;
		while (loc2 < loc1) {
			if (equipment[loc2] <= 0) {
				return loc2;
			}
			loc2++;
		}
		return -1;
	}

	public int numberOfAvailableSlots() {
		int loc1 = this.hasBackpack ? (int) equipment.length : (int) (equipment.length - GeneralConstants.NUM_INVENTORY_SLOTS);
		int loc2 = 0;
		int loc3 = 4;
		while (loc3 < loc1) {
			if (equipment[loc3] <= 0) {
				loc2++;
			}
			loc3++;
		}
		return loc2;
	}

	public int swapInventoryIndex(String param1) {
		int loc2 = 0;
		int loc3 = 0;
		if (!this.hasBackpack) {
			return -1;
		}
		if (param1.equals(TabStripModel.BACKPACK)) {
			loc2 = GeneralConstants.NUM_EQUIPMENT_SLOTS;
			loc3 = GeneralConstants.NUM_EQUIPMENT_SLOTS + GeneralConstants.NUM_INVENTORY_SLOTS;
		} else {
			loc2 = GeneralConstants.NUM_EQUIPMENT_SLOTS + GeneralConstants.NUM_INVENTORY_SLOTS;
			loc3 = equipment.length;
		}
		int loc4 = loc2;
		while (loc4 < loc3) {
			if (equipment[loc4] <= 0) {
				return loc4;
			}
			loc4++;
		}
		return -1;
	}

	public int getPotionCount(int param1) {
		switch (param1) {
			case PotionInventoryModel.HEALTH_POTION_ID:
				return this.healthPotionCount;
			case PotionInventoryModel.MAGIC_POTION_ID:
				return this.magicPotionCount;
			default:
				return 0;
		}
	}

	public int getTex1() {
		return tex1Id;
	}

	public int getTex2() {
		return tex2Id;
	}


}

package realmrelay.game.objects;

import java.awt.Point;
import java.util.List;

import realmrelay.game.XML;
import realmrelay.game.objects.animation.AnimatedChar;
import realmrelay.packets.data.unused.BitmapData;
import sun.misc.Signal;

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
    public float mp = 0;
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
    public float distSqFromThisPlayer = 0;
    public int attackPeriod = 0;
    public int nextAltAttack = 0;
    public int nextTeleportAt = 0;
    public int dropBoost = 0;
    public int tierBoost = 0;
    public boolean isDefaultAnimatedChar = true;
    public String projectileIdSetOverrideNew = "";
    public String projectileIdSetOverrideOld = "";
    protected float rotate = 0;
    protected Point relMoveVec = null;
    protected float moveMultiplier = 1;

    /**protected HealingEffect healingEffect = null;
    public Merchant nearestMerchant = null;
    private AddTextLineSignal addTextLine;
    private CharacterFactory factory;
    private IntPoint ip;
    private GraphicsSolidFill breathBackFill = null;
    private GraphicsPath breathBackPath = null;
    private GraphicsSolidFill breathFill = null;
    private GraphicsPath breathPath = null;*/


    public Player(XML param) {
        super(param);
    }
}

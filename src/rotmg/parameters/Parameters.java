package rotmg.parameters;

import alde.flash.utils.Vector;
import flash.display.DisplayObject;
import flash.events.Event;
import flash.net.SharedObject;
import flash.system.Capabilities;
import flash.utils.Dictionary;
import rotmg.map.Map;
import rotmg.util.KeyCodes;
import rotmg.util.MoreDateUtil;

/**
 * The AS3 version uses Parameters.stats.value (Object)
 */
public class Parameters {

	public static final String BUILD_VERSION = "X27.0";
	public static final String MINOR_VERSION = "1";
	public static final boolean ENABLE_ENCRYPTION = true;
	public static final boolean ALLOW_SCREENSHOT_MODE = false;
	public static final boolean USE_NEW_FRIENDS_UI = true;
	public static final double PLAYER_ROTATE_SPEED = 0.003;
	public static final String SERVER_CHAT_NAME = "";
	public static final String CLIENT_CHAT_NAME = "*Client*";
	public static final String ERROR_CHAT_NAME = "*Error*";
	public static final String HELP_CHAT_NAME = "*Help*";
	public static final String GUILD_CHAT_NAME = "*Guild*";
	public static final double NEWS_TIMESTAMP_DEFAULT = 1.1;
	public static final double MAX_SINK_LEVEL = 18;
	public static final String TERMS_OF_USE_URL = "http://legal.decagames.com/tos/";
	public static final String PRIVACY_POLICY_URL = "http://legal.decagames.com/privacy/";
	public static final String USER_GENERATED_CONTENT_TERMS = "/UGDTermsofUse.html";
	public static final String RANDOM1 = "311f80691451c71b09a13a2a6e";
	public static final String RANDOM2 = "72c5583cafb6818995cbd74b80";
	/**
	 * RSA formatted
	 * AS3 format : "-----BEGIN PUBLIC KEY-----\n" + "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCKFctVrhfF3m2Kes0FBL/JFeO" + "cmNg9eJz8k/hQy1kadD+XFUpluRqa//Uxp2s9W2qE0EoUCu59ugcf/p7lGuL99Uo" + "SGmQEynkBvZct+/M40L0E0rZ4BVgzLOJmIbXMp0J4PnPcb6VLZvxazGcmSfjauC7" + "F3yWYqUbZd/HCBtawwIDAQAB\n" + "-----END PUBLIC KEY-----";
	 */
	public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCKFctVrhfF3m2Kes0FBL/JFeOcmNg9eJz8k/hQy1kadD+XFUpluRqa//Uxp2s9W2qE0EoUCu59ugcf/p7lGuL99UoSGmQEynkBvZct+/M40L0E0rZ4BVgzLOJmIbXMp0J4PnPcb6VLZvxazGcmSfjauC7F3yWYqUbZd/HCBtawwIDAQAB\n";
	public static final Vector<Integer> skinTypes16 = new Vector<Integer>(1027, 1028, 1029, 1030, 10973, 19494, 19531);
	public static final Vector<Integer> itemTypes16 = new Vector<Integer>(5473, 5474, 5475, 5476, 10939, 19494, 19531);
	public static int PORT = 2050;
	public static DisplayObject root;
	public static Data data = new Data();
	public static boolean GPURenderError = false;
	public static boolean sessionStarted = false;
	public static boolean drawProj = true;

	public static boolean screenShotMode = false;

	public static boolean screenShotSlimMode = false;

	public static boolean sendLogin = true;
	public static int blendType = 1;
	public static int FELLOW_GUILD_COLOR = 10944349;
	public static int NAME_CHOSEN_COLOR = 16572160;
	public static int BREATH_THRESH = 20;
	public static int NAME_CHANGE_PRICE = 1000;
	public static int GUILD_CREATION_PRICE = 1000;
	public static int projColorType = 0;
	public static int TUTORIAL_GAMEID = -1;
	public static int NEXUS_GAMEID = -2;
	public static int RANDOM_REALM_GAMEID = -3;
	public static int MAPTEST_GAMEID = -6;
	public static Object stats;
	private static Dictionary keyNames = new Dictionary();
	private static SharedObject savedOptions = null;

	public Parameters() {
		super();
	}

	public static void load() {
		/*try {
			savedOptions = SharedObject.getLocal("AssembleeGameClientOptions", "/");
			stats = savedOptions.stats;
		} catch (Error error) {
			stats = null;
		}*/
		setDefaults();
		save();
	}

	public static void save() {
		try {
			if (savedOptions != null) {
				savedOptions.flush();
			}
			return;
		} catch (Error error) {
			return;
		}
	}

	private static void setDefaultKey(String param1, int param2) {
		if (!data.contains(param1)) {
			data.put(param1, param2);
		}
		keyNames.put(param1, true);
	}

	public static void setKey(String param1, int param2) {
		data.put(param1, param2);
	}

	private static void setDefault(String param1, Object param2) {
		if (!data.contains(param1)) {
			data.put(param1, param2);
		}
	}

	public static boolean isGpuRender() {
		return !GPURenderError && (boolean) data.GPURender && !Map.forceSoftwareRender;
	}

	public static void clearGpuRenderEvent(Event param1) {
		clearGpuRender();
	}

	public static void clearGpuRender() {
		GPURenderError = true;
	}

	public static void setDefaults() {
		setDefaultKey("moveLeft", KeyCodes.A);
		setDefaultKey("moveRight", KeyCodes.D);
		setDefaultKey("moveUp", KeyCodes.W);
		setDefaultKey("moveDown", KeyCodes.S);
		setDefaultKey("rotateLeft", KeyCodes.Q);
		setDefaultKey("rotateRight", KeyCodes.E);
		setDefaultKey("useSpecial", KeyCodes.SPACE);
		setDefaultKey("interact", KeyCodes.NUMBER_0);
		setDefaultKey("useInvSlot1", KeyCodes.NUMBER_1);
		setDefaultKey("useInvSlot2", KeyCodes.NUMBER_2);
		setDefaultKey("useInvSlot3", KeyCodes.NUMBER_3);
		setDefaultKey("useInvSlot4", KeyCodes.NUMBER_4);
		setDefaultKey("useInvSlot5", KeyCodes.NUMBER_5);
		setDefaultKey("useInvSlot6", KeyCodes.NUMBER_6);
		setDefaultKey("useInvSlot7", KeyCodes.NUMBER_7);
		setDefaultKey("useInvSlot8", KeyCodes.NUMBER_8);
		setDefaultKey("escapeToNexus2", KeyCodes.F5);
		setDefaultKey("escapeToNexus", KeyCodes.R);
		setDefaultKey("autofireToggle", KeyCodes.I);
		setDefaultKey("scrollChatUp", KeyCodes.PAGE_UP);
		setDefaultKey("scrollChatDown", KeyCodes.PAGE_DOWN);
		setDefaultKey("miniMapZoomOut", KeyCodes.MINUS);
		setDefaultKey("miniMapZoomIn", KeyCodes.EQUAL);
		setDefaultKey("resetToDefaultCameraAngle", KeyCodes.Z);
		setDefaultKey("togglePerformanceStats", KeyCodes.UNSET);
		setDefaultKey("options", KeyCodes.O);
		setDefaultKey("toggleCentering", KeyCodes.X);
		setDefaultKey("chat", KeyCodes.ENTER);
		setDefaultKey("chatCommand", KeyCodes.SLASH);
		setDefaultKey("tell", KeyCodes.TAB);
		setDefaultKey("guildChat", KeyCodes.G);
		setDefaultKey("testOne", KeyCodes.PERIOD);
		setDefaultKey("toggleFullscreen", KeyCodes.UNSET);
		setDefaultKey("useHealthPotion", KeyCodes.F);
		setDefaultKey("GPURenderToggle", KeyCodes.UNSET);
		setDefaultKey("friendList", KeyCodes.UNSET);
		setDefaultKey("useMagicPotion", KeyCodes.V);
		setDefaultKey("switchTabs", KeyCodes.B);
		setDefaultKey("particleEffect", KeyCodes.P);
		setDefaultKey("toggleHPBar", KeyCodes.H);
		setDefaultKey("toggleProjectiles", KeyCodes.N);
		setDefaultKey("toggleMasterParticles", KeyCodes.M);
		setDefault("playerObjectType", 782);
		setDefault("playMusic", true);
		setDefault("playSFX", true);
		setDefault("playPewPew", true);
		setDefault("centerOnPlayer", true);
		setDefault("preferredServer", null);
		setDefault("bestServer", null);
		setDefault("needsTutorial", true);
		setDefault("needsRandomRealm", true);
		setDefault("cameraAngle", 0);
		setDefault("defaultCameraAngle", 0);
		setDefault("showQuestPortraits", true);
		setDefault("fullscreenMode", false);
		setDefault("showProtips", true);
		setDefault("protipIndex", 0);
		setDefault("joinDate", MoreDateUtil.getDayStringInPT());
		setDefault("lastDailyAnalytics", null);
		setDefault("allowRotation", true);
		setDefault("allowMiniMapRotation", false);
		setDefault("charIdUseMap", new Vector<>());
		setDefault("drawShadows", true);
		setDefault("textBubbles", true);
		setDefault("showTradePopup", true);
		setDefault("paymentMethod", null);
		setDefault("filterLanguage", true);
		setDefault("showGuildInvitePopup", true);
		setDefault("showBeginnersOffer", false);
		setDefault("beginnersOfferTimeLeft", 0);
		setDefault("beginnersOfferShowNow", false);
		setDefault("beginnersOfferShowNowTime", 0);
		setDefault("watchForTutorialExit", false);
		setDefault("clickForGold", false);
		setDefault("contextualPotionBuy", false);
		setDefault("inventorySwap", true);
		setDefault("particleEffect", false);
		setDefault("uiQuality", true);
		setDefault("disableEnemyParticles", false);
		setDefault("disableAllyParticles", false);
		setDefault("disablePlayersHitParticles", false);
		setDefault("cursorSelect", "4");
		setDefault("friendListDisplayFlag", false);
		if (Capabilities.playerType.equals("Desktop")) {
			setDefault("GPURender", false);
		} else {
			setDefault("GPURender", false);
		}
		setDefault("forceChatQuality", false);
		setDefault("hidePlayerChat", false);
		setDefault("chatStarRequirement", 2);
		setDefault("chatAll", true);
		setDefault("chatWhisper", true);
		setDefault("chatGuild", true);
		setDefault("chatTrade", true);
		setDefault("toggleBarText", 0);
		setDefault("toggleToMaxText", false);
		setDefault("particleEffect", true);
		if (data.contains("playMusic") && data.playMusic) {
			setDefault("musicVolume", 1);
		} else {
			setDefault("musicVolume", 0);
		}
		if (data.contains("playSFX") && data.playMusic) {
			setDefault("SFXVolume", 1);
		} else {
			setDefault("SFXVolume", 0);
		}
		setDefault("friendList", KeyCodes.UNSET);
		setDefault("tradeWithFriends", false);
		setDefault("chatFriend", false);
		setDefault("friendStarRequirement", 0);
		setDefault("HPBar", 1);
		setDefault("newMiniMapColors", false);
		setDefault("noParticlesMaster", false);
		setDefault("noAllyNotifications", false);
		setDefault("noAllyDamage", false);
		setDefault("noEnemyDamage", false);
		setDefault("forceEXP", 0);
		setDefault("showFameGain", false);
		setDefault("curseIndication", false);
	}

	/**
	 * Replacement for AS3's 'stats' Object
	 * <p>
	 * Made final for now... For switch in MapUserInput
	 */
	public static class Data {

		public static boolean playMusic = true;
		public static boolean playSFX = true;
		public static boolean playPewPew = true;
		public static boolean centerOnPlayer = true;
		public static String preferredServer = null;
		public static String bestServer = null;
		public static boolean needsTutorial = true;
		public static boolean needsRandomRealm = true;
		public static boolean showQuestPortraits = true;
		public static boolean showProtips = true;
		public static String joinDate = MoreDateUtil.getDayStringInPT();
		public static String lastDailyAnalytics = null;
		public static boolean allowRotation = true;
		public static boolean allowMiniMapRotation = false;
		public static Vector<Integer> charIdUseMap = null;
		public static boolean drawShadows = true;
		public static boolean textBubbles = true;
		public static boolean showTradePopup = true;
		public static String paymentMethod = null;
		public static boolean filterLanguage = true;
		public static boolean showGuildInvitePopup = true;
		public static boolean showBeginnersOffer = false;
		public static boolean beginnersOfferShowNow = false;
		public static boolean watchForTutorialExit = false;
		public static boolean clickForGold = false;
		public static boolean contextualPotionBuy = false;
		public static boolean inventorySwap = true;
		public static boolean disableEnemyParticles = false;
		public static boolean disableAllyParticles = false;
		public static boolean disablePlayersHitParticles = false;
		public static String cursorSelect = "4";
		public static boolean friendListDisplayFlag = false;
		public static boolean GPURender = false;
		public static boolean forceChatQuality = false;
		public static boolean hidePlayerChat = false;
		public static boolean chatAll = true;
		public static boolean chatWhisper = true;
		public static boolean chatGuild = true;
		public static boolean chatTrade = true;
		public static boolean toggleToMaxText = false;
		public static boolean tradeWithFriends = false;
		public static boolean chatFriend = false;
		public static boolean newMiniMapColors = false;
		public static boolean noParticlesMaster = false;
		public static boolean noAllyNotifications = false;
		public static boolean noAllyDamage = false;
		public static boolean noEnemyDamage = false;
		public static boolean showFameGain = false;
		public static boolean curseIndication = false;
		public static int cameraAngle = 0;
		public final int moveUp = KeyCodes.W;
		public final int moveDown = KeyCodes.S;
		public final int rotateLeft = KeyCodes.Q;
		public boolean fullscreenMode = false;
		public boolean uiQuality = true;
		public int moveLeft = KeyCodes.A;
		public int moveRight = KeyCodes.D;
		public int rotateRight = KeyCodes.E;
		public int useSpecial = KeyCodes.SPACE;
		public int interact = KeyCodes.NUMBER_0;
		public int useInvSlot1 = KeyCodes.NUMBER_1;
		public int useInvSlot2 = KeyCodes.NUMBER_2;
		public int useInvSlot3 = KeyCodes.NUMBER_3;
		public int useInvSlot4 = KeyCodes.NUMBER_4;
		public int useInvSlot5 = KeyCodes.NUMBER_5;
		public int useInvSlot6 = KeyCodes.NUMBER_6;
		public int useInvSlot7 = KeyCodes.NUMBER_7;
		public int useInvSlot8 = KeyCodes.NUMBER_8;
		public int escapeToNexus2 = KeyCodes.F5;
		public int escapeToNexus = KeyCodes.R;
		public int autofireToggle = KeyCodes.I;
		public int scrollChatUp = KeyCodes.PAGE_UP;
		public int scrollChatDown = KeyCodes.PAGE_DOWN;
		public int miniMapZoomOut = KeyCodes.MINUS;
		public int miniMapZoomIn = KeyCodes.EQUAL;
		public int resetToDefaultCameraAngle = KeyCodes.Z;
		public int togglePerformanceStats = KeyCodes.UNSET;
		public int options = KeyCodes.O;
		public int toggleCentering = KeyCodes.X;
		public int chat = KeyCodes.ENTER;
		public int chatCommand = KeyCodes.SLASH;
		public int tell = KeyCodes.TAB;
		public int guildChat = KeyCodes.G;
		public int testOne = KeyCodes.PERIOD;
		public int toggleFullscreen = KeyCodes.UNSET;
		public int useHealthPotion = KeyCodes.F;
		public int GPURenderToggle = KeyCodes.UNSET;
		public int useMagicPotion = KeyCodes.V;
		public int switchTabs = KeyCodes.B;
		public int particleEffect = KeyCodes.P;
		public int toggleHPBar = KeyCodes.H;
		public int toggleProjectiles = KeyCodes.N;
		public int toggleMasterParticles = KeyCodes.M;
		public int playerObjectType = 782;
		public int defaultCameraAngle = 0;
		public int protipIndex = 0;
		public int beginnersOfferTimeLeft = 0;
		public int beginnersOfferShowNowTime = 0;
		public int chatStarRequirement = 2;
		public int toggleBarText = 0;
		public int musicVolume = 0;
		public int SFXVolume = 1;
		public int friendList = KeyCodes.UNSET;
		public int friendStarRequirement = 0;
		public int HPBar = 1;
		public int forceEXP = 0;

		public boolean contains(String param1) {
			return true;
		}

		public void put(String param1, Object param2) {
		}
	}
}



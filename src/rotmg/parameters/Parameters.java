package rotmg.parameters;

import alde.flash.utils.Dictionary;
import flash.display.DisplayObject;
import flash.net.SharedObject;
import flash.system.Capabilities;
import rotmg.map.Map;
import rotmg.util.KeyCodes;
import rotmg.util.MoreDateUtil;

public class Parameters {

	public static final String BUILD_VERSION = "X25.1";

	public static final String MINOR_VERSION = "1";

	public static final boolean ENABLE_ENCRYPTION = true;

	public static final int PORT = 2050;

	public static final boolean ALLOW_SCREENSHOT_MODE = false;

	public static final boolean USE_NEW_FRIENDS_UI = true;

	public static final int FELLOW_GUILD_COLOR = 10944349;

	public static final int NAME_CHOSEN_COLOR = 16572160;

	public static DisplayObject root;

	public static final double PLAYER_ROTATE_SPEED = 0.003;

	public static final int BREATH_THRESH = 20;

	public static final String SERVER_CHAT_NAME = "";

	public static final String CLIENT_CHAT_NAME = "*Client*";

	public static final String ERROR_CHAT_NAME = "*Error*";

	public static final String HELP_CHAT_NAME = "*Help*";

	public static final String GUILD_CHAT_NAME = "*Guild*";

	public static final double NEWS_TIMESTAMP_DEFAULT = 1.1;

	public static final int NAME_CHANGE_PRICE = 1000;

	public static final int GUILD_CREATION_PRICE = 1000;

	public static Object data = null;

	public static boolean GPURenderError = false;

	public static boolean sessionStarted = false;

	public static int blendType = 1;

	public static int projColorType = 0;

	public static boolean drawProj = true;

	public static boolean screenShotMode = false;

	public static boolean screenShotSlimMode = false;

	public static boolean sendLogin = true;

	public static final int TUTORIAL_GAMEID = -1;

	public static final int NEXUS_GAMEID = -2;

	public static final int RANDOM_REALM_GAMEID = -3;

	public static final int MAPTEST_GAMEID = -6;

	public static final double MAX_SINK_LEVEL = 18;

	public static final String TERMS_OF_USE_URL = "http://legal.decagames.com/tos/";

	public static final String PRIVACY_POLICY_URL = "http://legal.decagames.com/privacy/";

	public static final String USER_GENERATED_CONTENT_TERMS = "/UGDTermsofUse.html";

	public static final String RANDOM1 = "311f80691451c71b09a13a2a6e";

	public static final String RANDOM2 = "72c5583cafb6818995cbd74b80";

	public static final String RSA_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" + "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCKFctVrhfF3m2Kes0FBL/JFeO" + "cmNg9eJz8k/hQy1kadD+XFUpluRqa//Uxp2s9W2qE0EoUCu59ugcf/p7lGuL99Uo" + "SGmQEynkBvZct+/M40L0E0rZ4BVgzLOJmIbXMp0J4PnPcb6VLZvxazGcmSfjauC7" + "F3yWYqUbZd/HCBtawwIDAQAB\n" + "-----END PUBLIC KEY-----";

	private static SharedObject savedOptions = null;

	public static final int[] skinTypes16 = new int[]{
			1027, 1028, 1029, 1030, 10973, 19494, 19531
	};

	public static final int[] itemTypes16 = new int[]{
			5473, 5474, 5475, 5476, 10939, 19494, 19531
	};

	private static Dictionary keyNames = new Dictionary();

	public Parameters() {
		super();
	}

	public static void load() {
		try {
			savedOptions = SharedObject.getLocal("AssembleeGameClientOptions", "/");
			data = savedOptions.data;
		} catch (Error error) {
			data = null;
		}
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
		if (!data.hasOwnProperty(param1)) {
			data[param1] = param2;
		}
		keyNames[param1] = true;
	}

	public static void setKey(String param1, int param2) {
         *loc3 = null;
		for (keyNames.contains(loc3)) {
			if (data[loc3] == param2) {
				data[loc3] = KeyCodes.UNSET;
			}
		}
		data[param1] = param2;
	}

	private static void setDefault(String param1, Object param2) {
		if (!data.hasOwnProperty(param1)) {
			data[param1] = param2;
		}
	}

	public static boolean isGpuRender() {
		return !GPURenderError && data.GPURender && !Map.forceSoftwareRender;
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
		setDefault("charIdUseMap", {});
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
		if (data.hasOwnProperty("playMusic") && data.playMusic == true) {
			setDefault("musicVolume", 1);
		} else {
			setDefault("musicVolume", 0);
		}
		if (data.hasOwnProperty("playSFX") && data.playMusic == true) {
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
}



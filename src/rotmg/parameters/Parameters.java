package rotmg.parameters;

import rotmg.util.KeyCodes;
import rotmg.util.MoreDateUtil;

/**
 * This class is about 20% done
 */
public class Parameters {

	public static final String BUILD_VERSION = "X25.1";
	public static final String MINOR_VERSION = "1";

	public static final boolean ENABLE_ENCRYPTION = true;

	public static final int PORT = 2050;

	public static final boolean ALLOW_SCREENSHOT_MODE = false;
	public static final int FELLOW_GUILD_COLOR = 10944349;
	public static final int NAME_CHOSEN_COLOR = 16572160;
	public static final double PLAYER_ROTATE_SPEED = 0.003F;
	public static final int BREATH_THRESH = 20;
	public static final String SERVER_CHAT_NAME = "";
	public static final String CLIENT_CHAT_NAME = "*Client*";
	public static final String ERROR_CHAT_NAME = "*Error*";
	public static final String HELP_CHAT_NAME = "*Help*";
	public static final String GUILD_CHAT_NAME = "*Guild*";
	public static final double NEWS_TIMESTAMP_DEFAULT = 1.1F;
	public static final int NAME_CHANGE_PRICE = 1000;
	public static final int GUILD_CREATION_PRICE = 1000;
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
	
	//Remove ''BEGIN'' and ''END'' public key delimiters
	public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCKFctVrhfF3m2Kes0FBL/JFeO" + "cmNg9eJz8k/hQy1kadD+XFUpluRqa//Uxp2s9W2qE0EoUCu59ugcf/p7lGuL99Uo" + "SGmQEynkBvZct+/M40L0E0rZ4BVgzLOJmIbXMp0J4PnPcb6VLZvxazGcmSfjauC7" + "F3yWYqUbZd/HCBtawwIDAQAB\n";
	public static final int skinTypes16[] = new int[]{1027, 1028, 1029, 1030, 10973, 19494, 19531};
	public static final int itemTypes16[] = new int[]{5473, 5474, 5475, 5476, 10939, 19494, 19531};
	//public static DisplayObject root;
	public static Object data = null;
	public static boolean GPURenderError = false;
	public static int blendType = 1;
	public static int projColorType = 0;
	public static boolean drawProj = true;
	public static boolean screenShotMode = false;
	public static boolean screenShotSlimMode = false;
	public static boolean sendLogin = true;

	public static void load() {
		System.out.println("Request to load parameters");

		//load parameters heres
	}
	//private static SharedObject savedOptions = null;
	//private static Dictionary keyNames = new Dictionary(;


	// Begin Data
	public Object moveLeft = KeyCodes.A;
	public Object moveRight = KeyCodes.D;
	public Object moveUp = KeyCodes.W;
	public Object moveDown = KeyCodes.S;
	public Object rotateLeft = KeyCodes.Q;
	public Object rotateRight = KeyCodes.E;
	public Object useSpecial = KeyCodes.SPACE;
	public Object interact = KeyCodes.NUMBER_0;
	public Object useInvSlot1 = KeyCodes.NUMBER_1;
	public Object useInvSlot2 = KeyCodes.NUMBER_2;
	public Object useInvSlot3 = KeyCodes.NUMBER_3;
	public Object useInvSlot4 = KeyCodes.NUMBER_4;
	public Object useInvSlot5 = KeyCodes.NUMBER_5;
	public Object useInvSlot6 = KeyCodes.NUMBER_6;
	public Object useInvSlot7 = KeyCodes.NUMBER_7;
	public Object useInvSlot8 = KeyCodes.NUMBER_8;
	public Object escapeToNexus2 = KeyCodes.F5;
	public Object escapeToNexus = KeyCodes.R;
	public Object autofireToggle = KeyCodes.I;
	public Object scrollChatUp = KeyCodes.PAGE_UP;
	public Object scrollChatDown = KeyCodes.PAGE_DOWN;
	public Object miniMapZoomOut = KeyCodes.MINUS;
	public Object miniMapZoomIn = KeyCodes.EQUAL;
	public Object resetToDefaultCameraAngle = KeyCodes.Z;
	public Object togglePerformanceStats = KeyCodes.UNSET;
	public Object options = KeyCodes.O;
	public Object toggleCentering = KeyCodes.X;
	public Object chat = KeyCodes.ENTER;
	public Object chatCommand = KeyCodes.SLASH;
	public Object tell = KeyCodes.TAB;
	public Object guildChat = KeyCodes.G;
	public Object testOne = KeyCodes.PERIOD;
	public Object toggleFullscreen = KeyCodes.UNSET;
	public Object useHealthPotion = KeyCodes.F;
	public Object GPURenderToggle = KeyCodes.UNSET;
	public Object friendList = KeyCodes.UNSET;
	public Object useMagicPotion = KeyCodes.V;
	public Object switchTabs = KeyCodes.B;
	public Object toggleParticleEffect = KeyCodes.P;  // from particleEffect
	public Object toggleHPBar = KeyCodes.H;
	public Object toggleProjectiles = KeyCodes.N;
	public Object toggleMasterParticles = KeyCodes.M;
	public Object playerObjectType = 782;
	public Object playMusic = true;
	public Object playSFX = true;
	public Object playPewPew = true;
	public static boolean centerOnPlayer = true;
	public Object preferredServer = null;
	public Object bestServer = null;
	public Object needsTutorial = true;
	public Object needsRandomRealm = true;
	public static double cameraAngle = 0;
	public Object defaultCameraAngle = 0;
	public Object showQuestPortraits = true;
	public Object fullscreenMode = false;
	public Object showProtips = true;
	public Object protipIndex = 0;
	public Object joinDate = MoreDateUtil.getDayStringInPT();
	public Object lastDailyAnalytics = null;
	public Object allowRotation = true;
	public Object allowMiniMapRotation = false;
	public Object charIdUseMap = null;
	public Object drawShadows = true;
	public Object textBubbles = true;
	public Object showTradePopup = true;
	public Object paymentMethod = null;
	public Object filterLanguage = true;
	public Object showGuildInvitePopup = true;
	public Object showBeginnersOffer = false;
	public Object beginnersOfferTimeLeft = 0;
	public Object beginnersOfferShowNow = false;
	public Object beginnersOfferShowNowTime = 0;
	public Object watchForTutorialExit = false;
	public Object clickForGold = false;
	public Object contextualPotionBuy = false;
	public Object inventorySwap = true;
	public Object uiQuality = true;
	public Object disableEnemyParticles = false;
	public Object disableAllyParticles = false;
	public Object disablePlayersHitParticles = false;
	public Object cursorSelect = "4";
	public Object friendListDisplayFlag = false;
	public Object GPURender = false;
	public Object forceChatQuality = false;
	public Object hidePlayerChat = false;
	public Object chatStarRequirement = 2;
	public Object chatAll = true;
	public Object chatWhisper = true;
	public Object chatGuild = true;
	public Object chatTrade = true;
	public Object toggleBarText = 0;
	public Object toggleToMaxText = false;
	public Object particleEffect = true;
	public Object musicVolume = 1;
	public Object SFXVolume = 1;
	public Object tradeWithFriends = false;
	public Object chatFriend = false;
	public Object friendStarRequirement = 0;
	public Object HPBar = 1;
	public Object newMiniMapColors = false;
	public Object noParticlesMaster = false;
	public Object noAllyNotifications = false;
	public Object noAllyDamage = false;
	public Object noEnemyDamage = false;
	public Object forceEXP = 0;
	public Object showFameGain = false;
	public Object curseIndication = false;


	public static boolean isGpuRender() {
		return true;
	}
}



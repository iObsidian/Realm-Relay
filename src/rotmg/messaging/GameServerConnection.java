package rotmg.messaging;

import flash.display.DisplayObject;
import org.osflash.signals.Signal;
import rotmg.AGameSprite;
import rotmg.messaging.data.SlotObjectData;
import rotmg.net.Server;
import rotmg.net.SocketServer;
import rotmg.objects.GameObject;
import rotmg.objects.Player;
import rotmg.objects.Projectile;

import java.util.List;
import java.util.Random;

public class GameServerConnection {

	public static final int FAILURE = 0;
	public static final int CREATE_SUCCESS = 44;
	public static final int CREATE = 52;
	public static final int PLAYERSHOOT = 8;
	public static final int MOVE = 74;
	public static final int PLAYERTEXT = 59;
	public static final int TEXT = 20;
	public static final int SERVERPLAYERSHOOT = 5;
	public static final int DAMAGE = 58;
	public static final int UPDATE = 31;
	public static final int UPDATEACK = 80;
	public static final int NOTIFICATION = 47;
	public static final int NEWTICK = 36;
	public static final int INVSWAP = 27;
	public static final int USEITEM = 39;
	public static final int SHOWEFFECT = 28;
	public static final int HELLO = 100;
	public static final int GOTO = 76;
	public static final int INVDROP = 46;
	public static final int INVRESULT = 82;
	public static final int RECONNECT = 12;
	public static final int PING = 4;
	public static final int PONG = 86;
	public static final int MAPINFO = 85;
	public static final int LOAD = 62;
	public static final int PIC = 50;
	public static final int SETCONDITION = 19;
	public static final int TELEPORT = 99;
	public static final int USEPORTAL = 91;
	public static final int DEATH = 104;
	public static final int BUY = 68;
	public static final int BUYRESULT = 87;
	public static final int AOE = 18;
	public static final int GROUNDDAMAGE = 84;
	public static final int PLAYERHIT = 67;
	public static final int ENEMYHIT = 96;
	public static final int AOEACK = 102;
	public static final int SHOOTACK = 3;
	public static final int OTHERHIT = 25;
	public static final int SQUAREHIT = 95;
	public static final int GOTOACK = 81;
	public static final int EDITACCOUNTLIST = 21;
	public static final int ACCOUNTLIST = 1;
	public static final int QUESTOBJID = 7;
	public static final int CHOOSENAME = 9;
	public static final int NAMERESULT = 13;
	public static final int CREATEGUILD = 40;
	public static final int GUILDRESULT = 65;
	public static final int GUILDREMOVE = 101;
	public static final int GUILDINVITE = 75;
	public static final int ALLYSHOOT = 63;
	public static final int ENEMYSHOOT = 92;
	public static final int REQUESTTRADE = 6;
	public static final int TRADEREQUESTED = 14;
	public static final int TRADESTART = 15;
	public static final int CHANGETRADE = 26;
	public static final int TRADECHANGED = 93;
	public static final int ACCEPTTRADE = 17;
	public static final int CANCELTRADE = 55;
	public static final int TRADEDONE = 77;
	public static final int TRADEACCEPTED = 57;
	public static final int CLIENTSTAT = 66;
	public static final int CHECKCREDITS = 35;
	public static final int ESCAPE = 41;
	public static final int FILE = 42;
	public static final int INVITEDTOGUILD = 83;
	public static final int JOINGUILD = 94;
	public static final int CHANGEGUILDRANK = 51;
	public static final int PLAYSOUND = 61;
	public static final int GLOBAL_NOTIFICATION = 90;
	public static final int RESKIN = 69;
	public static final int PETUPGRADEREQUEST = 11;
	public static final int ACTIVE_PET_UPDATE_REQUEST = 53;
	public static final int ACTIVEPETUPDATE = 56;
	public static final int NEW_ABILITY = 60;
	public static final int PETYARDUPDATE = 38;
	public static final int EVOLVE_PET = 97;
	public static final int DELETE_PET = 78;
	public static final int HATCH_PET = 22;
	public static final int ENTER_ARENA = 89;
	public static final int IMMINENT_ARENA_WAVE = 23;
	public static final int ARENA_DEATH = 34;
	public static final int ACCEPT_ARENA_DEATH = 10;
	public static final int VERIFY_EMAIL = 49;
	public static final int RESKIN_UNLOCK = 98;
	public static final int PASSWORD_PROMPT = 48;
	public static final int QUEST_FETCH_ASK = 16;
	public static final int QUEST_REDEEM = 24;
	public static final int QUEST_FETCH_RESPONSE = 79;
	public static final int QUEST_REDEEM_RESPONSE = 30;
	public static final int PET_CHANGE_FORM_MSG = 64;
	public static final int KEY_INFO_REQUEST = 45;
	public static final int KEY_INFO_RESPONSE = 33;
	public static final int CLAIM_LOGIN_REWARD_MSG = 103;
	public static final int LOGIN_REWARD_MSG = 37;
	public static final int QUEST_ROOM_MSG = 88;
	public static final int PET_CHANGE_SKIN_MSG = 105;

	public static GameServerConnection instance;
	public Signal changeMapSignal;
	public AGameSprite gs;
	public Server server;
	public int gameId;
	public boolean createCharacter;
	public int charId;
	public int keyTime;
	public byte[] key;
	public byte[] mapJSON; // is a String in AS3's client TODO
	public boolean isFromArena = false;
	public int lastTickId = -1;
	//public JitterWatcher jitterWatcher;
	public SocketServer serverConnection;

	public OutstandingBuy outstandingBuy = null;
	public Random rand;
	public DisplayObject jitterWatcher;

	public GameServerConnection() {
		super();
	}

	public void chooseName(String name) {
	}

	public void createGuild(String name) {
	}

	public void connect() {
	}

	public void disconnect() {
	}

	public void checkCredits() {
	}

	public void escape() {
	}

	public void useItem(int time, int objectId, int slotId, int itemId, double x, double y, int useType) {
	}

	public boolean useItem_new(GameObject itemOwner, int slotId) {
		return false;
	}

	public void enableJitterWatcher() {
	}

	public void disableJitterWatcher() {
	}

	public void editAccountList(int accountListId, boolean add, int objectId) {
	}

	public void guildRemove(String param1) {
	}

	public void guildInvite(String param1) {
	}

	public void requestTrade(String param1) {
	}

	public void changeTrade(boolean[] param1) {
	}

	public void acceptTrade(boolean[] param1, boolean[] param2) {
	}

	public void cancelTrade() {
	}

	public void joinGuild(String param1) {
	}

	public void changeGuildRank(String param1, int param2) {
	}

	public boolean isConnected() {
		return false;
	}

	public void teleport(int param1) {
	}

	public void usePortal(int param1) {
	}

	public int getNextDamage(int param1, int param2) {
		return 0;
	}

	public void groundDamage(int param1, double param2, double param3) {
	}

	public void playerShoot(int param1, Projectile param2) {
	}

	public void playerHit(int param1, int param2) {
	}

	public void enemyHit(int param1, int param2, int param3, boolean param4) {
	}

	public void otherHit(int param1, int param2, int param3, int param4) {
	}

	public void squareHit(int param1, int param2, int param3) {
	}

	public void playerText(String param1) {
	}

	public boolean invSwap(Player player, GameObject sourceObj, int slotId1, int itemId1, GameObject targetObj,
	                       int slotId2, int itemId2) {
		return false;
	}

	public boolean invSwapRaw(Player player, int objectId1, int slotId1, int objectType1, int objectId2, int slotId2,
	                          int objectType2) {
		return false;
	}

	public boolean invSwapPotion(Player param1, GameObject param2, int param3, int param4, GameObject param5,
	                             int param6, int param7) {
		return false;
	}

	public void invDrop(GameObject param1, int param2, int param3) {
	}

	public void setCondition(int param1, double param2) {
	}

	public void buy(int param1, int param2) {
	}

	public void questFetch() {
	}

	public void questRedeem(String param1, List<SlotObjectData> param2) {
	}

	public void keyInfoRequest(int param1) {
	}

	public void claimRewardsMessageHack(String claimKey, String type) {
	}

	public void gotoQuestRoom() {
	}

	public void petCommand(int commandId, int petId) {
	}

}

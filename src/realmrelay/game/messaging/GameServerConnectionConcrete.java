package realmrelay.game.messaging;

import realmrelay.game._as3.Timer;
import realmrelay.game._as3.TimerEvent;
import realmrelay.game._as3.XML;
import realmrelay.game.account.core.Account;
import realmrelay.game.api.MessageProvider;
import realmrelay.game.arena.model.ArenaDeathSignal;
import realmrelay.game.arena.model.ImminentArenaWaveSignal;
import realmrelay.game.chat.model.ChatMessage;
import realmrelay.game.classes.model.CharacterClass;
import realmrelay.game.classes.model.ClassesModel;
import realmrelay.game.constants.GeneralConstants;
import realmrelay.game.constants.ItemConstants;
import realmrelay.game.dailyLogin.signal.ClaimDailyRewardResponseSignal;
import realmrelay.game.dailyQuests.QuestFetchResponse;
import realmrelay.game.dailyQuests.QuestRedeemResponse;
import realmrelay.game.dailyQuests.signal.QuestFetchCompleteSignal;
import realmrelay.game.dailyQuests.signal.QuestRedeemCompleteSignal;
import realmrelay.game.death.control.HandleDeathSignal;
import realmrelay.game.death.control.ZombifySignal;
import realmrelay.game.dialogs.CloseDialogsSignal;
import realmrelay.game.dialogs.OpenDialogSignal;
import realmrelay.game.events.KeyInfoResponseSignal;
import realmrelay.game.events.ReconnectEvent;
import realmrelay.game.focus.control.SetGameFocusSignal;
import realmrelay.game.focus.control.UpdateGroundTileSignal;
import realmrelay.game.game.AGameSprite;
import realmrelay.game.map.AbstractMap;
import realmrelay.game.map.GroundLibrary;
import realmrelay.game.map.Map;
import realmrelay.game.maploading.signals.ChangeMapSignal;
import realmrelay.game.messaging.data.GroundTileData;
import realmrelay.game.messaging.data.ObjectData;
import realmrelay.game.messaging.data.ObjectStatusData;
import realmrelay.game.messaging.data.StatData;
import realmrelay.game.messaging.impl.*;
import realmrelay.game.messaging.incoming.*;
import realmrelay.game.messaging.incoming.arena.ArenaDeath;
import realmrelay.game.messaging.incoming.arena.ImminentArenaWave;
import realmrelay.game.messaging.incoming.pets.DeletePetMessage;
import realmrelay.game.messaging.outgoing.*;
import realmrelay.game.messaging.outgoing.arena.EnterArena;
import realmrelay.game.messaging.outgoing.arena.QuestRedeem;
import realmrelay.game.minimap.control.UpdateGameObjectTileSignal;
import realmrelay.game.model.PotionInventoryModel;
import realmrelay.game.net.Server;
import realmrelay.game.net.SocketServer;
import realmrelay.game.net.api.MessageMap;
import realmrelay.game.net.impl.Message;
import realmrelay.game.net.impl.MessageCenter;
import realmrelay.game.objects.*;
import realmrelay.game.parameters.Parameters;
import realmrelay.game.pets.controller.PetFeedResultSignal;
import realmrelay.game.pets.data.PetsModel;
import realmrelay.game.signals.AddSpeechBalloonSignal;
import realmrelay.game.signals.AddTextLineSignal;
import realmrelay.game.signals.GiftStatusUpdateSignal;
import realmrelay.game.sound.SoundEffectLibrary;
import realmrelay.game.ui.model.Key;
import realmrelay.game.ui.signals.ShowKeySignal;
import realmrelay.game.ui.signals.ShowKeyUISignal;
import realmrelay.game.ui.signals.UpdateBackpackTabSignal;
import realmrelay.game.ui.view.NotEnoughFameDialog;
import realmrelay.game.ui.view.NotEnoughGoldDialog;
import realmrelay.game.util.ConditionEffect;
import realmrelay.game.util.Currency;
import realmrelay.game.util.TextKey;
import realmrelay.packets.data.unused.BitmapData;

import java.security.interfaces.RSAKey;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameServerConnectionConcrete extends GameServerConnection {

	private static final int TO_MILLISECONDS = 1000;
	//private PetUpdater petUpdater;
	private MessageProvider messages;
	private int playerId = -1;
	private Player player;
	private boolean retryConnection = true;
	private GiftStatusUpdateSignal giftChestUpdateSignal;
	private Death death;
	private Timer retryTimer;
	private int delayBeforeReconnect = 2;
	private AddTextLineSignal addTextLine;
	private AddSpeechBalloonSignal addSpeechBalloon;
	private UpdateGroundTileSignal updateGroundTileSignal;
	private UpdateGameObjectTileSignal updateGameObjectTileSignal;
	private HandleDeathSignal handleDeath;
	private ZombifySignal zombify;
	private SetGameFocusSignal setGameFocus;
	private UpdateBackpackTabSignal updateBackpackTab;
	private PetFeedResultSignal petFeedResult;
	private CloseDialogsSignal closeDialogs;
	private OpenDialogSignal openDialog;
	private ArenaDeathSignal arenaDeath;
	private ImminentArenaWaveSignal imminentWave;
	private QuestFetchCompleteSignal questFetchComplete;
	private QuestRedeemCompleteSignal questRedeemComplete;
	private KeyInfoResponseSignal keyInfoResponse;
	private ClaimDailyRewardResponseSignal claimDailyRewardResponse;
	private CurrentArenaRunModel currentArenaRun;
	private ClassesModel classesModel;
	private GameModel model;
	private UpdateActivePet updateActivePet;
	private PetsModel petsModel;
	private FriendModel friendModel;
	private CharactersMetricsTracker statsTracker;

	private long startTime = 0; // this is currently not set

	public GameServerConnectionConcrete(AGameSprite gs, Server server, int gameId, boolean createCharacter, int charId,
	                                    int keyTime, Byte[] key, String mapJSON, boolean isFromArena) {
		super();

		this.giftChestUpdateSignal = GiftStatusUpdateSignal.getInstance();
		this.addTextLine = AddTextLineSignal.getInstance();
		this.addSpeechBalloon = AddSpeechBalloonSignal.getInstance();
		this.updateGroundTileSignal = UpdateGroundTileSignal.getInstance();
		this.updateGameObjectTileSignal = UpdateGameObjectTileSignal.getInstance();
		this.petFeedResult = PetFeedResultSignal.getInstance();
		this.updateBackpackTab = UpdateBackpackTabSignal.getInstance();
		 /*this.updateActivePet = UpdateActivePet;
		 this.petsModel = PetsModel;
		 this.friendModel =  FriendModel*/
		this.closeDialogs = CloseDialogsSignal.getInstance();
		changeMapSignal = ChangeMapSignal.getInstance();
		this.openDialog = OpenDialogSignal.getInstance();
		this.arenaDeath = ArenaDeathSignal.getInstance();
		this.imminentWave = ImminentArenaWaveSignal.getInstance();
		this.questFetchComplete = QuestFetchCompleteSignal.getInstance();
		this.questRedeemComplete = QuestRedeemCompleteSignal.getInstance();
		this.keyInfoResponse = KeyInfoResponseSignal.getInstance();
		this.claimDailyRewardResponse = ClaimDailyRewardResponseSignal.getInstance();
		/*this.statsTracker =  CharactersMetricsTracker;*/
		this.handleDeath = HandleDeathSignal.getInstance();
		this.zombify = ZombifySignal.getInstance();
		this.setGameFocus = SetGameFocusSignal.getInstance();
		this.classesModel = ClassesModel.getInstance();
		serverConnection = SocketServer.getInstance();
		this.messages = MessageCenter.getInstance();
		this.model = GameModel.getInstance();
		this.currentArenaRun = CurrentArenaRunModel.getInstance();
		this.gs = gs;
		this.server = server;
		this.gameId = gameId;
		this.createCharacter = createCharacter;
		this.charId = charId;
		this.keyTime = keyTime;
		this.key = key;
		this.mapJSON = mapJSON;
		this.isFromArena = isFromArena;/*this.friendModel.setCurrentServer(server);
		 this.getPetUpdater();*/
		instance = this;
	}

	private static boolean isStatPotion(int param1) {
		return param1 == 2591 || param1 == 5465 || param1 == 9064
				|| (param1 == 2592 || param1 == 5466 || param1 == 9065)
				|| (param1 == 2593 || param1 == 5467 || param1 == 9066)
				|| (param1 == 2612 || param1 == 5468 || param1 == 9067)
				|| (param1 == 2613 || param1 == 5469 || param1 == 9068)
				|| (param1 == 2636 || param1 == 5470 || param1 == 9069)
				|| (param1 == 2793 || param1 == 5471 || param1 == 9070)
				|| (param1 == 2794 || param1 == 5472 || param1 == 9071)
				|| (param1 == 9724 || param1 == 9725 || param1 == 9726 || param1 == 9727 || param1 == 9728
				|| param1 == 9729 || param1 == 9730 || param1 == 9731);
	}

	//private function getPetUpdater()

	//public void disconnect()

	// removeServerConnectionListeners()

	public void connect() {
		this.addServerConnectionListeners();
		this.mapMessages();
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.name = Parameters.CLIENT_CHAT_NAME;
		chatMessage.text = TextKey.CHAT_CONNECTING_TO;
		String loc2 = server.name;
		if (loc2 == "{\"text\":\"server.vault\"}") {
			loc2 = "server.vault";
		}
		loc2 = LineBuilder.getLocalizedStringFromKey(loc2);
		chatMessage.tokens = {"serverName":loc2};
		this.addTextLine.dispatch(chatMessage);
		serverConnection.connect(server.address, server.port);
	}

	public void mapMessages() {
		MessageMap _loc1 = MessageCenter.getInstance();
		_loc1.map(CREATE).toMessage(Create.class);
		_loc1.map(PLAYERSHOOT).toMessage(PlayerShoot.class);
		_loc1.map(MOVE).toMessage(Move.class);
		_loc1.map(PLAYERTEXT).toMessage(PlayerText.class);
		_loc1.map(UPDATEACK).toMessage(Message.class);
		_loc1.map(INVSWAP).toMessage(InvSwap.class);
		_loc1.map(USEITEM).toMessage(UseItem.class);
		_loc1.map(HELLO).toMessage(Hello.class);
		_loc1.map(INVDROP).toMessage(InvDrop.class);
		_loc1.map(PONG).toMessage(Pong.class);
		_loc1.map(LOAD).toMessage(Load.class);
		_loc1.map(SETCONDITION).toMessage(SetCondition.class);
		_loc1.map(TELEPORT).toMessage(Teleport.class);
		_loc1.map(USEPORTAL).toMessage(UsePortal.class);
		_loc1.map(BUY).toMessage(Buy.class);
		_loc1.map(PLAYERHIT).toMessage(PlayerHit.class);
		_loc1.map(ENEMYHIT).toMessage(EnemyHit.class);
		_loc1.map(AOEACK).toMessage(AoeAck.class);
		_loc1.map(SHOOTACK).toMessage(ShootAck.class);
		_loc1.map(OTHERHIT).toMessage(OtherHit.class);
		_loc1.map(SQUAREHIT).toMessage(SquareHit.class);
		_loc1.map(GOTOACK).toMessage(GotoAck.class);
		_loc1.map(GROUNDDAMAGE).toMessage(GroundDamage.class);
		_loc1.map(CHOOSENAME).toMessage(ChooseName.class);
		_loc1.map(CREATEGUILD).toMessage(CreateGuild.class);
		_loc1.map(GUILDREMOVE).toMessage(GuildRemove.class);
		_loc1.map(GUILDINVITE).toMessage(GuildInvite.class);
		_loc1.map(REQUESTTRADE).toMessage(RequestTrade.class);
		_loc1.map(CHANGETRADE).toMessage(ChangeTrade.class);
		_loc1.map(ACCEPTTRADE).toMessage(AcceptTrade.class);
		_loc1.map(CANCELTRADE).toMessage(CancelTrade.class);
		_loc1.map(CHECKCREDITS).toMessage(CheckCredits.class);
		_loc1.map(ESCAPE).toMessage(Escape.class);
		_loc1.map(QUEST_ROOM_MSG).toMessage(GoToQuestRoom.class);
		_loc1.map(JOINGUILD).toMessage(JoinGuild.class);
		_loc1.map(CHANGEGUILDRANK).toMessage(ChangeGuildRank.class);
		_loc1.map(EDITACCOUNTLIST).toMessage(EditAccountList.class);
		_loc1.map(ACTIVE_PET_UPDATE_REQUEST).toMessage(ActivePetUpdateRequest.class);
		_loc1.map(PETUPGRADEREQUEST).toMessage(PetUpgradeRequest.class);
		_loc1.map(ENTER_ARENA).toMessage(EnterArena.class);
		_loc1.map(ACCEPT_ARENA_DEATH).toMessage(OutgoingMessage.class);
		_loc1.map(QUEST_FETCH_ASK).toMessage(OutgoingMessage.class);
		_loc1.map(QUEST_REDEEM).toMessage(QuestRedeem.class);
		_loc1.map(KEY_INFO_REQUEST).toMessage(KeyInfoRequest.class);
		_loc1.map(PET_CHANGE_FORM_MSG).toMessage(ReskinPet.class);
		_loc1.map(CLAIM_LOGIN_REWARD_MSG).toMessage(ClaimDailyRewardMessage.class);
		_loc1.map(FAILURE).toMessage(Failure.class).toMethod(this::onFailure);
		_loc1.map(CREATE_SUCCESS).toMessage(CreateSuccess.class).toMethod(this::onCreateSuccess);
		_loc1.map(SERVERPLAYERSHOOT).toMessage(ServerPlayerShoot.class).toMethod(this::onServerPlayerShoot);
		_loc1.map(DAMAGE).toMessage(Damage.class).toMethod(this::onDamage);
		_loc1.map(UPDATE).toMessage(Update.class).toMethod(this::onUpdate);
		_loc1.map(NOTIFICATION).toMessage(Notification.class).toMethod(this::onNotification);
		_loc1.map(GLOBAL_NOTIFICATION).toMessage(GlobalNotification.class).toMethod(this::onGlobalNotification);
		_loc1.map(NEWTICK).toMessage(NewTick.class).toMethod(this::onNewTick);
		_loc1.map(SHOWEFFECT).toMessage(ShowEffect.class).toMethod(this::onShowEffect);
		_loc1.map(GOTO).toMessage(Goto.class).toMethod(this::onGoto);
		_loc1.map(INVRESULT).toMessage(InvResult.class).toMethod(this::onInvResult);
		_loc1.map(RECONNECT).toMessage(Reconnect.class).toMethod(this::onReconnect);
		_loc1.map(PING).toMessage(Ping.class).toMethod(this::onPing);
		_loc1.map(MAPINFO).toMessage(MapInfo.class).toMethod(this::onMapInfo);
		//_loc1.map(PIC).toMessage(Pic.class).toMethod(this::onPic);
		_loc1.map(DEATH).toMessage(Death.class).toMethod(this::onDeath);
		_loc1.map(BUYRESULT).toMessage(BuyResult.class).toMethod(this::onBuyResult);
		_loc1.map(AOE).toMessage(Aoe.class).toMethod(this::onAoe);
		_loc1.map(ACCOUNTLIST).toMessage(AccountList.class).toMethod(this::onAccountList);
		_loc1.map(QUESTOBJID).toMessage(QuestObjId.class).toMethod(this::onQuestObjId);
		_loc1.map(NAMERESULT).toMessage(NameResult.class).toMethod(this::onNameResult);
		_loc1.map(GUILDRESULT).toMessage(GuildResult.class).toMethod(this::onGuildResult);
		_loc1.map(ALLYSHOOT).toMessage(AllyShoot.class).toMethod(this::onAllyShoot);
		_loc1.map(ENEMYSHOOT).toMessage(EnemyShoot.class).toMethod(this::onEnemyShoot);
		_loc1.map(TRADEREQUESTED).toMessage(TradeRequested.class).toMethod(this::onTradeRequested);
		_loc1.map(TRADESTART).toMessage(TradeStart.class).toMethod(this::onTradeStart);
		_loc1.map(TRADECHANGED).toMessage(TradeChanged.class).toMethod(this::onTradeChanged);
		_loc1.map(TRADEDONE).toMessage(TradeDone.class).toMethod(this::onTradeDone);
		_loc1.map(TRADEACCEPTED).toMessage(TradeAccepted.class).toMethod(this::onTradeAccepted);
		_loc1.map(CLIENTSTAT).toMessage(ClientStat.class).toMethod(this::onClientStat);
		_loc1.map(FILE).toMessage(File.class).toMethod(this::onFile);
		_loc1.map(INVITEDTOGUILD).toMessage(InvitedToGuild.class).toMethod(this::onInvitedToGuild);
		_loc1.map(PLAYSOUND).toMessage(PlaySound.class).toMethod(this::onPlaySound);
		_loc1.map(ACTIVEPETUPDATE).toMessage(ActivePet.class).toMethod(this::onActivePetUpdate);
		_loc1.map(NEW_ABILITY).toMessage(NewAbilityMessage.class).toMethod(this::onNewAbility);
		_loc1.map(PETYARDUPDATE).toMessage(PetYard.class).toMethod(this::onPetYardUpdate);
		_loc1.map(EVOLVE_PET).toMessage(EvolvedPetMessage.class).toMethod(this::onEvolvedPet);
		_loc1.map(DELETE_PET).toMessage(DeletePetMessage.class).toMethod(this::onDeletePet);
		_loc1.map(HATCH_PET).toMessage(HatchPetMessage.class).toMethod(this::onHatchPet);
		_loc1.map(IMMINENT_ARENA_WAVE).toMessage(ImminentArenaWave.class).toMethod(this::onImminentArenaWave);
		_loc1.map(ARENA_DEATH).toMessage(ArenaDeath.class).toMethod(this::onArenaDeath);
		_loc1.map(VERIFY_EMAIL).toMessage(VerifyEmail.class).toMethod(this::onVerifyEmail);
		_loc1.map(RESKIN_UNLOCK).toMessage(ReskinUnlock.class).toMethod(this::onReskinUnlock);
		_loc1.map(PASSWORD_PROMPT).toMessage(PasswordPrompt.class).toMethod(this::onPasswordPrompt);
		_loc1.map(QUEST_FETCH_RESPONSE).toMessage(QuestFetchResponse.class).toMethod(this::onQuestFetchResponse);
		_loc1.map(QUEST_REDEEM_RESPONSE).toMessage(QuestRedeemResponse.class).toMethod(this::onQuestRedeemResponse);
		_loc1.map(KEY_INFO_RESPONSE).toMessage(KeyInfoResponse.class).toMethod(this::onKeyInfoResponse);
		_loc1.map(LOGIN_REWARD_MSG).toMessage(ClaimDailyRewardResponse.class).toMethod(this::onLoginRewardResponse);
	}

	private void onHatchPet(HatchPetMessage param1) {

	}

	private void onDeletePet(DeletePetMessage param1) {

	}

	private void onNewAbility(NewAbilityMessage param1) {

	}

	private void onPetYardUpdate(PetYard param1) {

	}

	private void onEvolvedPet(EvolvedPetMessage param1) {

	}

	private void onActivePetUpdate(ActivePet param1) {

	}

	private void encryptConnection() {
		//serverConnection.setOutgoingCipher(311f80691451c71d09a13a2a6e);
		//serverConnection.setIncomingCipher(72c5583cafb6818995cdd74b80);
	}

	/**
	 * This method uses the Java 7 way of getting a random int between ranges.
	 * It serves the same purpose as 'AS3's Random.getNextIntInRange()...
	 */
	@Override
	public int getNextDamage(int param1, int param2) {
		return ThreadLocalRandom.current().nextInt(param1, param2 + 1);
	}

	private void create() {
		CharacterClass selectedClass = this.classesModel.getSelected();
		Create create = (Create) this.messages.require(CREATE);
		create.classType = selectedClass.id;
		create.skinType = selectedClass.skins.getSelectedSkin().id;
		serverConnection.sendMessage(create);
	}

	private void load() {
		Load load = (Load) this.messages.require(LOAD);
		load.charId = charId;
		load.isFromArena = isFromArena;
		serverConnection.sendMessage(load);

		/**if (isFromArena) {
		 this.openDialog.dispatch(new BattleSummaryDialog());
		 }*/
	}

	public void playerShoot(int time, Projectile proj) {
		PlayerShoot playerShoot = (PlayerShoot) this.messages.require(PLAYERSHOOT);
		playerShoot.time = time;
		playerShoot.bulletId = proj.bulletId;
		playerShoot.containerType = proj.containerType;
		playerShoot.startingPos.x = proj.x;
		playerShoot.startingPos.y = proj.y;
		playerShoot.angle = proj.angle;
		this.serverConnection.sendMessage(playerShoot);
	}

	public void playerHit(int bulletId, int objectId) {
		PlayerHit playerHit = (PlayerHit) this.messages.require(PLAYERHIT);
		playerHit.bulletId = bulletId;
		playerHit.objectId = objectId;
		this.serverConnection.sendMessage(playerHit);
	}

	public void enemyHit(int time, int bulletId, int targetId, boolean kill) {
		EnemyHit enemyHit = (EnemyHit) this.messages.require(ENEMYHIT);
		enemyHit.time = time;
		enemyHit.bulletId = bulletId;
		enemyHit.targetId = targetId;
		enemyHit.kill = kill;
		this.serverConnection.sendMessage(enemyHit);
	}

	public void otherHit(int time, int bulletId, int objectId, int targetId) {
		OtherHit otherHit = (OtherHit) this.messages.require(OTHERHIT);
		otherHit.time = time;
		otherHit.bulletId = bulletId;
		otherHit.objectId = objectId;
		otherHit.targetId = targetId;
		this.serverConnection.sendMessage(otherHit);
	}

	public void squareHit(int time, int bulletId, int objectId) {
		SquareHit squareHit = (SquareHit) this.messages.require(SQUAREHIT);
		squareHit.time = time;
		squareHit.bulletId = bulletId;
		squareHit.objectId = objectId;
		this.serverConnection.sendMessage(squareHit);
	}

	public void aoeAck(int time, float x, float y) {
		AoeAck aoeAck = (AoeAck) this.messages.require(AOEACK);
		aoeAck.time = time;
		aoeAck.position.x = x;
		aoeAck.position.y = y;
		this.serverConnection.sendMessage(aoeAck);
	}

	public void groundDamage(int time, float x, float y) {
		GroundDamage groundDamage = (GroundDamage) this.messages.require(GROUNDDAMAGE);
		groundDamage.time = time;
		groundDamage.position.x = x;
		groundDamage.position.y = y;
		this.serverConnection.sendMessage(groundDamage);
	}

	public void shootAck(int time) {
		ShootAck shootAck = (ShootAck) this.messages.require(SHOOTACK);
		shootAck.time = time;
		this.serverConnection.sendMessage(shootAck);
	}

	public void playerText(String textStr) {
		PlayerText playerTextMessage = (PlayerText) this.messages.require(PLAYERTEXT);
		playerTextMessage.text = textStr;
		this.serverConnection.sendMessage(playerTextMessage);
	}

	public boolean invSwap(Player player, GameObject sourceObj, int slotId1, int itemId, GameObject targetObj,
	                       int slotId2, int objectType2) {
		if (this.gs == null) {
			return false;
		}
		InvSwap invSwap = (InvSwap) this.messages.require(INVSWAP);
		invSwap.time = this.gs.lastUpdate;
		invSwap.position.x = player.x;
		invSwap.position.y = player.y;
		invSwap.slotObject1.objectId = sourceObj.objectId;
		invSwap.slotObject1.slotId = slotId1;
		invSwap.slotObject1.objectType = itemId;
		invSwap.slotObject2.objectId = targetObj.objectId;
		invSwap.slotObject2.slotId = slotId2;
		invSwap.slotObject2.objectType = objectType2;
		this.serverConnection.sendMessage(invSwap);
		int tempType = sourceObj.equipment[slotId1];
		sourceObj.equipment[slotId1] = targetObj.equipment[slotId2];
		targetObj.equipment[slotId2] = tempType;
		SoundEffectLibrary.play("inventory_move_item");
		return true;
	}

	@Override
	public boolean invSwapPotion(Player player, GameObject sourceObj, int slotId1, int itemId, GameObject targetObj,
	                             int slotId2, int objectType2) {
		if (this.gs == null) {
			return false;
		}
		InvSwap invSwap = (InvSwap) this.messages.require(INVSWAP);
		invSwap.time = this.gs.lastUpdate;
		invSwap.position.x = player.x;
		invSwap.position.y = player.y;
		invSwap.slotObject1.objectId = sourceObj.objectId;
		invSwap.slotObject1.slotId = slotId1;
		invSwap.slotObject1.objectType = itemId;
		invSwap.slotObject2.objectId = targetObj.objectId;
		invSwap.slotObject2.slotId = slotId2;
		invSwap.slotObject2.objectType = objectType2;
		sourceObj.equipment[slotId1] = ItemConstants.NO_ITEM;
		if (itemId == PotionInventoryModel.HEALTH_POTION_ID) {
			player.healthPotionCount++;
		} else if (itemId == PotionInventoryModel.MAGIC_POTION_ID) {
			player.magicPotionCount++;
		}
		this.serverConnection.sendMessage(invSwap);
		SoundEffectLibrary.play("inventory_move_item");
		return true;
	}

	@Override
	public boolean invSwapRaw(Player player, int objectId1, int slotId1, int objectType1, int objectId2, int slotId2,
	                          int objectType2) {
		if (this.gs == null) {
			return false;
		}
		InvSwap loc8 = (InvSwap) this.messages.require(INVSWAP);
		loc8.time = gs.lastUpdate;
		loc8.position.x = player.x;
		loc8.position.y = player.y;
		loc8.slotObject1.objectId = objectId1;
		loc8.slotObject1.slotId = slotId1;
		loc8.slotObject1.objectType = objectType1;
		loc8.slotObject2.objectId = objectId2;
		loc8.slotObject2.slotId = slotId2;
		loc8.slotObject2.objectType = objectType2;
		//this.addTextLine.dispatch(ChatMessage.make("",  "INVSWAP;
		serverConnection.sendMessage(loc8);
		SoundEffectLibrary.play("inventory_move_item");
		return true;
	}

	public void invDrop(GameObject object, int slotId, int objectType) {
		InvDrop invDrop = (InvDrop) this.messages.require(INVDROP);
		invDrop.slotObject.objectId = object.objectId;
		invDrop.slotObject.slotId = slotId;
		invDrop.slotObject.objectType = objectType;
		this.serverConnection.sendMessage(invDrop);
		if (slotId != PotionInventoryModel.HEALTH_POTION_SLOT && slotId != PotionInventoryModel.MAGIC_POTION_SLOT) {
			object.equipment[slotId] = ItemConstants.NO_ITEM;
		}
	}

	public void useItem(int time, int objectId, int slotId, int objectType, float posX, float posY, int useType)

	{
		UseItem useItemMess = (UseItem) this.messages.require(USEITEM);
		useItemMess.time = time;
		useItemMess.slotObject.objectId = objectId;
		useItemMess.slotObject.slotId = slotId;
		useItemMess.slotObject.objectType = objectType;
		useItemMess.itemUsePos.x = posX;
		useItemMess.itemUsePos.y = posY;
		useItemMess.useType = useType;
		this.serverConnection.sendMessage(useItemMess);
	}

	public boolean useItem_new(GameObject itemOwner, int slotId)

	{
		int itemId = itemOwner.equipment[slotId];
		XML objectXML = ObjectLibrary.xmlLibrary.get(itemId);
		if ((objectXML != null) && !itemOwner.isPaused()
				&& (objectXML.hasOwnProperty("Consumable") || objectXML.hasOwnProperty("InvUse"))) {
			this.applyUseItem(itemOwner, slotId, itemId, objectXML);
			SoundEffectLibrary.play("use_potion");
			return true;
		}
		SoundEffectLibrary.play("error");
		return false;
	}

	private void applyUseItem(GameObject owner, int slotId, int objectType, XML itemData) {
		UseItem useItemMess = (UseItem) this.messages.require(USEITEM);
		useItemMess.time = getTimer();
		useItemMess.slotObject.objectId = owner.objectId;
		useItemMess.slotObject.slotId = slotId;
		useItemMess.slotObject.objectType = objectType;
		useItemMess.itemUsePos.x = 0;
		useItemMess.itemUsePos.y = 0;
		this.serverConnection.sendMessage(useItemMess);
		if (itemData.hasOwnProperty("Consumable")) {
			owner.equipment[slotId] = -1;
		}
	}

	public void setCondition(int conditionEffect, float conditionDuration)

	{
		SetCondition setCondition = this.messages.require(SETCONDITION) as SetCondition;
		setCondition.conditionEffect = conditionEffect;
		setCondition.conditionDuration = conditionDuration;
		this.serverConnection.sendMessage(setCondition);
	}

	public void move(int tickId, Player player) {
		int len = 0;
		int i = 0;
		float x = -1;
		float y = -1;
		if (player != null && !player.isPaused()) {
			x = player.x;
			y = player.y;
		}
		Move move = (Move) this.messages.require(MOVE);
		move.tickId = tickId;
		move.time = this.gs.lastUpdate;
		move.newPosition.x = x;
		move.newPosition.y = y;
		int lastMove = this.gs.moveRecords.lastClearTime;
		move.records.clear();
		if (lastMove >= 0 && move.time - lastMove > 125) {
			len = Math.min(10, this.gs.moveRecords.records.size());
			for (i = 0; i < len; i++) {
				if (this.gs.moveRecords.records.get(i).time >= move.time - 25) {
					break;
				}
				move.records.add(this.gs.moveRecords.records.get(i));
			}
		}
		this.gs.moveRecords.clear(move.time);
		this.serverConnection.sendMessage(move);
		player && player.onMove();
	}

	public void teleport(int objectId) {
		Teleport teleport = (Teleport) this.messages.require(TELEPORT);
		teleport.objectId = objectId;
		this.serverConnection.sendMessage(teleport);
	}

	public void usePortal(int objectId) {
		UsePortal usePortalMess = (UsePortal) this.messages.require(USEPORTAL);
		usePortalMess.objectId = objectId;
		this.serverConnection.sendMessage(usePortalMess);
	}

	public void buy(int sellableObjectId, int currencyType) {
		if (this.outstandingBuy != null) {
			return;
		}
		SellableObject sObj = (SellableObject) this.gs.map.goDict.get(sellableObjectId);
		if (sObj == null) {
			return;
		}
		boolean converted = false;
		if (sObj.currency == Currency.GOLD) {
			converted = this.gs.model.getConverted() || this.player.credits > 100 || sObj.price > this.player.credits;
		}
		this.outstandingBuy = new OutstandingBuy(sObj.soldObjectInternalName(), sObj.price, sObj.currency, converted);
		Buy buyMesssage = (Buy) this.messages.require(BUY);
		buyMesssage.objectId = sellableObjectId;
		this.serverConnection.sendMessage(buyMesssage);
	}

	public void gotoAck(int time) {
		GotoAck gotoAck = (GotoAck) this.messages.require(GOTOACK);
		gotoAck.time = time;
		this.serverConnection.sendMessage(gotoAck);
	}

	public void editAccountList(int accountListId, boolean add, int objectId) {
		EditAccountList eal = (EditAccountList) this.messages.require(EDITACCOUNTLIST);
		eal.accountListId = accountListId;
		eal.add = add;
		eal.objectId = objectId;
		this.serverConnection.sendMessage(eal);
	}

	public void chooseName(String name) {
		ChooseName chooseName = (ChooseName) this.messages.require(CHOOSENAME);
		chooseName.name = name;
		this.serverConnection.sendMessage(chooseName);
	}

	public void createGuild(String name) {
		CreateGuild createGuild = (CreateGuild) this.messages.require(CREATEGUILD);
		createGuild.name = name;
		this.serverConnection.sendMessage(createGuild);
	}

	public void guildRemove(String name) {
		GuildRemove guildRemove = (GuildRemove) this.messages.require(GUILDREMOVE);
		guildRemove.name = name;
		this.serverConnection.sendMessage(guildRemove);
	}

	public void guildInvite(String name) {
		GuildInvite guildInvite = (GuildInvite) this.messages.require(GUILDINVITE);
		guildInvite.name = name;
		this.serverConnection.sendMessage(guildInvite);
	}

	public void requestTrade(String name) {
		RequestTrade requestTrade = (RequestTrade) this.messages.require(REQUESTTRADE);
		requestTrade.name = name;
		this.serverConnection.sendMessage(requestTrade);
	}

	public void changeTrade(boolean[] offer) {
		ChangeTrade changeTrade = (ChangeTrade) this.messages.require(CHANGETRADE);
		changeTrade.offer = offer;
		this.serverConnection.sendMessage(changeTrade);
	}

	public void acceptTrade(boolean[] myOffer, boolean[] yourOffer) {
		AcceptTrade acceptTrade = (AcceptTrade) this.messages.require(ACCEPTTRADE);
		acceptTrade.myOffer = myOffer;
		acceptTrade.yourOffer = yourOffer;
		this.serverConnection.sendMessage(acceptTrade);
	}

	public void cancelTrade() {
		this.serverConnection.sendMessage(this.messages.require(CANCELTRADE));
	}

	public void checkCredits() {
		this.serverConnection.sendMessage(this.messages.require(CHECKCREDITS));
	}

	public void escape() {
		if (this.playerId == -1) {
			return;
		}
		this.serverConnection.sendMessage(this.messages.require(ESCAPE));
	}

	public void joinGuild(String guildName) {
		JoinGuild joinGuild = (JoinGuild) this.messages.require(JOINGUILD);
		joinGuild.guildName = guildName;
		this.serverConnection.sendMessage(joinGuild);
	}

	public void changeGuildRank(String name, int rank) {
		ChangeGuildRank changeGuildRank = (ChangeGuildRank) this.messages.require(CHANGEGUILDRANK);
		changeGuildRank.name = name;
		changeGuildRank.guildRank = rank;
		this.serverConnection.sendMessage(changeGuildRank);
	}

	private String rsaEncrypt(String data) {
		RSAKey rsaKey = PEM.readRSAPublicKey(Parameters.RSA_PUBLIC_KEY);
		ByteArray byteArray = new ByteArray();
		byteArray.writeUTFBytes(data);
		ByteArray encryptedByteArray = new ByteArray();
		rsaKey.encrypt(byteArray, encryptedByteArray, byteArray.length);
		return Base64.encodeByteArray(encryptedByteArray);
	}

	private void onConnected() {
		Account account = StaticInjectorContext.getInjector().getInstance();
		this.addTextLine.dispatch(ChatMessage.make(Parameters.CLIENT_CHAT_NAME, "Connected!"));
		Hello hello = (Hello) this.messages.require(HELLO);
		hello.buildVersion = Parameters.BUILD_VERSION;
		hello.gameId = this.gameId;
		hello.guid = this.rsaEncrypt(account.getUserId());
		hello.password = this.rsaEncrypt(account.getPassword());
		hello.secret = this.rsaEncrypt(account.getSecret());
		hello.keyTime = this.keyTime;
		hello.key.length = 0;
		this.key != null && hello.key.writeBytes(this.key);
		hello.mapJSON = this.mapJSON == null ? "" : this.mapJSON;
		hello.entrytag = account.getEntryTag();
		hello.gameNet = account.gameNetwork();
		hello.gameNetUserId = account.gameNetworkUserId();
		hello.playPlatform = account.playPlatform();
		hello.platformToken = account.getPlatformToken();
		this.serverConnection.sendMessage(hello);
	}

	private void onCreateSuccess(CreateSuccess createSuccess)

	{
		this.playerId = createSuccess.objectId;
		this.charId = createSuccess.charId;
		this.gs.initialize();
		this.createCharacter = false;
	}

	private void onDamage(Damage damage)
	{
		int projId = 0;
		Map map = (Map) this.gs.map;
		Projectile proj = null;
		if (damage.objectId >= 0 && damage.bulletId > 0) {
			projId = Projectile.findObjId(damage.objectId, damage.bulletId);
			proj = (Projectile) map.boDict.get(projId);
			if (proj != null && !proj.projProps.multiHit) {
				map.removeObj(projId);
			}
		}
		GameObject target = map.goDict.get(damage.targetId);
		if (target != null) {
			target.damage(-1, damage.damageAmount, damage.effects, damage.kill, proj);
		}
	}

	private void onServerPlayerShoot(ServerPlayerShoot serverPlayerShoot) {
		boolean needsAck = serverPlayerShoot.ownerId == this.playerId;
		GameObject owner = this.gs.map.goDict.get(serverPlayerShoot.ownerId);
		if (owner == null || owner.dead) {
			if (needsAck) {
				this.shootAck(-1);
			}
			return;
		}
		Projectile proj = new Projectile();
		proj.reset(serverPlayerShoot.containerType, 0, serverPlayerShoot.ownerId, serverPlayerShoot.bulletId,
				serverPlayerShoot.angle, this.gs.lastUpdate);
		proj.setDamage(serverPlayerShoot.damage);
		this.gs.map.addObj(proj, serverPlayerShoot.startingPos.x, serverPlayerShoot.startingPos.y);
		if (needsAck) {
			this.shootAck(this.gs.lastUpdate);
		}
	}

	private void onAllyShoot(AllyShoot allyShoot) {
		GameObject owner = this.gs.map.goDict.get(allyShoot.ownerId);
		if (owner == null || owner.dead) {
			return;
		}
		Projectile proj = new Projectile();
		proj.reset(allyShoot.containerType, 0, allyShoot.ownerId, allyShoot.bulletId, allyShoot.angle,
				this.gs.lastUpdate);
		this.gs.map.addObj(proj, owner.x, owner.y);
		owner.setAttack(allyShoot.containerType, allyShoot.angle);
	}

	private void onEnemyShoot(EnemyShoot enemyShoot) {
		GameObject owner = this.gs.map.goDict.get(enemyShoot.ownerId);
		if (owner == null || owner.dead) {
			this.shootAck(-1);
			return;
		}
		for (int i = 0; i < enemyShoot.numShots; i++) {
			Projectile proj = new Projectile();
			float angle = enemyShoot.angle + enemyShoot.angleInc * i;
			proj.reset(owner.objectType, enemyShoot.bulletType, enemyShoot.ownerId, (enemyShoot.bulletId + i) % 256,
					angle, this.gs.lastUpdate);
			proj.setDamage(enemyShoot.damage);
			this.gs.map.addObj(proj, enemyShoot.startingPos.x, enemyShoot.startingPos.y);
		}
		this.shootAck(this.gs.lastUpdate);
		owner.setAttack(owner.objectType, enemyShoot.angle + enemyShoot.angleInc * ((enemyShoot.numShots - 1) / 2));
	}

	private void onTradeRequested(TradeRequested tradeRequested) {
		if (Parameters.data.showTradePopup) {
			this.gs.hudView.interactPanel.setOverride(new TradeRequestPanel(this.gs, tradeRequested.name));
		}
		this.addTextLine.dispatch(ChatMessage.make("", tradeRequested.name + " wants to "
				+ "trade with you.  Type \"/trade " + tradeRequested.name + "\" to trade."));

	}

	private void onTradeStart(TradeStart tradeStart) {
		this.gs.hudView.startTrade(this.gs, tradeStart);
	}

	private void onTradeChanged(TradeChanged tradeChanged) {
		this.gs.hudView.tradeChanged(tradeChanged);
	}

	private void onTradeDone(TradeDone tradeDone) {
		this.gs.hudView.tradeDone();
		this.addTextLine.dispatch(ChatMessage.make("", tradeDone.description));
	}

	private void onTradeAccepted(TradeAccepted tradeAccepted) {
		this.gs.hudView.tradeAccepted(tradeAccepted);
	}

	private void addObject(ObjectData obj) {
		Map map = (Map) this.gs.map;
		GameObject go = ObjectLibrary.getObjectFromType(obj.objectType);
		if (go == null) {
			return;
		}
		ObjectStatusData status = obj.status;
		go.setObjectId(status.objectId);
		map.addObj(go, status.pos.x, status.pos.y);
		if (go instanceof Player) {
			this.handleNewPlayer((Player) go, map);
		}
		this.processObjectStatus(status, 0, -1);
		if (go.props. static&&go.props.occupySquare && !go.props.noMiniMap){
			this.updateGameObjectTileSignal.dispatch(new UpdateGameObjectTileVO(go.x, go.y, go));
		}
	}

	private void handleNewPlayer(Player player, Map map)

	{
		this.setPlayerSkinTemplate(player, 0);
		if (player.objectId == this.playerId) {
			this.player = player;
			this.model.player = player;
			map.player = player;
			this.gs.setFocus(player);
			this.setGameFocus.dispatch(this.playerId.toString());
		}
	}

	private void onUpdate(Update update) {
		int i = 0;
		GroundTileData tile = null;
		Message updateAck = this.messages.require(UPDATEACK);
		this.serverConnection.sendMessage(updateAck);
		for (i = 0; i < update.tiles.length; i++) {
			tile = update.tiles[i];
			this.gs.map.setGroundTile(tile.x, tile.y, tile.type);
			this.updateGroundTileSignal.dispatch(new UpdateGroundTileVO(tile.x, tile.y, tile.type));
		}
		for (i = 0; i < update.newObjs.length; i++) {
			this.addObject(update.newObjs[i]);
		}
		for (i = 0; i < update.drops.length; i++) {
			this.gs.map.removeObj(update.drops[i]);
		}
	}

	private void onNotification(Notification notification) {
		QueuedStatusText text = null;
		GameObject go = this.gs.map.goDict.get(notification.objectId);
		if (go != null) {
			text = new QueuedStatusText(go, notification.message, notification.color, 2000);
			this.gs.map.mapOverlay.addQueuedText(text);
			if (go == this.player && notification.message.equals("Quest Complete!")) {
				this.gs.map.quest.completed();
			}
		}
	}

	private void onGlobalNotification(GlobalNotification notification) {
		switch (notification.text) {
			case "yellow":
				ShowKeySignal.getInstance().dispatch(Key.YELLOW);
				break;
			case "red":
				ShowKeySignal.getInstance().dispatch(Key.RED);
				break;
			case "green":
				ShowKeySignal.getInstance().dispatch(Key.GREEN);
				break;
			case "purple":
				ShowKeySignal.getInstance().dispatch(Key.PURPLE);
				break;
			case "showKeyUI":
				ShowKeyUISignal.getInstance().dispatch();
				break;
			case "giftChestOccupied":
				this.giftChestUpdateSignal.dispatch(GiftStatusUpdateSignal.HAS_GIFT);
				break;
			case "giftChestEmpty":
				this.giftChestUpdateSignal.dispatch(GiftStatusUpdateSignal.HAS_NO_GIFT);
				break;
			case "beginnersPackage":
		}
	}

	private void onNewTick(NewTick newTick) {
		this.move(newTick.tickId, this.player);
		for (ObjectStatusData objectStatus : newTick.statuses) {
			this.processObjectStatus(objectStatus, newTick.tickTime, newTick.tickId);
		}
		this.lastTickId = newTick.tickId;
	}

	private void onShowEffect(ShowEffect showEffect) {
		//System.out.println("Show effect : " + showEffect);
	}

	/**
	 * In Java goto is a reserved keyword
	 */
	private void onGoto(Goto gotoPacket) {
		this.gotoAck(this.gs.lastUpdate);
		GameObject go = this.gs.map.goDict.get(gotoPacket.objectId);
		if (go == null) {
			return;
		}
		go.onGoto(gotoPacket.pos.x, gotoPacket.pos.y, this.gs.lastUpdate);
	}

	private void updateGameObject(GameObject go, StatData[] stats, boolean isMyObject) {
		int index = 0;
		Player player = (Player) go;
		Merchant merchant = (Merchant) go;
		for (StatData stat : stats) {
			int value = stat.statValue;
			String strStatValue = stat.strStatValue;
			switch (stat.statType) {
				case StatData.MAX_HP_STAT:
					go.maxHP = value;
					continue;
				case StatData.HP_STAT:
					go.hp = value;
					continue;
				case StatData.SIZE_STAT:
					go.size = value;
					continue;
				case StatData.MAX_MP_STAT:
					player.maxMP = value;
					continue;
				case StatData.MP_STAT:
					player.mp = value;
					continue;
				case StatData.NEXT_LEVEL_EXP_STAT:
					player.nextLevelExp = value;
					continue;
				case StatData.EXP_STAT:
					player.exp = value;
					continue;
				case StatData.LEVEL_STAT:
					go.level = value;
					continue;
				case StatData.ATTACK_STAT:
					player.attack = value;
					continue;
				case StatData.DEFENSE_STAT:
					go.defense = value;
					continue;
				case StatData.SPEED_STAT:
					player.speed = value;
					continue;
				case StatData.DEXTERITY_STAT:
					player.dexterity = value;
					continue;
				case StatData.VITALITY_STAT:
					player.vitality = value;
					continue;
				case StatData.WISDOM_STAT:
					player.wisdom = value;
					continue;
				case StatData.CONDITION_STAT:
					go.condition[ConditionEffect.CE_FIRST_BATCH] = value;
					continue;
				case StatData.INVENTORY_0_STAT:
				case StatData.INVENTORY_1_STAT:
				case StatData.INVENTORY_2_STAT:
				case StatData.INVENTORY_3_STAT:
				case StatData.INVENTORY_4_STAT:
				case StatData.INVENTORY_5_STAT:
				case StatData.INVENTORY_6_STAT:
				case StatData.INVENTORY_7_STAT:
				case StatData.INVENTORY_8_STAT:
				case StatData.INVENTORY_9_STAT:
				case StatData.INVENTORY_10_STAT:
				case StatData.INVENTORY_11_STAT:
					go.equipment[stat.statType - StatData.INVENTORY_0_STAT] = value;
					continue;
				case StatData.NUM_STARS_STAT:
					player.numStars = value;
					continue;
				case StatData.NAME_STAT:
					if (go.name != stat.strStatValue) {
						go.name = stat.strStatValue;
						go.nameBitmapData = null;
					}
					continue;
				case StatData.TEX1_STAT:
					go.setTexture(value);
					continue;
				case StatData.TEX2_STAT:
					go.setAltTexture(value);
					continue;
				case StatData.MERCHANDISE_TYPE_STAT:
					merchant.setMerchandiseType(value);
					continue;
				case StatData.CREDITS_STAT:
					player.setCredits(value);
					continue;
				case StatData.MERCHANDISE_PRICE_STAT:
					(SellableObject) go.setPrice(value);
					continue;
				case StatData.ACTIVE_STAT:
					(Portal) go.active = value != 0;
					continue;
				case StatData.ACCOUNT_ID_STAT:
					player.accountId = stat.strStatValue;
					continue;
				case StatData.FAME_STAT:
					player.fame = value;
					continue;
				case StatData.MERCHANDISE_CURRENCY_STAT:
					(SellableObject) go.setCurrency(value);
					continue;
				case StatData.CONNECT_STAT:
					go.connectType = value;
					continue;
				case StatData.MERCHANDISE_COUNT_STAT:
					merchant.count = value;
					merchant.untilNextMessage = 0;
					continue;
				case StatData.MERCHANDISE_MINS_LEFT_STAT:
					merchant.minsLeft = value;
					merchant.untilNextMessage = 0;
					continue;
				case StatData.MERCHANDISE_DISCOUNT_STAT:
					merchant.discount = value;
					merchant.untilNextMessage = 0;
					continue;
				case StatData.MERCHANDISE_RANK_REQ_STAT:
					(SellableObject) go.setRankReq(value);
					continue;
				case StatData.MAX_HP_BOOST_STAT:
					player.maxHPBoost = value;
					continue;
				case StatData.MAX_MP_BOOST_STAT:
					player.maxMPBoost = value;
					continue;
				case StatData.ATTACK_BOOST_STAT:
					player.attackBoost = value;
					continue;
				case StatData.DEFENSE_BOOST_STAT:
					player.defenseBoost = value;
					continue;
				case StatData.SPEED_BOOST_STAT:
					player.speedBoost = value;
					continue;
				case StatData.VITALITY_BOOST_STAT:
					player.vitalityBoost = value;
					continue;
				case StatData.WISDOM_BOOST_STAT:
					player.wisdomBoost = value;
					continue;
				case StatData.DEXTERITY_BOOST_STAT:
					player.dexterityBoost = value;
					continue;
				case StatData.OWNER_ACCOUNT_ID_STAT:
					(Container) go.setOwnerId(value);
					continue;
				case StatData.RANK_REQUIRED_STAT:
					(NameChanger) go.setRankRequired(value);
					continue;
				case StatData.NAME_CHOSEN_STAT:
					player.nameChosen = value != 0;
					go.nameBitmapData = null;
					continue;
				case StatData.CURR_FAME_STAT:
					player.currFame = value;
					continue;
				case StatData.NEXT_CLASS_QUEST_FAME_STAT:
					player.nextClassQuestFame = value;
					continue;
				case StatData.LEGENDARY_RANK_STAT:
					player.legendaryRank = value;
					continue;
				case StatData.SINK_LEVEL_STAT:
					if (!isMyObject) {
						player.sinkLevel = value;
					}
					continue;
				case StatData.ALT_TEXTURE_STAT:
					go.setAltTexture(value);
					continue;
				case StatData.GUILD_NAME_STAT:
					player.setGuildName(stat.strStatValue);
					continue;
				case StatData.GUILD_RANK_STAT:
					player.guildRank = value;
					continue;
				case StatData.BREATH_STAT:
					player.breath = value;
					continue;
				case StatData.XP_BOOSTED_STAT:
					player.xpBoost = value;
					continue;
				case StatData.XP_TIMER_STAT:
					player.xpTimer = value * TO_MILLISECONDS;
					continue;
				case StatData.LD_TIMER_STAT:
					player.dropBoost = value * TO_MILLISECONDS;
					continue;
				case StatData.LT_TIMER_STAT:
					player.tierBoost = value * TO_MILLISECONDS;
					continue;
				case StatData.HEALTH_POTION_STACK_STAT:
					player.healthPotionCount = value;
					continue;
				case StatData.MAGIC_POTION_STACK_STAT:
					player.magicPotionCount = value;
					continue;
				case StatData.TEXTURE_STAT:
					player.skinId != value && this.setPlayerSkinTemplate(player, value);
					continue;
				case StatData.HASBACKPACK_STAT:
					(Player) go.hasBackpack = value;
					if (isMyObject) {
						this.updateBackpackTab.dispatch(value);
					}
					continue;
				case StatData.BACKPACK_0_STAT:
				case StatData.BACKPACK_1_STAT:
				case StatData.BACKPACK_2_STAT:
				case StatData.BACKPACK_3_STAT:
				case StatData.BACKPACK_4_STAT:
				case StatData.BACKPACK_5_STAT:
				case StatData.BACKPACK_6_STAT:
				case StatData.BACKPACK_7_STAT:
					index = stat.statType - StatData.BACKPACK_0_STAT + GeneralConstants.NUM_EQUIPMENT_SLOTS + GeneralConstants.NUM_INVENTORY_SLOTS;
					Player o = (Player) go;
					o.equipment[index] = value;
					continue;
				default:
					continue;
			}
		}
	}

	private void setPlayerSkinTemplate(Player player, int skinId) {
		Reskin message = (Reskin) this.messages.require(RESKIN);
		message.skinID = skinId;
		message.player = player;
		message.consume();
	}

	private void processObjectStatus(ObjectStatusData objectStatus, int tickTime, int tickId)

	{
		int oldLevel = 0;
		int oldExp = 0;
		Array newUnlocks = null;
		CharacterClass type = null;
		AbstractMap map = this.gs.map;
		GameObject go = map.goDict.get(objectStatus.objectId);
		if (go == null) {
			return;
		}
		boolean isMyObject = objectStatus.objectId == this.playerId;
		if (tickTime != 0 && !isMyObject) {
			go.onTickPos(objectStatus.pos.x, objectStatus.pos.y, tickTime, tickId);
		}
		Player player = (Player) go;
		if (player != null) {
			oldLevel = player.level;
			oldExp = player.exp;
		}
		this.updateGameObject(go, objectStatus.stats, isMyObject);
		if (player != null && oldLevel != -1) {
			if (player.level > oldLevel) {
				if (isMyObject) {
					newUnlocks = this.gs.model.getNewUnlocks(player.objectType, player.level);
					player.handleLevelUp(newUnlocks.length != 0);
					type = this.classesModel.getCharacterClass(player.objectType);
					if (type.getMaxLevelAchieved() < player.level) {
						type.setMaxLevelAchieved(player.level);
					}
				} else {
					player.levelUpEffect("Level Up!");
				}
			} else if (player.exp > oldExp) {
				player.handleExpUp(player.exp - oldExp);
			}
		}
	}

	/**
	 * Important : This method is not in the new version of the client
	 * <p>
	 * It's now in ChatConfig
	 */
	private void onText(Text text) {
		GameObject go = null;
		List<int> colors = null;
		AddSpeechBalloonVO speechBalloonvo = null;
		String textString = text.text;
		if (text.cleanText.length > 0 && text.objectId != this.playerId && Parameters.data.filterLanguage) {
			textString = text.cleanText;
		}
		if (text.objectId >= 0) {
			go = this.gs.map.goDict.get(text.objectId);
			if (go != null) {
				colors = NORMAL_SPEECH_COLORS;
				if (go.props.isEnemy) {
					colors = ENEMY_SPEECH_COLORS;
				} else if (text.recipient.equals(Parameters.GUILD_CHAT_NAME)) {
					colors = GUILD_SPEECH_COLORS;
				} else if (!text.recipient.equals("")) {
					colors = TELL_SPEECH_COLORS;
				}
				speechBalloonvo = new AddSpeechBalloonVO(go, textString, colors[0], 1, colors[1], 1, colors[2], text.bubbleTime, false, true);
				this.addSpeechBalloon.dispatch(speechBalloonvo);
			}
		}
		this.addTextLine.dispatch(ChatMessage.make(text.name, textString, text.objectId, text.numStars, text.recipient));
	}

	private void onInvResult(InvResult invResult) {
		if (invResult.result != 0) {
			this.handleInvFailure();
		}
	}

	private void handleInvFailure() {
		SoundEffectLibrary.play("error");
		this.gs.hudView.interactPanel.redraw();
	}

	private void onReconnect(Reconnect reconnect) {
		this.disconnect();
		Server server = new Server().setName(reconnect.name).setAddress(reconnect.host.equals("") ? reconnect.host : this.server.address).setPort(!reconnect.host.equals("") ?
				reconnect.port : this.server.port);
		int gameID = reconnect.gameId;
		boolean createChar = this.createCharacter;
		int charId = this.charId;
		int keyTime = reconnect.keyTime;
		byte[] key = reconnect.key;
		boolean isFromArena = reconnect.isFromArena;
		ReconnectEvent reconnectEvent = new ReconnectEvent(server, gameID, createChar, charId, keyTime, key, isFromArena);
		this.gs.dispatchEvent(reconnectEvent);
	}

	private void onPing(Ping ping) {
		Pong pong = (Pong) this.messages.require(PONG);
		pong.serial = ping.serial;
		pong.time = getTimer();
		this.serverConnection.sendMessage(pong);
	}

	private void parseXML(String xmlString) {
		XML extraXML = new XML(xmlString);
		GroundLibrary.parseFromXML(extraXML);
		ObjectLibrary.parseFromXML(extraXML);
		ObjectLibrary.parseFromXML(extraXML);
	}

	private void onMapInfo(MapInfo mapInfo) {
		for (String clientXMLString : mapInfo.clientXML) {
			this.parseXML(clientXMLString);
		}
		for (String extraXMLString : mapInfo.extraXML) {
			this.parseXML(extraXMLString);
		}
		this.gs.applyMapInfo(mapInfo);
		this.rand = new Random(mapInfo.fp);
		if (this.createCharacter) {
			this.create();
		} else {
			this.load();
		}
	}

	//private void onPic(Pic pic) { this.gs.addChild(new PicView(pic.bitmapData)); }

	private void onDeath(Death death) {
		this.death = death;
		BitmapData data = new BitmapData(this.gs.stage.stageWidth, this.gs.stage.stageHeight);
		data.draw(this.gs);
		death.background = data;
		if (!this.gs.isEditor) {
			this.handleDeath.dispatch(death);
		}
	}

	private void onBuyResult(BuyResult buyResult) {
		if (buyResult.result == BuyResult.SUCCESS_BRID) {
			if (this.outstandingBuy != null) {
				this.outstandingBuy.record();
			}
		}
		this.outstandingBuy = null;
		switch (buyResult.result) {
			case BuyResult.NOT_ENOUGH_GOLD_BRID:
				OpenDialogSignal.getInstance().dispatch(new NotEnoughGoldDialog());
				break;
			case BuyResult.NOT_ENOUGH_FAME_BRID:
				OpenDialogSignal.getInstance().dispatch(new NotEnoughFameDialog());
				break;
			default:
				this.addTextLine
						.dispatch(ChatMessage.make(buyResult.result == BuyResult.SUCCESS_BRID ? Parameters.SERVER_CHAT_NAME
								: Parameters.ERROR_CHAT_NAME, buyResult.resultString));
		}
	}

	private void onAccountList(AccountList accountList) {
		/**if (accountList.accountListId == 0) {
		 this.gs.map.party.setStars(accountList);
		 }
		 if (accountList.accountListId == 1) {
		 this.gs.map.party.setIgnores(accountList);
		 }*/
	}

	private void onQuestObjId(QuestObjId questObjId) {
		this.gs.map.quest.setObject(questObjId.objectId);
	}

	private void onAoe(Aoe aoe) {

		this.aoeAck(this.gs.lastUpdate, this.player.x, this.player.y);
	}

	private void onNameResult(NameResult nameResult) {
		this.gs.dispatchEvent(new NameResultEvent(nameResult));
	}

	private void onGuildResult(GuildResult guildResult) {
		this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, guildResult.errorText));
		this.gs.dispatchEvent(new GuildResultEvent(guildResult.success, guildResult.errorText));
	}

	private void onClientStat(ClientStat clientStat) {
		Account account = StaticInjectorContext.getInjector().getInstance(Account);
		account.reportIntStat(clientStat.name, clientStat.value);
	}

	private void onFile(File file) {
		new FileReference().save(file.file, file.filename);
	}

	private void onInvitedToGuild(InvitedToGuild invitedToGuild) {
		if (Parameters.data.showGuildInvitePopup) {
			this.gs.hudView.interactPanel
					.setOverride(new GuildInvitePanel(this.gs, invitedToGuild.name, invitedToGuild.guildName));
		}
		this.addTextLine.dispatch(ChatMessage.make("",
				"You have been invited by " + invitedToGuild.name + " to join the guild " + invitedToGuild.guildName
						+ ".\n  If you wish to join type \"/join " + invitedToGuild.guildName + "\""));
	}

	private void onPlaySound(PlaySound playSound) {
		GameObject obj = this.gs.map.goDict.get(playSound.ownerId);
		obj && obj.playSound(playSound.soundId);
	}

	private void onClosed() {
		if (this.playerId != -1) {
			this.gs.closed.dispatch();
		} else if (this.retryConnection) {
			if (this.delayBeforeReconect < 10) {
				this.retry(this.delayBeforeReconect++);
				this.addTextLine
						.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, "Connection failed!  Retrying..."));
			} else {
				this.gs.closed.dispatch();
			}
		}
	}

	private void retry(int time) {
		this.retryTimer = new Timer(time * 1000);
		this.retryTimer.addEventListener(TimerEvent.TIMER_COMPLETE, this::onRetryTimer);
		this.retryTimer.start();
	}

	private void onRetryTimer() {
		this.serverConnection.connect(this.server.address, this.server.port);
	}

	private void onError(String error) {
		this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, error));
	}

	private void onFailure(Failure event) {
		switch (event.errorId) {
			case Failure.INCORRECT_VERSION:
				this.handleIncorrectVersionFailure(event);
				break;
			case Failure.BAD_KEY:
				this.handleBadKeyFailure(event);
				break;
			case Failure.INVALID_TELEPORT_TARGET:
				this.handleInvalidTeleportTarget(event);
				break;
			default:
				this.handleDefaultFailure(event);
		}
	}

	private void handleInvalidTeleportTarget(Failure event) {
		this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, event.errorDescription));
		this.player.nextTeleportAt = 0;
	}

	private void handleBadKeyFailure(Failure event) {
		this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, event.errorDescription));
		this.retryConnection = false;
		this.gs.closed.dispatch();
	}

	private void handleIncorrectVersionFailure(Failure event) {
		System.out.println("Client version " + Parameters.BUILD_VERSION + " Server version: " + event.errorDescription
				+ "Client Update Needed.");

		/*Dialog dialog = new Dialog("Client version: " + Parameters.BUILD_VERSION + "\nServer version: " + event.errorDescription,
				"Client Update Needed", "Ok", null, "/clientUpdate");
		dialog.addEventListener(Dialog.BUTTON1_EVENT, this.onDoClientUpdate);
		this.gs.stage.addChild(dialog);
		this.retryConnection = false;**/
	}

	private void handleDefaultFailure(Failure event) {
		this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, event.errorDescription));
	}

	private void onDoClientUpdate(Event event) {
		Dialog dialog = (Dialog) event.currentTarget;
		dialog.parent.removeChild(dialog);
		this.gs.closed.dispatch();
	}

	public int getTimer() {
		return (int) (System.currentTimeMillis() - startTime);

	}
}

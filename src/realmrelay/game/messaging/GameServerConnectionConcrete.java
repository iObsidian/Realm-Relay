package realmrelay.game.messaging;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import realmrelay.game.api.MessageProvider;
import realmrelay.game.classes.model.CharacterClass;
import realmrelay.game.dailyQuests.QuestFetchResponse;
import realmrelay.game.dailyQuests.QuestRedeemResponse;
import realmrelay.game.game.AGameSprite;
import realmrelay.game.messaging.impl.ActivePet;
import realmrelay.game.messaging.impl.HatchPetMessage;
import realmrelay.game.messaging.impl.PetUpgradeRequest;
import realmrelay.game.messaging.impl.PetYard;
import realmrelay.game.messaging.impl.ReskinPet;
import realmrelay.game.messaging.incoming.AccountList;
import realmrelay.game.messaging.incoming.AllyShoot;
import realmrelay.game.messaging.incoming.Aoe;
import realmrelay.game.messaging.incoming.BuyResult;
import realmrelay.game.messaging.incoming.ClaimDailyRewardResponse;
import realmrelay.game.messaging.incoming.ClientStat;
import realmrelay.game.messaging.incoming.CreateSuccess;
import realmrelay.game.messaging.incoming.Damage;
import realmrelay.game.messaging.incoming.Death;
import realmrelay.game.messaging.incoming.EnemyShoot;
import realmrelay.game.messaging.incoming.EvolvedPetMessage;
import realmrelay.game.messaging.incoming.Failure;
import realmrelay.game.messaging.incoming.File;
import realmrelay.game.messaging.incoming.GlobalNotification;
import realmrelay.game.messaging.incoming.Goto;
import realmrelay.game.messaging.incoming.GuildResult;
import realmrelay.game.messaging.incoming.InvResult;
import realmrelay.game.messaging.incoming.InvitedToGuild;
import realmrelay.game.messaging.incoming.KeyInfoResponse;
import realmrelay.game.messaging.incoming.MapInfo;
import realmrelay.game.messaging.incoming.NameResult;
import realmrelay.game.messaging.incoming.NewAbilityMessage;
import realmrelay.game.messaging.incoming.NewTick;
import realmrelay.game.messaging.incoming.Notification;
import realmrelay.game.messaging.incoming.PasswordPrompt;
import realmrelay.game.messaging.incoming.Ping;
import realmrelay.game.messaging.incoming.PlaySound;
import realmrelay.game.messaging.incoming.QuestObjId;
import realmrelay.game.messaging.incoming.Reconnect;
import realmrelay.game.messaging.incoming.ReskinUnlock;
import realmrelay.game.messaging.incoming.ServerPlayerShoot;
import realmrelay.game.messaging.incoming.ShowEffect;
import realmrelay.game.messaging.incoming.TradeAccepted;
import realmrelay.game.messaging.incoming.TradeChanged;
import realmrelay.game.messaging.incoming.TradeDone;
import realmrelay.game.messaging.incoming.TradeRequested;
import realmrelay.game.messaging.incoming.TradeStart;
import realmrelay.game.messaging.incoming.Update;
import realmrelay.game.messaging.incoming.VerifyEmail;
import realmrelay.game.messaging.incoming.arena.ArenaDeath;
import realmrelay.game.messaging.incoming.arena.ImminentArenaWave;
import realmrelay.game.messaging.incoming.pets.DeletePetMessage;
import realmrelay.game.messaging.outgoing.AcceptTrade;
import realmrelay.game.messaging.outgoing.ActivePetUpdateRequest;
import realmrelay.game.messaging.outgoing.AoeAck;
import realmrelay.game.messaging.outgoing.Buy;
import realmrelay.game.messaging.outgoing.CancelTrade;
import realmrelay.game.messaging.outgoing.ChangeGuildRank;
import realmrelay.game.messaging.outgoing.ChangeTrade;
import realmrelay.game.messaging.outgoing.CheckCredits;
import realmrelay.game.messaging.outgoing.ChooseName;
import realmrelay.game.messaging.outgoing.ClaimDailyRewardMessage;
import realmrelay.game.messaging.outgoing.Create;
import realmrelay.game.messaging.outgoing.CreateGuild;
import realmrelay.game.messaging.outgoing.EditAccountList;
import realmrelay.game.messaging.outgoing.EnemyHit;
import realmrelay.game.messaging.outgoing.Escape;
import realmrelay.game.messaging.outgoing.GoToQuestRoom;
import realmrelay.game.messaging.outgoing.GotoAck;
import realmrelay.game.messaging.outgoing.GroundDamage;
import realmrelay.game.messaging.outgoing.GuildInvite;
import realmrelay.game.messaging.outgoing.GuildRemove;
import realmrelay.game.messaging.outgoing.Hello;
import realmrelay.game.messaging.outgoing.InvDrop;
import realmrelay.game.messaging.outgoing.InvSwap;
import realmrelay.game.messaging.outgoing.JoinGuild;
import realmrelay.game.messaging.outgoing.KeyInfoRequest;
import realmrelay.game.messaging.outgoing.Load;
import realmrelay.game.messaging.outgoing.Move;
import realmrelay.game.messaging.outgoing.OtherHit;
import realmrelay.game.messaging.outgoing.OutgoingMessage;
import realmrelay.game.messaging.outgoing.PlayerHit;
import realmrelay.game.messaging.outgoing.PlayerShoot;
import realmrelay.game.messaging.outgoing.PlayerText;
import realmrelay.game.messaging.outgoing.Pong;
import realmrelay.game.messaging.outgoing.RequestTrade;
import realmrelay.game.messaging.outgoing.SetCondition;
import realmrelay.game.messaging.outgoing.ShootAck;
import realmrelay.game.messaging.outgoing.SquareHit;
import realmrelay.game.messaging.outgoing.Teleport;
import realmrelay.game.messaging.outgoing.UseItem;
import realmrelay.game.messaging.outgoing.UsePortal;
import realmrelay.game.messaging.outgoing.arena.EnterArena;
import realmrelay.game.messaging.outgoing.arena.QuestRedeem;
import realmrelay.game.net.Server;
import realmrelay.game.net.api.MessageMap;
import realmrelay.game.net.impl.Message;
import realmrelay.game.net.impl.MessageCenter;
import realmrelay.game.objects.Player;

public class GameServerConnectionConcrete extends GameServerConnection {

	private static final int TO_MILLISECONDS = 1000;
	//private PetUpdater petUpdater;
	private MessageProvider messages;
	private int playerId = -1;
	private Player player;
	private boolean retryConnection = true;
	//private GiftStatusUpdateSignal giftChestUpdateSignal;
	private Death death;
	/*private Timer retryTimer;
	private int delayBeforeReconnect = 2;
	private AddTextLineSignal addTextLine;
	private AddSpeechBalloonSignal addSpeechBalloon;
	private UpdateGroundTileSignal updateGroundTileSignal;
	private UpdateGameObjectTileSignal updateGameObjectTileSignal;
	private ILogger logger;
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
	private Injector injector;
	private GameModel model;
	private UpdateActivePet updateActivePet;
	private PetsModel petsModel;
	private FriendModel friendModel;
	private CharactersMetricsTracker statsTracker;**/

	//public function GameServerConnection(gs:GameSprite, server:Server, gameId:int, createCharacter:Boolean, charId:int, keyTime:int, key:ByteArray, mapJSON:String)

	public GameServerConnectionConcrete(AGameSprite gs, Server server, int gameId, boolean createCharacter, int charId,
			int keyTime, Byte[] key, String mapJSON, boolean isFromArena) {
		super();
		/**this.injector = StaticInjectorContext.getInjector();
		this.giftChestUpdateSignal = this.injector.getInstance(GiftStatusUpdateSignal);
		this.addTextLine = this.injector.getInstance(AddTextLineSignal);
		this.addSpeechBalloon = this.injector.getInstance(AddSpeechBalloonSignal);
		this.updateGroundTileSignal = this.injector.getInstance(UpdateGroundTileSignal);
		this.updateGameObjectTileSignal = this.injector.getInstance(UpdateGameObjectTileSignal);
		this.petFeedResult = this.injector.getInstance(PetFeedResultSignal);
		this.updateBackpackTab = StaticInjectorContext.getInjector().getInstance(UpdateBackpackTabSignal);
		this.updateActivePet = this.injector.getInstance(UpdateActivePet);
		this.petsModel = this.injector.getInstance(PetsModel);
		this.friendModel = this.injector.getInstance(FriendModel);
		this.closeDialogs = this.injector.getInstance(CloseDialogsSignal);
		changeMapSignal = this.injector.getInstance(ChangeMapSignal);
		this.openDialog = this.injector.getInstance(OpenDialogSignal);
		this.arenaDeath = this.injector.getInstance(ArenaDeathSignal);
		this.imminentWave = this.injector.getInstance(ImminentArenaWaveSignal);
		this.questFetchComplete = this.injector.getInstance(QuestFetchCompleteSignal);
		this.questRedeemComplete = this.injector.getInstance(QuestRedeemCompleteSignal);
		this.keyInfoResponse = this.injector.getInstance(KeyInfoResponseSignal);
		this.claimDailyRewardResponse = this.injector.getInstance(ClaimDailyRewardResponseSignal);
		this.statsTracker = this.injector.getInstance(CharactersMetricsTracker);
		this.logger = this.injector.getInstance(ILogger);
		this.handleDeath = this.injector.getInstance(HandleDeathSignal);
		this.zombify = this.injector.getInstance(ZombifySignal);
		this.setGameFocus = this.injector.getInstance(SetGameFocusSignal);
		this.classesModel = this.injector.getInstance(ClassesModel);
		serverConnection = this.injector.getInstance(SocketServer);*/
		this.messages = MessageCenter.getInstance();
		/*this.model = this.injector.getInstance(GameModel);
		this.currentArenaRun = this.injector.getInstance(CurrentArenaRunModel);*/
		this.gs = gs;
		this.server = server;
		this.gameId = gameId;
		this.createCharacter = createCharacter;
		this.charId = charId;
		this.keyTime = keyTime;
		this.key = key;
		this.mapJSON = mapJSON;
		this.isFromArena = isFromArena;/**this.friendModel.setCurrentServer(server_);
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

	/*public void  connect()  {
		this.addServerConnectionListeners();
		this.mapMessages();
		ChatMessage _loc1 = new ChatMessage();
		_loc1_.name = Parameters.CLIENT_CHAT_NAME;
		_loc1_.text = TextKey.CHAT_CONNECTING_TO;
		String loc2 = server_.name;
		if (loc2 == "{\"text\":\"server.vault\"}") {
			loc2 = "server.vault";
		}
		loc2 = LineBuilder.getLocalizedStringFromKey(loc2);
		_loc1_.tokens = {"serverName": loc2};
		this.addTextLine.dispatch(_loc1_);
		serverConnection.connect(server_.address, server_.port);
	}**/

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

		CharacterClass _loc1 = this.classesModel.getSelected();
		Create _loc2 = (Create) this.messages.require(CREATE);
		_loc2.classType = _loc1.id;
		_loc2.skinType = _loc1.skins.getSelectedSkin().id;
		serverConnection.sendMessage(_loc2);
	}

}

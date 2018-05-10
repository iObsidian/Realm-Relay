package realmrelay.game.messaging;

import realmrelay.game.game.AGameSprite;
import realmrelay.game.net.Server;
import realmrelay.game.net.api.MessageMap;
import realmrelay.game.objects.Player;

public class GameServerConnectionConcrete extends GameServerConnection {

	private static final int TO_MILLISECONDS = 1000;
	//private PetUpdater petUpdater;
	//private MessageProvider messages;
	private int playerId = -1;
	private Player player;
	/*private boolean retryConnection = true;
	private Random rand = null;
	private GiftStatusUpdateSignal giftChestUpdateSignal;
	private Death death;
	private Timer retryTimer;
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
		serverConnection = this.injector.getInstance(SocketServer);
		this.messages = this.injector.getInstance(MessageProvider);
		this.model = this.injector.getInstance(GameModel);
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
		MessageMap _loc1 = this.injector.getInstance(MessageMap);
		_loc1.map(CREATE).toMessage(Create);
		_loc1.map(PLAYERSHOOT).toMessage(PlayerShoot);
		_loc1.map(MOVE).toMessage(Move);
		_loc1.map(PLAYERTEXT).toMessage(PlayerText);
		_loc1.map(UPDATEACK).toMessage(Message);
		_loc1.map(INVSWAP).toMessage(InvSwap);
		_loc1.map(USEITEM).toMessage(UseItem);
		_loc1.map(HELLO).toMessage(Hello);
		_loc1.map(INVDROP).toMessage(InvDrop);
		_loc1.map(PONG).toMessage(Pong);
		_loc1.map(LOAD).toMessage(Load);
		_loc1.map(SETCONDITION).toMessage(SetCondition);
		_loc1.map(TELEPORT).toMessage(Teleport);
		_loc1.map(USEPORTAL).toMessage(UsePortal);
		_loc1.map(BUY).toMessage(Buy);
		_loc1.map(PLAYERHIT).toMessage(PlayerHit);
		_loc1.map(ENEMYHIT).toMessage(EnemyHit);
		_loc1.map(AOEACK).toMessage(AoeAck);
		_loc1.map(SHOOTACK).toMessage(ShootAck);
		_loc1.map(OTHERHIT).toMessage(OtherHit);
		_loc1.map(SQUAREHIT).toMessage(SquareHit);
		_loc1.map(GOTOACK).toMessage(GotoAck);
		_loc1.map(GROUNDDAMAGE).toMessage(GroundDamage);
		_loc1.map(CHOOSENAME).toMessage(ChooseName);
		_loc1.map(CREATEGUILD).toMessage(CreateGuild);
		_loc1.map(GUILDREMOVE).toMessage(GuildRemove);
		_loc1.map(GUILDINVITE).toMessage(GuildInvite);
		_loc1.map(REQUESTTRADE).toMessage(RequestTrade);
		_loc1.map(CHANGETRADE).toMessage(ChangeTrade);
		_loc1.map(ACCEPTTRADE).toMessage(AcceptTrade);
		_loc1.map(CANCELTRADE).toMessage(CancelTrade);
		_loc1.map(CHECKCREDITS).toMessage(CheckCredits);
		_loc1.map(ESCAPE).toMessage(Escape);
		_loc1.map(QUEST_ROOM_MSG).toMessage(GoToQuestRoom);
		_loc1.map(JOINGUILD).toMessage(JoinGuild);
		_loc1.map(CHANGEGUILDRANK).toMessage(ChangeGuildRank);
		_loc1.map(EDITACCOUNTLIST).toMessage(EditAccountList);
		_loc1.map(ACTIVE_PET_UPDATE_REQUEST).toMessage(ActivePetUpdateRequest);
		_loc1.map(PETUPGRADEREQUEST).toMessage(PetUpgradeRequest);
		_loc1.map(ENTER_ARENA).toMessage(EnterArena);
		_loc1.map(ACCEPT_ARENA_DEATH).toMessage(OutgoingMessage);
		_loc1.map(QUEST_FETCH_ASK).toMessage(OutgoingMessage);
		_loc1.map(QUEST_REDEEM).toMessage(QuestRedeem);
		_loc1.map(KEY_INFO_REQUEST).toMessage(KeyInfoRequest);
		_loc1.map(PET_CHANGE_FORM_MSG).toMessage(ReskinPet);
		_loc1.map(CLAIM_LOGIN_REWARD_MSG).toMessage(ClaimDailyRewardMessage);
		_loc1.map(FAILURE).toMessage(Failure).toMethod(this.onFailure);
		_loc1.map(CREATE_SUCCESS).toMessage(CreateSuccess).toMethod(this.onCreateSuccess);
		_loc1.map(SERVERPLAYERSHOOT).toMessage(ServerPlayerShoot).toMethod(this.onServerPlayerShoot);
		_loc1.map(DAMAGE).toMessage(Damage).toMethod(this.onDamage);
		_loc1.map(UPDATE).toMessage(Update).toMethod(this.onUpdate);
		_loc1.map(NOTIFICATION).toMessage(Notification).toMethod(this.onNotification);
		_loc1.map(GLOBAL_NOTIFICATION).toMessage(GlobalNotification).toMethod(this.onGlobalNotification);
		_loc1.map(NEWTICK).toMessage(NewTick).toMethod(this.onNewTick);
		_loc1.map(SHOWEFFECT).toMessage(ShowEffect).toMethod(this.onShowEffect);
		_loc1.map(GOTO).toMessage(Goto).toMethod(this.onGoto);
		_loc1.map(INVRESULT).toMessage(InvResult).toMethod(this.onInvResult);
		_loc1.map(RECONNECT).toMessage(Reconnect).toMethod(this.onReconnect);
		_loc1.map(PING).toMessage(Ping).toMethod(this.onPing);
		_loc1.map(MAPINFO).toMessage(MapInfo).toMethod(this.onMapInfo);
		_loc1.map(PIC).toMessage(Pic).toMethod(this.onPic);
		_loc1.map(DEATH).toMessage(Death).toMethod(this.onDeath);
		_loc1.map(BUYRESULT).toMessage(BuyResult).toMethod(this.onBuyResult);
		_loc1.map(AOE).toMessage(Aoe).toMethod(this.onAoe);
		_loc1.map(ACCOUNTLIST).toMessage(AccountList).toMethod(this.onAccountList);
		_loc1.map(QUESTOBJID).toMessage(QuestObjId).toMethod(this.onQuestObjId);
		_loc1.map(NAMERESULT).toMessage(NameResult).toMethod(this.onNameResult);
		_loc1.map(GUILDRESULT).toMessage(GuildResult).toMethod(this.onGuildResult);
		_loc1.map(ALLYSHOOT).toMessage(AllyShoot).toMethod(this.onAllyShoot);
		_loc1.map(ENEMYSHOOT).toMessage(EnemyShoot).toMethod(this.onEnemyShoot);
		_loc1.map(TRADEREQUESTED).toMessage(TradeRequested).toMethod(this.onTradeRequested);
		_loc1.map(TRADESTART).toMessage(TradeStart).toMethod(this.onTradeStart);
		_loc1.map(TRADECHANGED).toMessage(TradeChanged).toMethod(this.onTradeChanged);
		_loc1.map(TRADEDONE).toMessage(TradeDone).toMethod(this.onTradeDone);
		_loc1.map(TRADEACCEPTED).toMessage(TradeAccepted).toMethod(this.onTradeAccepted);
		_loc1.map(CLIENTSTAT).toMessage(ClientStat).toMethod(this.onClientStat);
		_loc1.map(FILE).toMessage(File).toMethod(this.onFile);
		_loc1.map(INVITEDTOGUILD).toMessage(InvitedToGuild).toMethod(this.onInvitedToGuild);
		_loc1.map(PLAYSOUND).toMessage(PlaySound).toMethod(this.onPlaySound);
		_loc1.map(ACTIVEPETUPDATE).toMessage(ActivePet).toMethod(this.onActivePetUpdate);
		_loc1.map(NEW_ABILITY).toMessage(NewAbilityMessage).toMethod(this.onNewAbility);
		_loc1.map(PETYARDUPDATE).toMessage(PetYard).toMethod(this.onPetYardUpdate);
		_loc1.map(EVOLVE_PET).toMessage(EvolvedPetMessage).toMethod(this.onEvolvedPet);
		_loc1.map(DELETE_PET).toMessage(DeletePetMessage).toMethod(this.onDeletePet);
		_loc1.map(HATCH_PET).toMessage(HatchPetMessage).toMethod(this.onHatchPet);
		_loc1.map(IMMINENT_ARENA_WAVE).toMessage(ImminentArenaWave).toMethod(this.onImminentArenaWave);
		_loc1.map(ARENA_DEATH).toMessage(ArenaDeath).toMethod(this.onArenaDeath);
		_loc1.map(VERIFY_EMAIL).toMessage(VerifyEmail).toMethod(this.onVerifyEmail);
		_loc1.map(RESKIN_UNLOCK).toMessage(ReskinUnlock).toMethod(this.onReskinUnlock);
		_loc1.map(PASSWORD_PROMPT).toMessage(PasswordPrompt).toMethod(this.onPasswordPrompt);
		_loc1.map(QUEST_FETCH_RESPONSE).toMessage(QuestFetchResponse).toMethod(this.onQuestFetchResponse);
		_loc1.map(QUEST_REDEEM_RESPONSE).toMessage(QuestRedeemResponse).toMethod(this.onQuestRedeemResponse);
		_loc1.map(KEY_INFO_RESPONSE).toMessage(KeyInfoResponse).toMethod(this.onKeyInfoResponse);
		_loc1.map(LOGIN_REWARD_MSG).toMessage(ClaimDailyRewardResponse).toMethod(this.onLoginRewardResponse);
	}

}

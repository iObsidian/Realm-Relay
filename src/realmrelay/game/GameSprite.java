package realmrelay.game;

import realmrelay.game._as3.Event;
import realmrelay.game._as3.Signal;
import realmrelay.game._as3.Sprite;
import realmrelay.game.account.core.Account;
import realmrelay.game.appengine.api.AppEngineClient;
import realmrelay.game.constants.GeneralConstants;
import realmrelay.game.core.model.MapModel;
import realmrelay.game.core.model.PlayerModel;
import realmrelay.game.dialogs.OpenDialogSignal;
import realmrelay.game.events.MoneyChangedEvent;
import realmrelay.game.game.AGameSprite;
import realmrelay.game.game.MapUserInput;
import realmrelay.game.map.Map;
import realmrelay.game.messaging.GameServerConnectionConcrete;
import realmrelay.game.messaging.incoming.MapInfo;
import realmrelay.game.net.Server;
import realmrelay.game.objects.GameObject;
import realmrelay.game.objects.IInteractiveObject;
import realmrelay.game.objects.Player;
import realmrelay.game.objects.Projectile;
import realmrelay.game.parameters.Parameters;
import realmrelay.game.promotions.model.BeginnersPackageModel;
import realmrelay.game.ui.HUDView;

import javax.swing.*;

public class GameSprite extends AGameSprite {

	//protected static final ColorMatrixFilter PAUSED_FILTER = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);


	public final Signal monitor = new Signal(String, int);

	public final Signal modelInitialized = new Signal();

	public final Signal drawCharacterWindow = new Signal(Player);



	public boolean isNexus = false;



	public MapModel mapModel;

	public BeginnersPackageModel beginnersPackageModel;


	public OpenDialogSignal openDialog;

	public Signal showPackage;


	private GameObject focus;

	private int frameTimeSum = 0;

	private int frameTimeCount = 0;

	private boolean isGameStarted;

	private int displaysPosY = 4;

	private double packageY;


	public  GameSprite(Server param1, int param2, boolean param3, int param4, int param5, byte[] param6, PlayerModel param7, String param8, boolean param9)  {
		this.showPackage = new Signal();
		this.currentPackage = new Sprite();
		super();
		this.model = param7;
		map = new Map(this);

		gsc = new GameServerConnectionConcrete(this, param1, param2, param3, param4, param5, param6, param8, param9);
		mui = new MapUserInput(this);

	}

	public static void  dispatchMapLoaded(MapInfo param1)  {
		MapLoadedSignal loc2 = StaticInjectorContext.getInjector().getInstance(MapLoadedSignal);
		loc2 && loc2.dispatch(param1);
	}

	private static void  hidePreloader()  {
		HideMapLoadingSignal loc1 = StaticInjectorContext.getInjector().getInstance(HideMapLoadingSignal);
		loc1 && loc1.dispatch();
	}

	public void  onChatDown(MouseEvent param1)  {
		if (this.chatPlayerMenu != null) {
			this.removeChatPlayerMenu();
		}
		mui.onMouseDown(param1);
	}

	public void  onChatUp(MouseEvent param1)  {
		mui.onMouseUp(param1);
	}

	override public void  setFocus(GameObject param1)  {
		param1 = param1 || map.player_;
		this.focus = param1;
	}

	public void  addChatPlayerMenu(Player param1, double param2, double param3, String = null param4, boolean = false param5, boolean = false param6)  {
		this.removeChatPlayerMenu();
		this.chatPlayerMenu = new PlayerMenu();
		if (param4 == null) {
			this.chatPlayerMenu.init(this, param1);
		} else if (param6) {
			this.chatPlayerMenu.initDifferentServer(this, param4, param5, param6);
		} else {
			if (param4.length > 0 && (param4.charAt(0) == "#" || param4.charAt(0) == "*" || param4.charAt(0) == "@")) {
				return;
			}
			this.chatPlayerMenu.initDifferentServer(this, param4, param5);
		}
		addChild(this.chatPlayerMenu);
		this.chatPlayerMenu.x = param2;
		this.chatPlayerMenu.y = param3 - this.chatPlayerMenu.height;
	}

	public void  removeChatPlayerMenu()  {
		if (this.chatPlayerMenu != null && this.chatPlayerMenu.parent != null) {
			removeChild(this.chatPlayerMenu);
			this.chatPlayerMenu = null;
		}
	}

	override public void  applyMapInfo(MapInfo param1)  {
		map.setProps(param1.width_, param1.height_, param1.name_, param1.background_, param1.allowPlayerTeleport_, param1.showDisplays_);
		dispatchMapLoaded(param1);
	}

	public void  hudModelInitialized()  {
		hudView = new HUDView();
		hudView.x = 600;
		addChild(hudView);
	}

	override public void  initialize()  {
		Account loc1 = null;
		ShowProTipSignal loc4 = null;
		map.initialize();
		this.modelInitialized.dispatch();
		if (this.evalIsNotInCombatMapArea()) {
			this.showSafeAreaDisplays();
		}
		if (map.name == "Arena") {
			this.showTimer();
			this.showWaveCounter();
		}
		loc1 = StaticInjectorContext.getInjector().getInstance(Account);
		this.googleAnalytics = StaticInjectorContext.getInjector().getInstance(GoogleAnalytics);
		if (map.name == Map.NEXUS) {
			this.addToQueueSignal.dispatch(PopupNamesConfig.DAILY_LOGIN_POPUP, this.openDailyCalendarPopupSignal, -1, null);
			if (this.beginnersPackageModel.isBeginnerAvailable()) {
				this.addToQueueSignal.dispatch(PopupNamesConfig.BEGINNERS_OFFER_POPUP, this.showBeginnersPackage, 1, null);
			} else {
				this.addToQueueSignal.dispatch(PopupNamesConfig.PACKAGES_OFFER_POPUP, this.showPackage, 1, null);
			}
			this.flushQueueSignal.dispatch();
		}
		this.isNexus = map.name == Map.NEXUS;
		if (this.isNexus || map.name == Map.DAILY_QUEST_ROOM) {
			this.creditDisplay = new CreditDisplay(this, true, true);
		} else {
			this.creditDisplay = new CreditDisplay(this);
		}
		this.creditDisplay.x = 594;
		this.creditDisplay.y = 0;
		addChild(this.creditDisplay_);
		AppEngineClient loc2 = StaticInjectorContext.getInjector().getInstance(AppEngineClient);
		var loc3:Object = {
				"game_net_user_id": loc1.gameNetworkUserId(),
				"game_net": loc1.gameNetwork(),
				"play_platform": loc1.playPlatform()
        };
		MoreObjectUtil.addToObject(loc3, loc1.getCredentials());
		if (map.name != "Kitchen" && map.name != "Tutorial" && map.name != "Nexus Explanation" && Parameters.data.watchForTutorialExit == true) {
			Parameters.data.watchForTutorialExit = false;
			this.callTracking("rotmg.Marketing.track(\"tutorialComplete\")");
			loc3["fteStepCompleted"] = 9900;
			loc2.sendRequest("/log/logFteStep", loc3);
		}
		if (map.name == "Kitchen") {
			loc3["fteStepCompleted"] = 200;
			loc2.sendRequest("/log/logFteStep", loc3);
		}
		if (map.name == "Tutorial") {
			if (Parameters.data.needsTutorial == true) {
				Parameters.data.watchForTutorialExit = true;
				this.callTracking("rotmg.Marketing.track(\"install\")");
				loc3["fteStepCompleted"] = 100;
				loc2.sendRequest("/log/logFteStep", loc3);
			}
			this.startTutorial();
		} else if (map.name != "Arena" && map.name != "Kitchen" && map.name != "Nexus Explanation" && map.name != "Vault Explanation" && map.name != "Guild Explanation" && !this.evalIsNotInCombatMapArea() && Parameters.data.showProtips) {
			loc4 = StaticInjectorContext.getInjector().getInstance(ShowProTipSignal);
			loc4 && loc4.dispatch();
		}
		if (map.name == Map.DAILY_QUEST_ROOM) {
			gsc.questFetch();
		}
		Parameters.save();
		hidePreloader();
	}

	private void  showSafeAreaDisplays()  {
		this.showRankText();
		this.showGuildText();
		this.showShopDisplay();
		this.showGiftStatusDisplay();
		this.showNewsUpdate();
		this.showNewsTicker();
	}

	private void  setDisplayPosY(double param1)  {
		double loc2 = UIUtils.NOTIFICATION_SPACE * param1;
		if (param1 != 0) {
			this.displaysPosY = 4 + loc2;
		} else {
			this.displaysPosY = 2;
		}
	}

	private void  showTimer()  {
		this.arenaTimer = new ArenaTimer();
		this.arenaTimer.y = 5;
		addChild(this.arenaTimer);
	}

	private void  showWaveCounter()  {
		this.arenaWaveCounter = new ArenaWaveCounter();
		this.arenaWaveCounter.y = 5;
		this.arenaWaveCounter.x = 5;
		addChild(this.arenaWaveCounter);
	}

	private void  showNewsTicker()  {
		this.newsTicker = new NewsTicker();
		this.newsTicker.x = 300 - this.newsTicker.width / 2;
		this.setDisplayPosY(4);
		this.newsTicker.y = this.displaysPosY;
		addChild(this.newsTicker);
	}

	private void  showGiftStatusDisplay()  {
		this.giftStatusDisplay = new GiftStatusDisplay();
		this.giftStatusDisplay.x = 6;
		this.setDisplayPosY(2);
		this.giftStatusDisplay.y = this.displaysPosY;
		addChild(this.giftStatusDisplay);
	}

	private void  showShopDisplay()  {
		this.shopDisplay = new ShopDisplay(map.name == Map.NEXUS);
		this.shopDisplay.x = 6;
		this.setDisplayPosY(1);
		this.shopDisplay.y = this.displaysPosY;
		addChild(this.shopDisplay);
	}

	private void  showNewsUpdate(boolean = true param1)  {
		NewsModalButton loc4 = null;
		ILogger loc2 = StaticInjectorContext.getInjector().getInstance(ILogger);
		NewsModel loc3 = StaticInjectorContext.getInjector().getInstance(NewsModel);
		loc2.debug("NEWS UPDATE -- making button");
		if (loc3.hasValidModalNews()) {
			loc2.debug("NEWS UPDATE -- making button - ok");
			loc4 = new NewsModalButton();
			if (this.newsModalButton != null) {
				removeChild(this.newsModalButton);
			}
			loc4.x = 6;
			this.setDisplayPosY(3);
			loc4.y = this.displaysPosY;
			this.newsModalButton = loc4;
			addChild(this.newsModalButton);
		}
	}

	public void  refreshNewsUpdateButton()  {
		ILogger loc1 = StaticInjectorContext.getInjector().getInstance(ILogger);
		loc1.debug("NEWS UPDATE -- refreshing button, update noticed");
		this.showNewsUpdate(false);
	}

	private void  setYAndPositionPackage()  {
		this.packageY = this.displaysPosY + 2;
		this.displaysPosY = this.displaysPosY + UIUtils.NOTIFICATION_SPACE;
		this.positionPackage();
	}

	public void  showBeginnersButtonIfSafe()  {
		if (this.evalIsNotInCombatMapArea()) {
			this.addAndPositionPackage(new BeginnersPackageButton());
		}
	}

	public void  showPackageButtonIfSafe()  {
		if (!this.evalIsNotInCombatMapArea()) {
		}
	}

	private void  addAndPositionPackage(DisplayObject param1)  {
		this.currentPackage = param1;
		addChild(this.currentPackage);
		this.positionPackage();
	}

	private void  positionPackage()  {
		this.currentPackage.x = 80;
		this.setDisplayPosY(1);
		this.currentPackage.y = this.displaysPosY;
	}

	private void  showGuildText()  {
		this.guildText = new GuildText("", -1);
		this.guildText.x = 64;
		this.setDisplayPosY(0);
		this.guildText.y = this.displaysPosY;
		addChild(this.guildText_);
	}

	private void  showRankText()  {
		this.rankText = new RankText(-1, true, false);
		this.rankText.x = 8;
		this.setDisplayPosY(0);
		this.rankText.y = this.displaysPosY;
		addChild(this.rankText_);
	}

	private void  callTracking(String param1)  {
		if (ExternalInterface.available == false) {
			return;
		}
		try {
			ExternalInterface.call(param1);
			return;
		}
		catch (err:Error) {
			return;
		}
	}

	private void  startTutorial()  {
		tutorial = new Tutorial(this);
		addChild(tutorial_);
	}

	private void  updateNearestInteractive()  {
		double loc4 = NaN;
		GameObject loc7 = null;
		IInteractiveObject loc8 = null;
		if (!map || !map.player_) {
			return;
		}
		Player loc1 = map.player_;
		double loc2 = GeneralConstants.MAXIMUM_INTERACTION_DISTANCE;
		IInteractiveObject loc3 = null;
		double loc5 = loc1.x_;
		double loc6 = loc1.y_;
		for(loc7 in map.goDict_) {
			loc8 = loc7 as IInteractiveObject;
			if (loc8 && (!(loc8 is Pet) || this.map.isPetYard)) {
				if (Math.abs(loc5 - loc7.x_) < GeneralConstants.MAXIMUM_INTERACTION_DISTANCE || Math.abs(loc6 - loc7.y_) < GeneralConstants.MAXIMUM_INTERACTION_DISTANCE) {
					loc4 = PointUtil.distanceXY(loc7.x_, loc7.y_, loc5, loc6);
					if (loc4 < GeneralConstants.MAXIMUM_INTERACTION_DISTANCE && loc4 < loc2) {
						loc2 = loc4;
						loc3 = loc8;
					}
				}
			}
		}
		this.mapModel.currentInteractiveTarget = loc3;
	}

	private boolean  isPetMap()  {
		return true;
	}

	public void  connect()  {
		if (!this.isGameStarted) {
			this.isGameStarted = true;
			Renderer.inGame = true;
			gsc.connect();
			this.idleWatcher.start(this);
			lastUpdate = getTimer();
			stage.addEventListener(MoneyChangedEvent.MONEY_CHANGED, this.onMoneyChanged);
			stage.addEventListener(Event.ENTER_FRAME, this.onEnterFrame);
			LoopedProcess.addProcess(new LoopedCallback(100, this.updateNearestInteractive));
		}
	}

	public void  disconnect()  {
		if (this.isGameStarted) {
			this.isGameStarted = false;
			Renderer.inGame = false;
			this.idleWatcher.stop();
			stage.removeEventListener(MoneyChangedEvent.MONEY_CHANGED, this.onMoneyChanged);
			stage.removeEventListener(Event.ENTER_FRAME, this.onEnterFrame);
			LoopedProcess.destroyAll();
			contains(map) && removeChild(map);
			map.dispose();
			CachingColorTransformer.clear();
			TextureRedrawer.clearCache();
			Projectile.dispose();
			gsc.disconnect();
		}
	}

	private void  onMoneyChanged(Event param1)  {
		gsc.checkCredits();
	}

	override public boolean  evalIsNotInCombatMapArea()  {
		return map.name == Map.NEXUS || map.name == Map.VAULT || map.name == Map.GUILD_HALL || map.name == Map.CLOTH_BAZAAR || map.name == Map.NEXUS_EXPLANATION || map.name == Map.DAILY_QUEST_ROOM;
	}

	private void  onEnterFrame(Event param1)  {
		double loc7 = NaN;
		int loc2 = getTimer();
		int loc3 = loc2 - lastUpdate_;
		if (this.idleWatcher.update(loc3)) {
			closed.dispatch();
			return;
		}
		LoopedProcess.runProcesses(loc2);
		this.frameTimeSum = this.frameTimeSum + loc3;
		this.frameTimeCount = this.frameTimeCount + 1;
		if (this.frameTimeSum > 300000) {
			loc7 = int(Math.round(1000 * this.frameTimeCount / this.frameTimeSum_));
			this.googleAnalytics.trackEvent("performance", "frameRate", map.name_, loc7);
			this.frameTimeCount = 0;
			this.frameTimeSum = 0;
		}
		int loc4 = getTimer();
		map.update(loc2, loc3);
		this.monitor.dispatch("Map.update", getTimer() - loc4);
		camera.update(loc3);
		Player loc5 = map.player_;
		if (this.focus) {
			camera.configureCamera(this.focus, !!loc5 ? boolean(loc5.isHallucinating()) : false);
			map.draw(camera_, loc2);
		}
		if (loc5 != null) {
			this.creditDisplay.draw(loc5.credits. loc5.fame. loc5.tokens.;
			this.drawCharacterWindow.dispatch(loc5);
			if (this.evalIsNotInCombatMapArea()) {
				this.rankText.draw(loc5.numStars.;
				this.guildText.draw(loc5.guildName. loc5.guildRank.;
			}
			if (loc5.isPaused()) {
				map.filters = [PAUSED_FILTER];
				hudView.filters = [PAUSED_FILTER];
				map.mouseEnabled = false;
				map.mouseChildren = false;
				hudView.mouseEnabled = false;
				hudView.mouseChildren = false;
			} else if (map.filters.length > 0) {
				map.filters = [];
				hudView.filters = [];
				map.mouseEnabled = true;
				map.mouseChildren = true;
				hudView.mouseEnabled = true;
				hudView.mouseChildren = true;
			}
			moveRecords.addRecord(loc2, loc5.x. loc5.y.;
		}
		lastUpdate = loc2;
		int loc6 = getTimer() - loc2;
		this.monitor.dispatch("GameSprite.loop", loc6);
	}

	public void  showPetToolTip(boolean param1)  {
	}




}

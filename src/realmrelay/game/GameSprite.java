package realmrelay.game;

import javafx.scene.Camera;
import realmrelay.game._as3.Signal;
import realmrelay.game.account.core.Account;
import realmrelay.game.appengine.api.AppEngineClient;
import realmrelay.game.constants.GeneralConstants;
import realmrelay.game.core.model.MapModel;
import realmrelay.game.core.model.PlayerModel;
import realmrelay.game.game.AGameSprite;
import realmrelay.game.game.MoveRecords;
import realmrelay.game.map.Map;
import realmrelay.game.messaging.GameServerConnection;
import realmrelay.game.messaging.incoming.MapInfo;
import realmrelay.game.net.Server;
import realmrelay.game.objects.GameObject;
import realmrelay.game.objects.IInteractiveObject;
import realmrelay.game.objects.Player;
import realmrelay.game.objects.Projectile;
import realmrelay.game.parameters.Parameters;
import realmrelay.game.promotions.model.BeginnersPackageModel;
import realmrelay.game.ui.HUDView;
import realmrelay.game.util.TextureRedrawer;

public class GameSprite extends AGameSprite {

	//protected static final ColorMatrixFilter PAUSED_FILTER = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);

	public final Signal closed = new Signal();

	public final Signal monitor = new Signal<String, Integer>();

	public final Signal modelInitialized = new Signal();

	public final Signal drawCharacterWindow = new Signal<Player>();

	public Map map;

	public Camera camera;

	public GameServerConnection gsc;

	public MapUserInput mui;

	public TextBox textBox;

	public Tutorial tutorial;

	public boolean isNexus = false;

	public IdleWatcher idleWatcher;

	public HUDView hudView;

	public RankText rankText;

	public GuildText guildText;

	public CreditDisplay creditDisplay;

	public GiftStatusDisplay giftStatusDisplay;

	public boolean isEditor;

	public int lastUpdate = 0;

	public MoveRecords moveRecords;

	public MapModel mapModel;

	public BeginnersPackageModel beginnersPackageModel;

	public PlayerModel model;

	public ShowBeginnersPackageSignal showBeginnersPackage;

	public Signal showPackage;

	public Packages packages;

	private GameObject focus;

	private int frameTimeSum = 0;

	private int frameTimeCount = 0;

	private boolean isGameStarted;

	private int displaysPosY = 4;

	private DisplayObject currentPackage;

	private float packageY;

	public GameSprite(Server server, int gameId, boolean createCharacter, int charId, int keyTime, ByteArray key, PlayerModel model, String mapJSON) {
		this.camera = new Camera();
		this.moveRecords = new MoveRecords();
		this.showPackage = new Signal();
		super();
		this.model = model;
		this.map = new Map(this);
		addChild(this.map);
		this.gsc = new GameServerConnection(this, server, gameId, createCharacter, charId, keyTime, key, mapJSON);
		this.mui = new MapUserInput(this);
		this.textBox = new TextBox(this, 600, 600);
		addChild(this.textBox);
		this.idleWatcher = new IdleWatcher();
	}

	public void setFocus(GameObject focus) {
		if (focus != null) {
			this.focus = focus;
		} else {
			this.focus = this.map.player;
		}
	}

	public void applyMapInfo(MapInfo mapInfo) {
		this.map.setProps(mapInfo.width, mapInfo.height, mapInfo.name, mapInfo.background, mapInfo.allowPlayerTeleport, mapInfo.showDisplays);
		this.showPreloader(mapInfo);
	}

	public void showPreloader(MapInfo mapInfo) {
		ShowMapLoadingSignal showMapLoading = StaticInjectorContext.getInjector().getInstance(ShowMapLoadingSignal);
		showMapLoading && showMapLoading.dispatch(mapInfo);
	}

	private void hidePreloader() {
		HideMapLoadingSignal hideMapLoading = StaticInjectorContext.getInjector().getInstance(HideMapLoadingSignal);
		hideMapLoading && hideMapLoading.dispatch();
	}

	public void hudModelInitialized()

	{
		this.hudView = new HUDView();
		this.hudView.x = 600;
		addChild(this.hudView);
	}

	public void initialize() {
		ShowProTipSignal showProTip = null;
		this.map.initialize();
		this.creditDisplay = new CreditDisplay(this);
		this.creditDisplay.x = 594;
		this.creditDisplay.y = 0;
		addChild(this.creditDisplay);
		this.modelInitialized.dispatch();
		if (this.map.showDisplays) {
			this.showSafeAreaDisplays();
		}
		if (this.packages.shouldSpam() && this.map.name.equals(Map.NEXUS)) {
			if (this.beginnersPackageModel.isBeginnerAvailable()) {
				this.showBeginnersPackage.dispatch();
			} else {
				this.showPackage.dispatch();
			}
			this.packages.numSpammed++;
		}
		this.isNexus = this.map.name.equals(Map.NEXUS);
		AppEngineClient aeClient = StaticInjectorContext.getInjector().getInstance();
		Account account = StaticInjectorContext.getInjector().getInstance();
		var params:Object = {
				"game_net_user_id":account.gameNetworkUserId(),
				"game_net":account.gameNetwork(),
				"play_platform":account.playPlatform()
         };
		MoreObjectUtil.addToObject(params, account.getCredentials());
		if (!this.map.name.equals("Kitchen") && this.map.name != "Tutorial" && this.map.name != "Nexus Explanation" && Parameters.data.watchForTutorialExit == true) {
			Parameters.data.watchForTutorialExit = false;
			this.callTracking("rotmg.Marketing.track(\"tutorialComplete\")");
			params["fteStepCompleted"] = 9900;
			aeClient.sendRequest("/log/logFteStep", params);
		}
		if (this.map.name.equals("Kitchen")) {
			params["fteStepCompleted"] = 200;
			aeClient.sendRequest("/log/logFteStep", params);
		}
		if (this.map.name.equals("Tutorial")) {
			if (Parameters.data.needsTutorial == true) {
				Parameters.data.watchForTutorialExit = true;
				this.callTracking("rotmg.Marketing.track(\"install\")");
				params["fteStepCompleted"] = 100;
				aeClient.sendRequest("/log/logFteStep", params);
			}
			this.startTutorial();
		} else if (this.map.name != "Kitchen" && this.map.name != "Nexus Explanation" && this.map.name != "Vault Explanation" && this.map.name != "Guild Explanation" && !this.map.showDisplays && Parameters.data.showProtips) {
			showProTip = StaticInjectorContext.getInjector().getInstance(ShowProTipSignal);
			showProTip && showProTip.dispatch();
		}
		Parameters.save();
		this.hidePreloader();
	}

	private void showSafeAreaDisplays() {
		this.showRankText();
		this.showGuildText();
		this.setYAndPositionPackage();
		this.showGiftStatusDisplay();
	}

	private void showGiftStatusDisplay() {
		this.giftStatusDisplay = new GiftStatusDisplay();
		this.giftStatusDisplay.x = 6;
		this.giftStatusDisplay.y = this.displaysPosY;
		this.displaysPosY = this.displaysPosY + UIUtils.NOTIFICATION_SPACE;
		addChild(this.giftStatusDisplay);
	}

	private void setYAndPositionPackage()

	{
		this.packageY = this.displaysPosY + 2;
		this.displaysPosY = this.displaysPosY + UIUtils.NOTIFICATION_SPACE;
		this.positionPackage();
	}

	private void positionPackage()

	{
		this.currentPackage.x = 6;
		this.currentPackage.y = this.packageY;
	}

	public void showBeginnersOfferButton() {
		this.currentPackage = new BeginnersPackageButton();
		this.addAndPositionPackager();
	}

	public void showPackageButton()

	{
		this.currentPackage = new PackageButton();
		addChild(this.currentPackage);
		this.positionPackage();
	}

	private void addAndPositionPackager() {
		addChild(this.currentPackage);
		this.positionPackage();
	}

	private function showGuildText() :void

	{
		this.guildText = new GuildText("", -1);
		this.guildText.x = 64;
		this.guildText.y = 6;
		addChild(this.guildText_);
	}

	private function showRankText() :void

	{
		this.rankText = new RankText(-1, true, false);
		this.rankText.x = 8;
		this.rankText.y = this.displaysPosY;
		this.displaysPosY = this.displaysPosY + UIUtils.NOTIFICATION_SPACE;
		addChild(this.rankText_);
	}

	private function callTracking(functionName:String) :void

	{
		if (ExternalInterface.available == false) {
			return;
		}
		try {
			ExternalInterface.call(functionName);
		} catch (err:Error)
		{
		}
	}

	private function startTutorial() :void

	{
		this.tutorial = new Tutorial(this);
		addChild(this.tutorial_);
	}

	private function updateNearestInteractive() :void

	{
		float dist = NaN;
		GameObject go = null;
		IInteractiveObject iObj = null;
		if (!this.map || !this.map.player_) {
			return;
		}
		Player player = this.map.player_;
		float minDist = GeneralConstants.MAXIMUM_INTERACTION_DISTANCE;
		IInteractiveObject closestInteractive = null;
		float playerX = player.x_;
		float playerY = player.y_;
		for (go in this.map.goDict_) {
			iObj = go as IInteractiveObject;
			if (iObj) {
				if (Math.abs(playerX - go.x_) < GeneralConstants.MAXIMUM_INTERACTION_DISTANCE || Math.abs(playerY - go.y_) < GeneralConstants.MAXIMUM_INTERACTION_DISTANCE) {
					dist = PointUtil.distanceXY(go.x_, go.y_, playerX, playerY);
					if (dist < GeneralConstants.MAXIMUM_INTERACTION_DISTANCE && dist < minDist) {
						minDist = dist;
						closestInteractive = iObj;
					}
				}
			}
		}
		this.mapModel.currentInteractiveTarget = closestInteractive;
	}

	// Build GSC
	public void connect() {
		if (!this.isGameStarted) {
			this.isGameStarted = true;
			this.gsc.connect();
			this.idleWatcher.start(this);
			this.lastUpdate = getTimer();
			stage.addEventListener(MoneyChangedEvent.MONEY_CHANGED, this::onMoneyChanged);
			stage.addEventListener(Event.ENTER_FRAME, this::onEnterFrame);
			LoopedProcess.addProcess(new LoopedCallback(100, this.updateNearestInteractive));
		}
	}

	public void disconnect() {
		if (this.isGameStarted) {
			this.isGameStarted = false;
			this.idleWatcher.stop();
			this.gsc.serverConnection.disconnect();
			stage.removeEventListener(MoneyChangedEvent.MONEY_CHANGED, this.onMoneyChanged);
			stage.removeEventListener(Event.ENTER_FRAME, this.onEnterFrame);
			LoopedProcess.destroyAll();
			contains(this.map) && removeChild(this.map);
			this.map.dispose();
			CachingColorTransformer.clear();
			TextureRedrawer.clearCache();
			Projectile.dispose();
			this.gsc.disconnect();
		}
	}

	private function onMoneyChanged(event:Event) :void

	{
		this.gsc.checkCredits();
	}

	public function evalIsNotInCombatMapArea() :boolean

	{
		return this.map.name == "Nexus" || this.map.name == "Vault" || this.map.name == "Guild Hall";
	}

	private function onEnterFrame(event:Event) :void

	{
		float avgFrameRate = NaN;
		int time = getTimer();
		int dt = time - this.lastUpdate_;
		if (this.idleWatcher.update(dt)) {
			this.closed.dispatch();
			return;
		}
		LoopedProcess.runProcesses(time);
		this.frameTimeSum = this.frameTimeSum + dt;
		this.frameTimeCount = this.frameTimeCount + 1;
		if (this.frameTimeSum > 300000) {
			avgFrameRate = int(Math.round(1000 * this.frameTimeCount / this.frameTimeSum_));
			GA.global().trackEvent("performance", "frameRate", this.map.name_, avgFrameRate);
			this.frameTimeCount = 0;
			this.frameTimeSum = 0;
		}
		int mapTime = getTimer();
		this.map.update(time, dt);
		this.monitor.dispatch("Map.update", getTimer() - mapTime);
		this.camera.update(dt);
		Player player = this.map.player_;
		if (this.focus) {
			this.camera.configureCamera(this.focus,boolean(player) ?boolean(player.isHallucinating()):boolean(false));
			this.map.draw(this.camera_, time);
		}
		if (player != null) {
			this.creditDisplay.draw(player.credits.player.fame.;
			this.drawCharacterWindow.dispatch(player);
			if (this.map.showDisplays_) {
				this.rankText.draw(player.numStars.;
				this.guildText.draw(player.guildName.player.guildRank.;
			}
			if (player.isPaused()) {
				this.map.filters = [PAUSED_FILTER];
				this.hudView.filters = [PAUSED_FILTER];
				this.map.mouseEnabled = false;
				this.map.mouseChildren = false;
				this.hudView.mouseEnabled = false;
				this.hudView.mouseChildren = false;
			} else if (this.map.filters.length > 0) {
				this.map.filters = [];
				this.hudView.filters = [];
				this.map.mouseEnabled = true;
				this.map.mouseChildren = true;
				this.hudView.mouseEnabled = true;
				this.hudView.mouseChildren = true;
			}
			this.moveRecords.addRecord(time, player.x.player.y.;
		}
		this.lastUpdate = time;
		int delta = getTimer() - time;
		this.monitor.dispatch("GameSprite.loop", delta);
	}


}

package rotmg;

import static java.lang.Float.NaN;

import org.osflash.signals.Signal;

import flash.events.Event;
import rotmg.core.model.MapModel;
import rotmg.core.model.PlayerModel;
import rotmg.game.AGameSprite;
import rotmg.game.MapUserInput;
import rotmg.map.Map;
import rotmg.maploading.signals.HideMapLoadingSignal;
import rotmg.maploading.signals.MapLoadedSignal;
import rotmg.messaging.GameServerConnectionConcrete;
import rotmg.messaging.incoming.MapInfo;
import rotmg.net.Server;
import rotmg.objects.GameObject;
import rotmg.objects.Player;
import rotmg.promotions.model.BeginnersPackageModel;
import rotmg.stage3d.Renderer;
import rotmg.ui.GuildText;


public class GameSprite extends AGameSprite {

	public final Signal modelInitialized = new Signal();

	public final Signal drawCharacterWindow = new Signal<Player>();


	public boolean isNexus = false;

	public GuildText guildText;

	public MapModel mapModel;

	public BeginnersPackageModel beginnersPackageModel;

	private GameObject focus;

	private int frameTimeSum = 0;

	private int frameTimeCount = 0;

	private boolean isGameStarted;

	private int displaysPosY = 4;

	private double packageY;

	//public PlayerMenu chatPlayerMenu;

	public GameSprite(Server param1, int param2, boolean param3, int param4, int param5, byte[] param6, PlayerModel param7, byte[] param8, boolean param9) {
		this.model = param7;
		map = new Map(this);
		gsc = new GameServerConnectionConcrete(this, param1, param2, param3, param4, param5, param6, param8, param9);
		mui = new MapUserInput(this);
	}

	public static void dispatchMapLoaded(MapInfo param1) {
		MapLoadedSignal loc2 = MapLoadedSignal.getInstance();
		loc2.dispatch(param1);
	}

	private static void hidePreloader() {
		HideMapLoadingSignal loc1 = HideMapLoadingSignal.getInstance();
		loc1.dispatch();
	}

	@Override
	public void applyMapInfo(MapInfo param1) {
		map.setProps(param1.width, param1.height, param1.name, param1.background, param1.allowPlayerTeleport, param1.showDisplays);
		dispatchMapLoaded(param1);
	}

	@Override
	public void initialize() {
		map.initialize();
		this.modelInitialized.dispatch();
		if (this.evalIsNotInCombatMapArea()) {
			this.showSafeAreaDisplays();
		}

		this.isNexus = map.name.equals(Map.NEXUS);

		hidePreloader();
	}

	private void showSafeAreaDisplays() {
		/**this.showRankText();
		this.showGuildText();
		this.showShopDisplay();
		this.showGiftStatusDisplay();
		this.showNewsUpdate();
		this.showNewsTicker();*/
	}

	public void connect() {
		if (!this.isGameStarted) {
			this.isGameStarted = true;
			Renderer.inGame = true;
			gsc.connect();
			lastUpdate = getTimer();
			//stage.addEventListener(Event.ENTERFRAME, this.onEnterFrame);
		}
	}

	private void onMoneyChanged(Event param1) {
		gsc.checkCredits();
	}

	@Override
	public boolean evalIsNotInCombatMapArea() {
		return map.name.equals(Map.NEXUS) ||
				map.name.equals(Map.VAULT) ||
				map.name.equals(Map.GUILD_HALL) ||
				map.name.equals(Map.CLOTH_BAZAAR) ||
				map.name.equals(Map.NEXUS_EXPLANATION) ||
				map.name.equals(Map.DAILY_QUEST_ROOM);
	}

	private void onEnterFrame(Event event) {
		Number avgFrameRate = NaN;
		int time = getTimer();
		int dt = time - this.lastUpdate;
		this.frameTimeSum = this.frameTimeSum + dt;
		this.frameTimeCount = this.frameTimeCount + 1;
		if (this.frameTimeSum > 300000) {
			avgFrameRate = (int) Math.round(1000 * this.frameTimeCount / this.frameTimeSum);
			this.frameTimeCount = 0;
			this.frameTimeSum = 0;
		}
		int mapTime = getTimer();
		this.map.update(time, dt);
		this.camera.update(dt);
		Player player = this.map.player;
		if (this.focus != null) {
			this.camera.configureCamera(this.focus, player != null ? player.isHallucinating() : false);
			this.map.draw(this.camera, time);
		}
		if (player != null) {

			this.moveRecords.addRecord(time, player.x, player.y);
		}
		this.lastUpdate = time;
		int delta = getTimer() - time;
	}


	public void showPetToolTip(boolean param1) {
	}

}

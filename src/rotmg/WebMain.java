package rotmg;

//The uppermost Sprite

import alde.flash.utils.EventConsumer;
import flash.display.Sprite;
import flash.display.Stage;
import flash.display.StageScaleMode;
import flash.events.Event;
import flash.system.Capabilities;
import rotmg.account.core.WebAccount;
import rotmg.core.model.PlayerModel;
import rotmg.net.Server;
import rotmg.parameters.Parameters;
import rotmg.startup.control.StartupSignal;
import rotmg.ui.UIUtils;
import rotmg.util.AssetLoader;
import rotmg.util.StageProxy;

//[SWF(width=800,height=600,frameRate=60,backgroundColor=000000)]
public class WebMain extends Sprite {

	public static Stage STAGE;

	public WebMain() {
		super();
		if (stage != null) {
			this.setup();
		} else {
			addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		}
	}

	private void onAddedToStage(Event param1) {
		removeEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		this.setup();
	}

	private void setup() {
		STAGE = stage;
		this.hackParameters();
		this.createContext();
		new AssetLoader().load();
		stage.scaleMode = StageScaleMode.EXACT_FIT;
		StartupSignal.getInstance().dispatch();
		this.configureForAirIfDesktopPlayer();
		UIUtils.toggleQuality(Parameters.data.uiQuality);

		// Following is a loose implementation of PlayGameCommand's makeGameView

		PlayerModel p = PlayerModel.getInstance();
		p.account = WebAccount.getInstance();
		p.currentCharId = 2;
		p.setIsAgeVerified(true);

		Server loc1 = new Server().setAddress("54.183.179.205").setPort(2050);

		boolean createCharacter = false;
		int keyTime = -1;
		byte[] loc6 = new byte[0];
		boolean isFromArena = false;

		GameSprite g = new GameSprite(loc1, Parameters.NEXUS_GAMEID, createCharacter, p.currentCharId, keyTime, loc6, p, null, isFromArena);
		g.connect();
	}

	private void hackParameters() {
		Parameters.root = stage.root;
	}

	private void createContext() {
		/*this.context = new StaticInjectorContext();
		this.context.injector.map(LoaderInfo).toValue(root.stage.root.loaderInfo);*/
		StageProxy loc1 = new StageProxy(this);
		/*this.context.injector.map(StageProxy.class).toValue(loc1);
		this.context.extend(MVCSBundle).extend(SignalCommandMapExtension).configure(BuildConfig).configure(StartupConfig).configure(NetConfig).configure(AssetsConfig).configure(DialogsConfig).configure(EnvironmentConfig).configure(ApplicationConfig).configure(LanguageConfig).configure(TextConfig).configure(AppEngineConfig).configure(AccountConfig).configure(ErrorConfig).configure(CoreConfig).configure(ApplicationSpecificConfig).configure(DeathConfig).configure(CharactersConfig).configure(ServersConfig).configure(GameConfig).configure(UIConfig).configure(MiniMapConfig).configure(LegendsConfig).configure(NewsConfig).configure(FameConfig).configure(TooltipsConfig).configure(PromotionsConfig).configure(ProTipConfig).configure(MapLoadingConfig).configure(ClassesConfig).configure(PackageConfig).configure(PetsConfig).configure(DailyLoginConfig).configure(Stage3DConfig).configure(ArenaConfig).configure(ExternalConfig).configure(MysteryBoxConfig).configure(FortuneConfig).configure(DailyQuestsConfig).configure(FriendsConfig).configure(this);
		this.context.logLevel = LogLevel.DEBUG;*/
	}

	private void configureForAirIfDesktopPlayer() {
		if (Capabilities.playerType.equals("Desktop")) {
			Parameters.data.fullscreenMode = false;
			Parameters.save();
		}
	}
}

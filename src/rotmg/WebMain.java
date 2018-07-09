package rotmg;

import alde.flash.utils.EventConsumer;
import flash.display.Sprite;
import flash.display.Stage;
import flash.display.StageScaleMode;
import flash.events.Event;
import flash.system.Capabilities;
import robotlegs.bender.framework.api.IContext;
import rotmg.game.commands.PlayGameCommand;
import rotmg.net.Server;
import rotmg.parameters.Parameters;
import rotmg.startup.control.StartupSignal;
import rotmg.util.AssetLoader;

public class WebMain extends Sprite {

	public static Stage STAGE;

	public static String USER_AGENT = "None";

	protected IContext context;

	public WebMain() {
		super();


		// Bellow is bootleg code

		this.setup();

		/*if (stage != null) {
			this.setup();
		} else {
			addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		}*/
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
		//UIUtils.toggleQuality(Parameters.data.uiQuality);


		// Following is a loose implementation of PlayGameCommand's makeGameView

		PlayGameCommand playGameCommand = new PlayGameCommand();



	}

	private void hackParameters() {
		//Parameters.root = stage.root;
	}

	private void createContext() {
		/*this.context = new StaticInjectorContext();
		this.context.injector.map(LoaderInfo).toValue(root.stage.root.loaderInfo);
		StageProxy loc1 = new StageProxy(this);
		this.context.injector.map(StageProxy).toValue(loc1);
		this.context.extend(MVCSBundle).extend(SignalCommandMapExtension).configure(BuildConfig).configure(StartupConfig).configure(NetConfig).configure(AssetsConfig).configure(DialogsConfig).configure(EnvironmentConfig).configure(ApplicationConfig).configure(LanguageConfig).configure(TextConfig).configure(AppEngineConfig).configure(AccountConfig).configure(ErrorConfig).configure(CoreConfig).configure(ApplicationSpecificConfig).configure(DeathConfig).configure(CharactersConfig).configure(ServersConfig).configure(GameConfig).configure(UIConfig).configure(MiniMapConfig).configure(LegendsConfig).configure(NewsConfig).configure(FameConfig).configure(TooltipsConfig).configure(PromotionsConfig).configure(ProTipConfig).configure(MapLoadingConfig).configure(ClassesConfig).configure(PackageConfig).configure(PetsConfig).configure(DailyLoginConfig).configure(Stage3DConfig).configure(ArenaConfig).configure(ExternalConfig).configure(MysteryBoxConfig).configure(FortuneConfig).configure(DailyQuestsConfig).configure(FriendsConfig).configure(this);
		this.context.logLevel = LogLevel.DEBUG;**/
	}

	private void configureForAirIfDesktopPlayer() {
		if (Capabilities.playerType.equals("Desktop")) {
			//Parameters.data.fullscreenMode = false;
			Parameters.save();
		}
	}
}

package rotmg.view;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import robotlegs.bender.bundles.mvcs.Mediator;
import rotmg.GameSprite;
import rotmg.core.model.MapModel;
import rotmg.core.model.PlayerModel;
import rotmg.core.signals.InvalidateDataSignal;
import rotmg.core.signals.SetScreenSignal;
import rotmg.core.signals.SetScreenWithValidDataSignal;
import rotmg.core.signals.TrackPageViewSignal;
import rotmg.dailyLogin.signal.ShowDailyCalendarPopupSignal;
import rotmg.dialogs.AddPopupToStartupQueueSignal;
import rotmg.dialogs.CloseDialogsSignal;
import rotmg.dialogs.FlushPopupStartupQueueSignal;
import rotmg.dialogs.OpenDialogSignal;
import rotmg.dialogs.model.DialogsModel;
import rotmg.events.ReconnectEvent;
import rotmg.logging.LoopMonitor;
import rotmg.maploading.signals.HideMapLoadingSignal;
import rotmg.maploading.signals.ShowLoadingViewSignal;
import rotmg.model.GameInitData;
import rotmg.news.controller.NewsButtonRefreshSignal;
import rotmg.objects.Player;
import rotmg.packages.control.BeginnersPackageAvailableSignal;
import rotmg.packages.control.InitPackagesSignal;
import rotmg.packages.control.OpenPackageSignal;
import rotmg.packages.control.PackageAvailableSignal;
import rotmg.packages.models.PackageInfo;
import rotmg.packages.services.PackageModel;
import rotmg.pets.controller.ShowPetTooltip;
import rotmg.promotions.model.BeginnersPackageModel;
import rotmg.promotions.signals.ShowBeginnersPackageSignal;
import rotmg.signals.GameClosedSignal;
import rotmg.signals.PlayGameSignal;
import rotmg.signals.SetWorldInteractionSignal;
import rotmg.ui.popups.signals.CloseAllPopupsSignal;
import rotmg.ui.popups.signals.ShowPopupSignal;
import rotmg.ui.signals.HUDModelInitialized;
import rotmg.ui.signals.HUDSetupStarted;
import rotmg.ui.signals.UpdateHUDSignal;

import static flash.utils.timer.getTimer.getTimer;


public class GameSpriteMediator extends Mediator {

	public GameSprite view;

	public SetWorldInteractionSignal setWorldInteraction;

	public InvalidateDataSignal invalidate;

	public SetScreenWithValidDataSignal setScreenWithValidData;

	public SetScreenSignal setScreen;

	public PlayGameSignal playGame;

	public PlayerModel playerModel;

	public GameClosedSignal gameClosed;

	public MapModel mapModel;

	public BeginnersPackageModel beginnersPackageModel;

	public CloseDialogsSignal closeDialogs;

	public LoopMonitor monitor;

	public HUDSetupStarted hudSetupStarted;

	public UpdateHUDSignal updateHUDSignal;

	public HUDModelInitialized hudModelInitialized;

	public TrackPageViewSignal tracking;

	public BeginnersPackageAvailableSignal beginnersPackageAvailable;

	public PackageAvailableSignal packageAvailable;

	public InitPackagesSignal initPackages;

	public ShowBeginnersPackageSignal showBeginnersPackage;

	public PackageModel packageModel;

	public OpenPackageSignal openPackageSignal;

	public ShowPetTooltip showPetTooltip;

	public ShowLoadingViewSignal showLoadingViewSignal;

	public NewsButtonRefreshSignal newsButtonRefreshSignal;

	public OpenDialogSignal openDialog;

	public DialogsModel dialogsModel;

	public ShowDailyCalendarPopupSignal showDailyCalendarSignal;

	public AddPopupToStartupQueueSignal addToQueueSignal;

	public FlushPopupStartupQueueSignal flushQueueSignal;

	public CloseAllPopupsSignal closeAllPopups;

	public ShowPopupSignal showPopupSignal;

	public GameSpriteMediator() {
		super();
	}

	public static void sleepForMs(int param1) {
		int loc2 = getTimer();
		while (getTimer() - loc2 < param1) {
		}
	}

	@Override
	public void initialize() {
		this.showLoadingViewSignal.dispatch();
		this.view.packageModel = this.packageModel;
		this.setWorldInteraction.add(new SignalConsumer<>(this::onSetWorldInteraction));
		addViewListener(ReconnectEvent.RECONNECT, new EventConsumer<>(this::onReconnect));
		this.view.modelInitialized.add(new SignalConsumer<>(this::onGameSpriteModelInitialized));
		this.view.drawCharacterWindow.add(new SignalConsumer<>(this::onStatusPanelDraw));
		this.hudModelInitialized.add(new SignalConsumer<>(this::onHUDModelInitialized));
		this.showPetTooltip.add(new SignalConsumer<>(this::onShowPetTooltip));
		//this.rotmg.view.monitor.add(new SignalConsumer<>(this::onMonitor));
		this.view.closed.add(new SignalConsumer<>(this::onClosed));
		this.view.mapModel = this.mapModel;
		this.view.dialogsModel = this.dialogsModel;
		this.view.beginnersPackageModel = this.beginnersPackageModel;
		this.view.openDialog = this.openDialog;
		this.view.addToQueueSignal = this.addToQueueSignal;
		this.view.flushQueueSignal = this.flushQueueSignal;
		this.view.connect();
		this.tracking.dispatch("/gameStarted");
		this.view.showBeginnersPackage = this.showBeginnersPackage;
		this.view.openDailyCalendarPopupSignal = this.showDailyCalendarSignal;
		this.view.showPackage.add(new SignalConsumer<>(this::onShowPackage));
		this.newsButtonRefreshSignal.add(new SignalConsumer<>(this::onNewsButtonRefreshSignal));
	}


	private void onShowPackage() {
		PackageInfo loc1 = this.packageModel.startupPackage();
		/**if (loc1) {
		 this.showPopupSignal.dispatch(new StartupPackage(loc1));
		 } else {*/
		this.flushQueueSignal.dispatch();
		/*}*/
	}


	//@Override from Mediator
	public void destroy() {
		this.view.showPackage.remove(new SignalConsumer<>(this::onShowPackage));
		this.setWorldInteraction.remove(new SignalConsumer<>(this::onSetWorldInteraction));
		removeViewListener(ReconnectEvent.RECONNECT, new SignalConsumer<>(this::onReconnect));
		this.view.modelInitialized.remove(new SignalConsumer<>(this::onGameSpriteModelInitialized));
		this.view.drawCharacterWindow.remove(new SignalConsumer<>(this::onStatusPanelDraw));
		this.hudModelInitialized.remove(new SignalConsumer<>(this::onHUDModelInitialized));
		this.beginnersPackageAvailable.remove(new SignalConsumer<>(this::onBeginner));
		this.packageAvailable.remove(new SignalConsumer<>(this::onPackage));
		this.view.closed.remove(new SignalConsumer<>(this::onClosed));
		//this.rotmg.view.monitor.remove(new SignalConsumer<>(this::onMonitor));
		this.newsButtonRefreshSignal.remove(new SignalConsumer<>(this::onNewsButtonRefreshSignal));
		this.view.disconnect();
	}


	private void onMonitor(String param1, int param2) {
		this.monitor.recordTime(param1, param2);
	}

	public void onSetWorldInteraction(boolean param1) {
		this.view.mui.setEnablePlayerInput(param1);
	}

	private void onBeginner() {
		this.view.showBeginnersButtonIfSafe();
	}

	private void onPackage() {
		this.view.showPackageButtonIfSafe();
	}

	private void onClosed() {
		if (!this.view.isEditor) {
			this.gameClosed.dispatch();
		}
		this.closeDialogs.dispatch();
		this.closeAllPopups.dispatch();
		HideMapLoadingSignal loc1 = HideMapLoadingSignal.getInstance();
		loc1.dispatch();
		sleepForMs(100);
	}

	private void onReconnect(ReconnectEvent param1) {
		if (this.view.isEditor) {
			return;
		}
		GameInitData loc2 = new GameInitData();
		loc2.server = param1.server;
		loc2.gameId = param1.gameId;
		loc2.createCharacter = param1.createCharacter;
		loc2.charId = param1.charId;
		loc2.keyTime = param1.keyTime;
		loc2.key = param1.key;
		loc2.isFromArena = param1.isFromArena;
		this.playGame.dispatch(loc2);
	}

	private void onGameSpriteModelInitialized() {
		this.hudSetupStarted.dispatch(this.view);
		this.beginnersPackageAvailable.add(new SignalConsumer<>(this::onBeginner));
		this.packageAvailable.add(new SignalConsumer<>(this::onPackage));
		this.initPackages.dispatch();
	}

	private void onStatusPanelDraw(Player param1) {
		this.updateHUDSignal.dispatch(param1);
	}

	private void onHUDModelInitialized() {
		this.view.hudModelInitialized();
	}

	private void onShowPetTooltip(boolean param1) {
		this.view.showPetToolTip(param1);
	}

	private void onNewsButtonRefreshSignal() {
		this.view.refreshNewsUpdateButton();
	}
}

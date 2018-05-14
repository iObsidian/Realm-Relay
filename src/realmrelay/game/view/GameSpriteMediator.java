package realmrelay.game.view;

import realmrelay.game.GameSprite;
import realmrelay.game.core.model.MapModel;
import realmrelay.game.core.model.PlayerModel;
import realmrelay.game.core.signals.InvalidateDataSignal;
import realmrelay.game.core.signals.SetScreenSignal;
import realmrelay.game.core.signals.SetScreenWithValidDataSignal;
import realmrelay.game.core.signals.TrackPageViewSignal;
import realmrelay.game.dialogs.CloseDialogsSignal;
import realmrelay.game.dialogs.OpenDialogSignal;
import realmrelay.game.events.ReconnectEvent;
import realmrelay.game.model.GameInitData;
import realmrelay.game.objects.Player;
import realmrelay.game.promotions.model.BeginnersPackageModel;
import realmrelay.game.signals.GameClosedSignal;
import realmrelay.game.signals.PlayGameSignal;
import realmrelay.game.signals.SetWorldInteractionSignal;
import realmrelay.game.ui.signals.HUDModelInitialized;
import realmrelay.game.ui.signals.HUDSetupStarted;
import realmrelay.game.ui.signals.UpdateHUDSignal;

public class GameSpriteMediator {

	public GameSprite view;

	public SetWorldInteractionSignal setWorldInteraction;

	public InvalidateDataSignal invalidate;

	public SetScreenWithValidDataSignal setScreenWithValidData;

	public SetScreenSignal setScreen;

	public PlayGameSignal playGame;

	public PlayerModel playerModel;

	public GameClosedSignal gameClosed;

	public MapModel mapModel;

	//public BeginnersPackageModel beginnersPackageModel;

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

	//@Override from Mediator
	public void initialize() {
		this.showLoadingViewSignal.dispatch();
		this.view.packageModel = this.packageModel;
		this.setWorldInteraction.add(this::onSetWorldInteraction);
		addViewListener(ReconnectEvent.RECONNECT, this::onReconnect);
		this.view.modelInitialized.add(this::onGameSpriteModelInitialized);
		this.view.drawCharacterWindow.add(this::onStatusPanelDraw);
		this.hudModelInitialized.add(this::onHUDModelInitialized);
		this.showPetTooltip.add(this::onShowPetTooltip);
		this.view.monitor.add(this::onMonitor);
		this.view.closed.add(this::onClosed);
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
		this.view.showPackage.add(this.onShowPackage);
		this.newsButtonRefreshSignal.add(this.onNewsButtonRefreshSignal);
	}

	private void onShowPackage() {
		PackageInfo loc1 = this.packageModel.startupPackage();
		if (loc1) {
			this.showPopupSignal.dispatch(new StartupPackage(loc1));
		} else {
			this.flushQueueSignal.dispatch();
		}
	}


	//@Override from Mediator
	public void destroy() {
		this.view.showPackage.remove(this::onShowPackage);
		this.setWorldInteraction.remove(this::onSetWorldInteraction);
		removeViewListener(ReconnectEvent.RECONNECT, this::onReconnect);
		this.view.modelInitialized.remove(this::onGameSpriteModelInitialized);
		this.view.drawCharacterWindow.remove(this::onStatusPanelDraw);
		this.hudModelInitialized.remove(this::onHUDModelInitialized);
		this.beginnersPackageAvailable.remove(this::onBeginner);
		this.packageAvailable.remove(this::onPackage);
		this.view.closed.remove(this::onClosed);
		this.view.monitor.remove(this::onMonitor);
		this.newsButtonRefreshSignal.remove(this::onNewsButtonRefreshSignal);
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
		HideMapLoadingSignal loc1 = StaticInjectorContext.getInjector().getInstance(HideMapLoadingSignal);
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
		this.beginnersPackageAvailable.add(this.onBeginner);
		this.packageAvailable.add(this.onPackage);
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

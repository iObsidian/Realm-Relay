package rotmg.ui;

import robotlegs.bender.framework.api.IConfig;

public class UIConfig implements IConfig {
	@Override
	public void configure() {

	}
/*

	public Injector injector = Injector.getInstance();


	public IMediatorMap mediatorMap = IMediatorMap.getInstance();


	public ISignalCommandMap commandMap = ISignalCommandMap.getInstance();


	public ApplicationSetup setup = ApplicationSetup.getInstance();


	public  StartupSequence startup = StartupSequence.getInstance();

	public  UIConfig()  {
		super();
	}

	public void  configure()  {
		this.injector.map(NameChangedSignal.class).asSingleton();
		this.injector.map(PotionInventoryModel.class).asSingleton();
		this.injector.map(UpdatePotionInventorySignal.class).asSingleton();
		this.injector.map(UpdateBackpackTabSignal.class).asSingleton();
		this.injector.map(StatsUndockedSignal.class).asSingleton();
		this.injector.map(StatsDockedSignal.class).asSingleton();
		this.injector.map(StatsTabHotKeyInputSignal.class).asSingleton();
		this.injector.map(IconButtonFactory.class).asSingleton();
		this.injector.map(ImageFactory.class).asSingleton();
		this.injector.map(ShowPopupSignal.class).asSingleton();
		this.injector.map(ClosePopupSignal.class).asSingleton();
		this.injector.map(CloseCurrentPopupSignal.class).asSingleton();
		this.injector.map(ClosePopupByClassSignal.class).asSingleton();
		this.injector.map(CloseAllPopupsSignal.class).asSingleton();
		this.injector.map(ShowLockFade.class).asSingleton();
		this.injector.map(RemoveLockFade.class).asSingleton();
		this.injector.map(CharactersMetricsTracker.class).asSingleton();
		this.injector.map(FameTracker.class).asSingleton();
		this.injector.map(CharactersMetricsTracker.class).asSingleton();
		this.injector.map(FameTracker.class).asSingleton();
		this.commandMap.map(ShowLoadingUISignal.class).toCommand(ShowLoadingUICommand);
		this.commandMap.map(ShowTitleUISignal.class).toCommand(ShowTitleUICommand);
		this.commandMap.map(ChooseNameSignal.class).toCommand(ChooseNameCommand);
		this.commandMap.map(EnterGameSignal.class).toCommand(EnterGameCommand);
		this.mediatorMap.map(LoadingScreen.class).toMediator(LoadingMediator);
		this.mediatorMap.map(ServersScreen.class).toMediator(ServersMediator);
		this.mediatorMap.map(CreditsScreen.class).toMediator(CreditsMediator);
		this.mediatorMap.map(CharacterSelectionAndNewsScreen.class).toMediator(CurrentCharacterMediator);
		this.mediatorMap.map(AccountInfoView.class).toMediator(AccountInfoMediator);
		this.mediatorMap.map(AccountScreen.class).toMediator(AccountScreenMediator);
		this.mediatorMap.map(TitleView.class).toMediator(TitleMediator);
		this.mediatorMap.map(NewCharacterScreen.class).toMediator(NewCharacterMediator);
		this.mediatorMap.map(MapEditor.class).toMediator(MapEditorMediator);
		this.mediatorMap.map(CurrentCharacterRect.class).toMediator(CurrentCharacterRectMediator);
		this.mediatorMap.map(CharacterRectList.class).toMediator(CharacterRectListMediator);
		this.mediatorMap.map(ErrorDialog.class).toMediator(ErrorDialogMediator);
		this.mediatorMap.map(GraveyardLine.class).toMediator(NewsLineMediator);
		this.mediatorMap.map(NotEnoughGoldDialog.class).toMediator(NotEnoughGoldMediator);
		this.mediatorMap.map(InteractPanel.class).toMediator(InteractPanelMediator);
		this.mediatorMap.map(TextPanel.class).toMediator(TextPanelMediator);
		this.mediatorMap.map(ItemGrid.class).toMediator(ItemGridMediator);
		this.mediatorMap.map(ChooseNameRegisterDialog.class).toMediator(ChooseNameRegisterMediator);
		this.mediatorMap.map(CharacterSlotRegisterDialog.class).toMediator(CharacterSlotRegisterMediator);
		this.mediatorMap.map(RegisterPromptDialog.class).toMediator(RegisterPromptDialogMediator);
		this.mediatorMap.map(CharacterSlotNeedGoldDialog.class).toMediator(CharacterSlotNeedGoldMediator);
		this.mediatorMap.map(NameChangerPanel.class).toMediator(NameChangerPanelMediator);
		this.mediatorMap.map(GuildRegisterPanel.class).toMediator(GuildRegisterPanelMediator);
		this.mediatorMap.map(ChooseNameFrame.class).toMediator(ChooseNameFrameMediator);
		this.mediatorMap.map(CreateGuildFrame.class).toMediator(CreateGuildFrameMediator);
		this.mediatorMap.map(NewChooseNameFrame.class).toMediator(NewChooseNameFrameMediator);
		this.mediatorMap.map(PlayerGroupMenu.class).toMediator(PlayerGroupMenuMediator);
		this.mediatorMap.map(AgeVerificationDialog.class).toMediator(AgeVerificationMediator);
		this.mediatorMap.map(LanguageOptionOverlay.class).toMediator(LanguageOptionOverlayMediator);
		this.mediatorMap.map(ArenaPortalPanel.class).toMediator(ArenaPortalPanelMediator);
		this.mediatorMap.map(StatMetersView.class).toMediator(StatMetersMediator);
		this.mediatorMap.map(HUDView.class).toMediator(HUDMediator);
		this.mediatorMap.map(PotionSlotView.class).toMediator(PotionSlotMediator);
		this.mediatorMap.map(ResurrectionView.class).toMediator(ResurrectionViewMediator);
		this.mediatorMap.map(GameObjectArrow.class).toMediator(GameObjectArrowMediator);
		this.mediatorMap.map(UnFocusAble.class).toMediator(UnFocusAbleMediator);
		this.mediatorMap.map(ShopPopupView.class).toMediator(ShopPopupMediator);
		this.mediatorMap.map(FameContentPopup.class).toMediator(FameContentPopupMediator);
		this.mediatorMap.map(MysteryBoxTile.class).toMediator(MysteryBoxTileMediator);
		this.mediatorMap.map(PackageBoxTile.class).toMediator(PackageBoxTileMediator);
		this.mediatorMap.map(doubleSpinner.class).toMediator(doubleSpinnerMediator);
		this.mediatorMap.map(MysteryBoxContentPopup.class).toMediator(MysteryBoxContentPopupMediator);
		this.mediatorMap.map(PackageBoxContentPopup.class).toMediator(PackageBoxContentPopupMediator);
		this.mediatorMap.map(ItemBo.classx).toMediator(ItemBoxMediator);
		this.mediatorMap.map(SlotBox.class).toMediator(SlotBoxMediator);
		this.mediatorMap.map(UIScrollbar.class).toMediator(UIScrollbarMediator);
		this.mediatorMap.map(UIItemContainer.class).toMediator(UIItemContainerMediator);
		this.mediatorMap.map(StatsLine.class).toMediator(FameStatsLineMediator);
		this.mediatorMap.map(UITab.class).toMediator(UITabMediator);
		this.mediatorMap.map(PopupView.class).toMediator(PopupMediator);
		this.mediatorMap.map(ModalPopup.class).toMediator(ModalPopupMediator);
		this.mediatorMap.map(BuyGoldButton.class).toMediator(BuyGoldButtonMediator);
		this.mediatorMap.map(ClosePopupButton.class).toMediator(CancelButtonMediator);
		this.mediatorMap.map(ConfirmationModal.class).toMediator(ConfirmationModalMediator);
		this.mediatorMap.map(MysteryBoxRollModal.class).toMediator(MysteryBoxRollModalMediator);
		this.mediatorMap.map(StartupPackage.class).toMediator(StartupPackageMediator);
		TextureParser.class.instance;
		this.setupKeyUI();
		this.mapNoServersDialogFactory();
		this.setupCharacterWindow();
		this.startup.addSignal(ShowLoadingUISignal, -1);
		this.startup.addTask(LoadAccountTask);
		this.startup.addTask(GetCharListTask);
		this.startup.addTask(FetchPlayerCalendarTask);
		this.startup.addTask(GetInGameNewsTask);
		this.startup.addSignal(ShowTitleUISignal, StartupSequence.LAST);
	}

	private void  setupKeyUI()  {
		this.injector.map(ShowKeySignal).toValue(new ShowKeySignal());
		this.injector.map(HideKeySignal).toValue(new HideKeySignal());
		this.commandMap.map(ShowHideKeyUISignal).toCommand(ShowHideKeyUICommand);
		this.commandMap.map(RefreshScreenAfterLoginSignal).toCommand(RefreshScreenAfterLoginCommand);
		this.mediatorMap.map(KeysView).toMediator(KeysMediator);
	}

	private void  mapNoServersDialogFactory()  {
		if (this.setup.useProductionDialogs()) {
			this.injector.map(NoServersDialogFactory).toSingleton(ProductionNoServersDialogFactory);
		} else {
			this.injector.map(NoServersDialogFactory).toSingleton(TestingNoServersDialogFactory);
		}
	}

	private void  setupCharacterWindow()  {
		this.injector.map(HUDModel).asSingleton();
		this.injector.map(UpdateHUDSignal).asSingleton();
		this.injector.map(HUDModelInitialized).asSingleton();
		this.commandMap.map(HUDSetupStarted).toCommand(HUDInitCommand);
		this.mediatorMap.map(CharacterDetailsView).toMediator(CharacterDetailsMediator);
	}*/


}

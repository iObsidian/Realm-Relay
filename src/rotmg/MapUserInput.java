package rotmg.game;

import alde.flash.utils.XML;
import flash.events.Event;
import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import flash.system.Capabilities;
import javafx.stage.Stage;
import net.hires.debug.Stats;
import rotmg.GameSprite;
import rotmg.MiniMapZoomSignal;
import rotmg.api.ApplicationSetup;
import rotmg.chat.model.ChatMessage;
import rotmg.constants.GeneralConstants;
import rotmg.constants.UseType;
import rotmg.dialogs.CloseDialogsSignal;
import rotmg.dialogs.OpenDialogSignal;
import rotmg.game.ui.UIUtils;
import rotmg.messaging.GameServerConnection;
import rotmg.model.PotionInventoryModel;
import rotmg.objects.GameObject;
import rotmg.objects.ObjectLibrary;
import rotmg.objects.Player;
import rotmg.objects.Square;
import rotmg.parameters.Parameters;
import rotmg.pets.controller.reskin.ReskinPetFlowStartSignal;
import rotmg.signals.*;
import rotmg.tutorial.Tutorial;
import rotmg.ui.model.TabStripModel;
import rotmg.ui.popups.signals.ClosePopupByClassSignal;
import rotmg.ui.popups.signals.ShowPopupSignal;
import rotmg.util.KeyCodes;
import rotmg.util.TextureRedrawer;
import rotmg.view.components.StatsTabHotKeyInputSignal;



public class MapUserInput {

	private static Stats stats = new Stats();

	private static final int MOUSE_DOWN_WAIT_PERIOD = 175;

	private static boolean arrowWarning = false;


	public GameSprite gs;

	private boolean moveLeft = false;

	private boolean moveRight = false;

	private boolean moveUp = false;

	private boolean moveDown = false;

	private boolean rotateLeft = false;

	private boolean rotateRight = false;

	private boolean mouseDown = false;

	private boolean autofire = false;

	private String currentString = "";

	private boolean specialKeyDown = false;

	private boolean enablePlayerInput = true;

	private GiftStatusUpdateSignal giftStatusUpdateSignal;

	private AddTextLineSignal addTextLine;

	private SetTextBoxVisibilitySignal setTextBoxVisibility;

	private StatsTabHotKeyInputSignal statsTabHotKeyInputSignal;

	private MiniMapZoomSignal miniMapZoom;

	private UseBuyPotionSignal useBuyPotionSignal;

	private PotionInventoryModel potionInventoryModel;

	private OpenDialogSignal openDialogSignal;

	private CloseDialogsSignal closeDialogSignal;

	private ClosePopupByClassSignal closePopupByClassSignal;

	private TabStripModel tabStripModel;

	private Layers layers;

	private ExitGameSignal exitGame;

	private boolean areFKeysAvailable;

	private ReskinPetFlowStartSignal reskinPetFlowStart;

	public MapUserInput(GameSprite param1) {
		super();
		this.gs = param1;
		this.gs.addEventListener(Event.ADDED_TO_STAGE, this::onAddedToStage);
		this.gs.addEventListener(Event.REMOVED_FROM_STAGE, this::onRemovedFromStage);
		this.giftStatusUpdateSignal = GiftStatusUpdateSignal.getInstance();
		this.reskinPetFlowStart = ReskinPetFlowStartSignal.getInstance();
		this.addTextLine = AddTextLineSignal.getInstance();
		this.setTextBoxVisibility = SetTextBoxVisibilitySignal.getInstance();
		this.miniMapZoom = MiniMapZoomSignal.getInstance();
		this.useBuyPotionSignal = UseBuyPotionSignal.getInstance();
		this.potionInventoryModel = PotionInventoryModel.getInstance();
		this.tabStripModel = TabStripModel.getInstance();
		this.layers = Layers.getInstance();
		this.statsTabHotKeyInputSignal = StatsTabHotKeyInputSignal.getInstance();
		this.exitGame = ExitGameSignal.getInstance();
		this.openDialogSignal = OpenDialogSignal.getInstance();
		this.closeDialogSignal = CloseDialogsSignal.getInstance();
		this.closePopupByClassSignal = ClosePopupByClassSignal.getInstance();
		ApplicationSetup loc3 = ApplicationSetup.getInstance();
		this.areFKeysAvailable = loc3.areDeveloperHotkeysEnabled();
		this.gs.map.signalRenderSwitch.add(this::onRenderSwitch);
	}

	public void onRenderSwitch(boolean param1) {
		if (param1) {
			this.gs.stage.removeEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			this.gs.stage.removeEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
			this.gs.map.addEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			this.gs.map.addEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
		} else {
			this.gs.map.removeEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			this.gs.map.removeEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
			this.gs.stage.addEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			this.gs.stage.addEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
		}
	}

	public void clearInput() {
		this.moveLeft = false;
		this.moveRight = false;
		this.moveUp = false;
		this.moveDown = false;
		this.rotateLeft = false;
		this.rotateRight = false;
		this.mouseDown = false;
		this.autofire = false;
		this.setPlayerMovement();
	}

	public void setEnablePlayerInput(boolean param1) {
		if (this.enablePlayerInput != param1) {
			this.enablePlayerInput = param1;
			this.clearInput();
		}
	}

	private void onAddedToStage(Event param1) {
		Stage loc2 = this.gs.stage;
		loc2.addEventListener(Event.ACTIVATE, this::onActivate);
		loc2.addEventListener(Event.DEACTIVATE, this::onDeactivate);
		loc2.addEventListener(KeyboardEvent.KEY_DOWN, this::onKeyDown);
		loc2.addEventListener(KeyboardEvent.KEY_UP, this::onKeyUp);
		loc2.addEventListener(MouseEvent.MOUSE_WHEEL, this::onMouseWheel);
		if (Parameters.isGpuRender()) {
			loc2.addEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			loc2.addEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
		} else {
			this.gs.map.addEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			this.gs.map.addEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
		}
		loc2.addEventListener(Event.ENTER_FRAME, this::onEnterFrame);
		loc2.addEventListener(MouseEvent.RIGHT_CLICK, this::disableRightClick);
	}

	public void disableRightClick(MouseEvent param1) {
	}

	private void onRemovedFromStage(Event param1) {
		Stage loc2 = this.gs.stage;
		loc2.removeEventListener(Event.ACTIVATE, this::onActivate);
		loc2.removeEventListener(Event.DEACTIVATE, this::onDeactivate);
		loc2.removeEventListener(KeyboardEvent.KEY_DOWN, this::onKeyDown);
		loc2.removeEventListener(KeyboardEvent.KEY_UP, this::onKeyUp);
		loc2.removeEventListener(MouseEvent.MOUSE_WHEEL, this::onMouseWheel);
		if (Parameters.isGpuRender()) {
			loc2.removeEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			loc2.removeEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
		} else {
			this.gs.map.removeEventListener(MouseEvent.MOUSE_DOWN, this::onMouseDown);
			this.gs.map.removeEventListener(MouseEvent.MOUSE_UP, this::onMouseUp);
		}
		loc2.removeEventListener(Event.ENTER_FRAME, this::onEnterFrame);
		loc2.removeEventListener(MouseEvent.RIGHT_CLICK, this::disableRightClick);
	}

	private void onActivate(Event param1) {
	}

	private void onDeactivate(Event param1) {
		this.clearInput();
	}

	public void onMouseDown(MouseEvent param1) {
		double loc3 = 0;
		int loc4 = 0;
		XML loc5 = null;
		double loc6 = 0;
		double loc7 = 0;
		Player loc2 = this.gs.map.player;
		if (loc2 == null) {
			return;
		}
		if (!this.enablePlayerInput) {
			return;
		}
		if (param1.shiftKey) {
			loc4 = loc2.equipment[1];
			if (loc4 == -1) {
				return;
			}
			loc5 = ObjectLibrary.xmlLibrary[loc4];
			if (loc5 == null || loc5.hasOwnProperty("EndMpCost")) {
				return;
			}
			if (loc2.isUnstable()) {
				loc6 = Math.random() * 600 - 300;
				loc7 = Math.random() * 600 - 325;
			} else {
				loc6 = this.gs.map.mouseX;
				loc7 = this.gs.map.mouseY;
			}
			if (Parameters.isGpuRender()) {
				if (param1.currentTarget == param1.target || param1.target == this.gs.map || param1.target == this.gs) {
					loc2.useAltWeapon(loc6, loc7, UseType.START_USE);
				}
			} else {
				loc2.useAltWeapon(loc6, loc7, UseType.START_USE);
			}
			return;
		}
		if (Parameters.isGpuRender()) {
			if (param1.currentTarget == param1.target || param1.target == this.gs.map || param1.target == this.gs || param1.currentTarget == this.gs.chatBox.list) {
				loc3 = Math.atan2(this.gs.map.mouseY, this.gs.map.mouseX);
			} else {
				return;
			}
		} else {
			loc3 = Math.atan2(this.gs.map.mouseY, this.gs.map.mouseX);
		}
		doneAction(this.gs, Tutorial.ATTACK_ACTION);
		if (loc2.isUnstable()) {
			loc2.attemptAttackAngle(Math.random() * 360);
		} else {
			loc2.attemptAttackAngle(loc3);
		}
		this.mouseDown = true;
	}

	public void onMouseUp(MouseEvent param1) {
		this.mouseDown = false;
		Player loc2 = this.gs.map.player;
		if (loc2 == null) {
			return;
		}
		loc2.isShooting = false;
	}

	private void onMouseWheel(MouseEvent param1) {
		if (param1.delta > 0) {
			this.miniMapZoom.dispatch(MiniMapZoomSignal.IN);
		} else {
			this.miniMapZoom.dispatch(MiniMapZoomSignal.OUT);
		}
	}

	private void onEnterFrame(Event param1) {
		Player loc2 = null;
		double loc3 = 0;
		doneAction(this.gs, Tutorial.UPDATE_ACTION);
		if (this.enablePlayerInput && (this.mouseDown || this.autofire)) {
			loc2 = this.gs.map.player;
			if (loc2 != null) {
				if (loc2.isUnstable()) {
					loc2.attemptAttackAngle(Math.random() * 360);
				} else {
					loc3 = Math.atan2(this.gs.map.mouseY, this.gs.map.mouseX);
					loc2.attemptAttackAngle(loc3);
				}
			}
		}
	}

	private void onKeyDown(KeyboardEvent param1) {
		CloseAllPopupsSignal loc4 = null;
		AddTextLineSignal loc5 = null;
		ChatMessage loc6 = null;
		GameObject loc7 = null;
		double loc8 = 0;
		double loc9 = 0;
		boolean loc10 = false;
		ShowPopupSignal loc11 = null;
		FriendModel loc12 = null;
		OpenDialogSignal loc13 = null;
		Square loc14 = null;
		Stage loc2 = this.gs.stage;
		this.currentString = this.currentString + String.fromCharCode(param1.keyCode).toLowerCase();
		if (this.currentString.equals(UIUtils.EXPERIMENTAL_MENU_PASSWORD.substring(0, this.currentString.length()))) {
			if (this.currentString.length() == UIUtils.EXPERIMENTAL_MENU_PASSWORD.length()) {
				loc5 = AddTextLineSignal.getInstance();
				loc6 = new ChatMessage();
				loc6.name = Parameters.SERVER_CHAT_NAME;
				this.currentString = "";
				UIUtils.SHOW_EXPERIMENTAL_MENU = !UIUtils.SHOW_EXPERIMENTAL_MENU;
				loc6.text = !!UIUtils.SHOW_EXPERIMENTAL_MENU ? "Experimental menu activated" : "Experimental menu deactivated";
				loc5.dispatch(loc6);
			}
		} else {
			this.currentString = "";
		}
		switch (param1.keyCode) {
			case KeyCodes.F1:
			case KeyCodes.F2:
			case KeyCodes.F3:
			case KeyCodes.F4:
			case KeyCodes.F5:
			case KeyCodes.F6:
			case KeyCodes.F7:
			case KeyCodes.F8:
			case KeyCodes.F9:
			case KeyCodes.F10:
			case KeyCodes.F11:
			case KeyCodes.F12:
			case KeyCodes.INSERT:
			case KeyCodes.ALTERNATE:
				break;
			default:
				if (loc2.focus != null) {
					return;
				}
				break;
		}
		Player loc3 = this.gs.map.player;
		switch (param1.keyCode) {
			case Parameters.data.moveUp:
				doneAction(this.gs, Tutorial.MOVE_FORWARD_ACTION);
				this.moveUp = true;
				break;
			case Parameters.data.moveDown:
				doneAction(this.gs, Tutorial.MOVE_BACKWARD_ACTION);
				this.moveDown = true;
				break;
			case Parameters.data.moveLeft:
				doneAction(this.gs, Tutorial.MOVE_LEFT_ACTION);
				this.moveLeft = true;
				break;
			case Parameters.data.moveRight:
				doneAction(this.gs, Tutorial.MOVE_RIGHT_ACTION);
				this.moveRight = true;
				break;
			case Parameters.data.rotateLeft:
				if (!Parameters.data.allowRotation) {
					break;
				}
				doneAction(this.gs, Tutorial.ROTATE_LEFT_ACTION);
				this.rotateLeft = true;
				break;
			case Parameters.data.rotateRight:
				if (!Parameters.data.allowRotation) {
					break;
				}
				doneAction(this.gs, Tutorial.ROTATE_RIGHT_ACTION);
				this.rotateRight = true;
				break;
			case Parameters.data.resetToDefaultCameraAngle:
				Parameters.data.cameraAngle = Parameters.data.defaultCameraAngle;
				Parameters.save();
				break;
			case Parameters.data.useSpecial:
				loc7 = this.gs.map.player;
				if (loc7 == null) {
					break;
				}
				if (!this.specialKeyDown) {
					if (loc3.isUnstable()) {
						loc8 = Math.random() * 600 - 300;
						loc9 = Math.random() * 600 - 325;
					} else {
						loc8 = this.gs.map.mouseX;
						loc9 = this.gs.map.mouseY;
					}
					loc10 = loc3.useAltWeapon(loc8, loc9, UseType.START_USE);
					if (loc10) {
						this.specialKeyDown = true;
					}
				}
				break;
			case Parameters.data.autofireToggle:
				this.gs.map.player.isShooting = this.autofire = !this.autofire;
				break;
			case Parameters.data.toggleHPBar:
				Parameters.data.HPBar = Parameters.data.HPBar != 0 ? 0 : 1;
				break;
			case Parameters.data.toggleProjectiles:
				Parameters.data.disableAllyParticles = !Parameters.data.disableAllyParticles;
				break;
			case Parameters.data.toggleMasterParticles:
				Parameters.data.noParticlesMaster = !Parameters.data.noParticlesMaster;
				break;
			case Parameters.data.useInvSlot1:
				this.useItem(4);
				break;
			case Parameters.data.useInvSlot2:
				this.useItem(5);
				break;
			case Parameters.data.useInvSlot3:
				this.useItem(6);
				break;
			case Parameters.data.useInvSlot4:
				this.useItem(7);
				break;
			case Parameters.data.useInvSlot5:
				this.useItem(8);
				break;
			case Parameters.data.useInvSlot6:
				this.useItem(9);
				break;
			case Parameters.data.useInvSlot7:
				this.useItem(10);
				break;
			case Parameters.data.useInvSlot8:
				this.useItem(11);
				break;
			case Parameters.data.useHealthPotion:
				if (this.potionInventoryModel.getPotionModel(PotionInventoryModel.HEALTH_POTION_ID).available) {
					this.useBuyPotionSignal.dispatch(new UseBuyPotionVO(PotionInventoryModel.HEALTH_POTION_ID, UseBuyPotionVO.CONTEXTBUY));
				}
				break;
			case Parameters.data.GPURenderToggle:
				Parameters.data.GPURender = !Parameters.data.GPURender;
				break;
			case Parameters.data.useMagicPotion:
				if (this.potionInventoryModel.getPotionModel(PotionInventoryModel.MAGIC_POTION_ID).available) {
					this.useBuyPotionSignal.dispatch(new UseBuyPotionVO(PotionInventoryModel.MAGIC_POTION_ID, UseBuyPotionVO.CONTEXTBUY));
				}
				break;
			case Parameters.data.miniMapZoomOut:
				this.miniMapZoom.dispatch(MiniMapZoomSignal.OUT);
				break;
			case Parameters.data.miniMapZoomIn:
				this.miniMapZoom.dispatch(MiniMapZoomSignal.IN);
				break;
			case Parameters.data.togglePerformanceStats:
				this.togglePerformanceStats();
				break;
			case Parameters.data.escapeToNexus:
			case Parameters.data.escapeToNexus2:
				loc4 = StaticInjectorContext.getInjector().getInstance(CloseAllPopupsSignal);
				loc4.dispatch();
				this.exitGame.dispatch();
				this.gs.gsc.escape();
				Parameters.data.needsRandomRealm = false;
				Parameters.save();
				break;
			case Parameters.data.friendList:
				Parameters.data.friendListDisplayFlag = !Parameters.data.friendListDisplayFlag;
				if (Parameters.data.friendListDisplayFlag) {
					if (Parameters.USE_NEW_FRIENDS_UI) {
						loc11 = StaticInjectorContext.getInjector().getInstance(ShowPopupSignal);
						loc12 = StaticInjectorContext.getInjector().getInstance(FriendModel);
						loc11.dispatch(new FriendsPopupView(loc12.hasInvitations));
					} else {
						loc13 = StaticInjectorContext.getInjector().getInstance(OpenDialogSignal);
						loc13.dispatch(new FriendListView());
					}
				} else {
					this.closeDialogSignal.dispatch();
					this.closePopupByClassSignal.dispatch(FriendsPopupView);
				}
				break;
			case Parameters.data.options:
				loc4 = StaticInjectorContext.getInjector().getInstance(CloseAllPopupsSignal);
				loc4.dispatch();
				this.clearInput();
				this.layers.overlay.addChild(new Options(this.gs));
				break;
			case Parameters.data.toggleCentering:
				Parameters.data.centerOnPlayer = !Parameters.data.centerOnPlayer;
				Parameters.save();
				break;
			case Parameters.data.toggleFullscreen:
				if (Capabilities.playerType == "Desktop") {
					Parameters.data.fullscreenMode = !Parameters.data.fullscreenMode;
					Parameters.save();
					loc2.displayState = !!Parameters.data.fullscreenMode ? "fullScreenInteractive" : StageDisplayState.NORMAL;
				}
				break;
			case Parameters.data.switchTabs:
				loc4 = StaticInjectorContext.getInjector().getInstance(CloseAllPopupsSignal);
				loc4.dispatch();
				this.statsTabHotKeyInputSignal.dispatch();
				break;
			case Parameters.data.interact:
				loc4 = StaticInjectorContext.getInjector().getInstance(CloseAllPopupsSignal);
				loc4.dispatch();
				break;
			case Parameters.data.testOne:
		}
		if (Parameters.ALLOW_SCREENSHOT_MODE) {
			switch (param1.keyCode) {
				case KeyCodes.F2:
					this.toggleScreenShotMode();
					break;
				case KeyCodes.F3:
					Parameters.screenShotSlimMode = !Parameters.screenShotSlimMode;
					break;
				case KeyCodes.F4:
					this.gs.map.mapOverlay.visible = !this.gs.map.mapOverlay.visible;
					this.gs.map.partyOverlay.visible = !this.gs.map.partyOverlay.visible;
			}
		}
		if (this.areFKeysAvailable) {
			switch (param1.keyCode) {
				case KeyCodes.F6:
					TextureRedrawer.clearCache();
					Parameters.projColorType = (Parameters.projColorType + 1) % 7;
					this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, "Projectile Color  Type;
					break;
				case KeyCodes.F7:
					for (loc14:
					     this.gs.map.squares) {
						if (loc14 != null) {
							loc14.faces.length = 0;
						}
					}
					Parameters.blendType = (Parameters.blendType + 1) % 2;
					this.addTextLine.dispatch(ChatMessage.make(Parameters.CLIENT_CHAT_NAME, "Blend  type;
					break;
				case KeyCodes.F8:
					Parameters.data.surveyDate = 0;
					Parameters.data.needsSurvey = true;
					Parameters.data.playTimeLeftTillSurvey = 5;
					Parameters.data.surveyGroup = "testing";
					break;
				case KeyCodes.F9:
					Parameters.drawProj = !Parameters.drawProj;
			}
		}
		this.setPlayerMovement();
	}

	private void onKeyUp(KeyboardEvent param1) {
		double loc2 = 0;
		double loc3 = 0;
		switch (param1.keyCode) {
			case Parameters.data.moveUp:
				this.moveUp = false;
				break;
			case Parameters.data.moveDown:
				this.moveDown = false;
				break;
			case Parameters.data.moveLeft:
				this.moveLeft = false;
				break;
			case Parameters.data.moveRight:
				this.moveRight = false;
				break;
			case Parameters.data.rotateLeft:
				this.rotateLeft = false;
				break;
			case Parameters.data.rotateRight:
				this.rotateRight = false;
				break;
			case Parameters.data.useSpecial:
				if (this.specialKeyDown) {
					this.specialKeyDown = false;
					if (this.gs.map.player.isUnstable()) {
						loc2 = Math.random() * 600 - 300;
						loc3 = Math.random() * 600 - 325;
					} else {
						loc2 = this.gs.map.mouseX;
						loc3 = this.gs.map.mouseY;
					}
					this.gs.map.player.useAltWeapon(this.gs.map.mouseX, this.gs.map.mouseY, UseType.END_USE);
				}
		}
		this.setPlayerMovement();
	}

	private void setPlayerMovement() {
		Player loc1 = this.gs.map.player;
		if (loc1 != null) {
			if (this.enablePlayerInput) {
				loc1.setRelativeMovement((!!this.rotateRight ? 1 : 0) - (!!this.rotateLeft ? 1 : 0), (!!this.moveRight ? 1 : 0) - (!!this.moveLeft ? 1 : 0), (!!this.moveDown ? 1 : 0) - (!!this.moveUp ? 1 : 0));
			} else {
				loc1.setRelativeMovement(0, 0, 0);
			}
		}
	}

	private void useItem(int param1) {
		if (this.tabStripModel.currentSelection.equals(TabStripModel.BACKPACK)) {
			param1 = param1 + GeneralConstants.NUM_INVENTORY_SLOTS;
		}
		GameServerConnection.instance.useItem_new(this.gs.map.player, param1);
	}

	private void togglePerformanceStats() {
		if (this.gs.contains(stats)) {
			this.gs.removeChild(stats);
			this.gs.removeChild(this.gs.gsc.jitterWatcher);
			this.gs.gsc.disableJitterWatcher();
		} else {
			this.gs.addChild(stats);
			this.gs.gsc.enableJitterWatcher();
			this.gs.gsc.jitterWatcher.y = stats.height;
			this.gs.addChild(this.gs.gsc.jitterWatcher);
		}
	}

	private void toggleScreenShotMode() {
		Parameters.screenShotMode = !Parameters.screenShotMode;
		if (Parameters.screenShotMode) {
			this.gs.hudView.visible = false;
			this.setTextBoxVisibility.dispatch(false);
		} else {
			this.gs.hudView.visible = true;
			this.setTextBoxVisibility.dispatch(true);
		}
	}
}
}
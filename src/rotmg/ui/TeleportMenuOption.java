package rotmg.ui;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import flash.airglobal.Shape;
import flash.events.Event;
import flash.events.MouseEvent;
import flash.geom.ColorTransform;
import rotmg.objects.Player;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.menu.MenuOption;
import rotmg.util.AssetLibrary;
import rotmg.util.TextKey;

public class TeleportMenuOption extends MenuOption {

	private static final ColorTransform inactiveCT = new ColorTransform(84 / 255, 84 / 255, 84 / 255);

	private Player player;

	private boolean mouseOver = false;

	private TextFieldDisplayConcrete barText;

	private int barTextOrigWidth;

	private Shape barMask;

	public TeleportMenuOption(Player param1) {
		super(AssetLibrary.getImageFromSet("lofiInterface2", 3), 16777215, TextKey.TELEPORTMENUOPTION_TITLE);
		this.barMask = new Shape();
		this.player = param1;
		this.barText = new TextFieldDisplayConcrete().setSize(18).setColor(16777215);
		this.barText.setBold(true);
		this.barText.setStringBuilder(new LineBuilder().setParams(TextKey.TELEPORTMENUOPTION_TITLE));
		this.barText.x = this.barMask.x = text.x;
		this.barText.y = this.barMask.y = text.y;
		this.barText.textChanged.add(new SignalConsumer<Object>(this::onTextChanged));
		addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemovedFromStage));
	}

	private void onTextChanged() {
		this.barTextOrigWidth = (int) this.barText.textField.width;
		this.barMask.graphics.beginFill(16711935);
		this.barMask.graphics.drawRect(0, 0, this.barText.textField.width, this.barText.textField.height);
	}

	private void onAddedToStage(Event param1) {
		addEventListener(Event.ENTER_FRAME, new EventConsumer<>(this::onEnterFrame));
	}

	private void onRemovedFromStage(Event param1) {
		removeEventListener(Event.ENTER_FRAME, new EventConsumer<>(this::onEnterFrame));
	}

	private void onEnterFrame(Event param1) {
		int loc3 = 0;
		int loc4 = 0;
		int loc2 = this.player.msUtilTeleport();
		if (loc2 > 0) {
			loc3 = loc2 <= Player.MS_BETWEEN_TELEPORT ? Player.MS_BETWEEN_TELEPORT : Player.MS_REALM_TELEPORT;
			if (!contains(this.barText)) {
				addChild(this.barText);
				addChild(this.barMask);
				this.barText.mask = this.barMask;
			}
			loc4 = this.barTextOrigWidth * (1 - loc2 / loc3);
			this.barMask.width = loc4;
			setColorTransform(inactiveCT);
		} else {
			if (contains(this.barText)) {
				removeChild(this.barText);
			}
			if (this.mouseOver) {
				setColorTransform(mouseOverCT);
			} else {
				setColorTransform(null);
			}
		}
	}

	protected void onMouseOver(MouseEvent param1) {
		this.mouseOver = true;
	}

	protected void onMouseOut(MouseEvent param1) {
		this.mouseOver = false;
	}
}

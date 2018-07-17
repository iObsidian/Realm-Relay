package rotmg.ui.panels;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import flash.events.Event;
import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import flash.text.TextFieldAutoSize;
import rotmg.AGameSprite;
import rotmg.objects.GuildHallPortal;
import rotmg.objects.Player;
import rotmg.parameters.Parameters;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.DeprecatedTextButton;
import rotmg.ui.view.SignalWaiter;
import rotmg.util.StageProxy;
import rotmg.util.TextKey;
import spark.filters.DropShadowFilter;

public class GuildHallPortalPanel extends Panel {

	private final SignalWaiter waiter = new SignalWaiter();
	public StageProxy stageProxy;
	private GuildHallPortal owner;
	private TextFieldDisplayConcrete nameText;
	private DeprecatedTextButton enterButton;
	private TextFieldDisplayConcrete noGuildText;

	public GuildHallPortalPanel(AGameSprite param1, GuildHallPortal param2) {
		super(param1);
		Player loc3 = null;
		this.stageProxy = new StageProxy(this);
		this.owner = param2;
		if (gs.map == null || gs.map.player == null) {
			return;
		}
		loc3 = gs.map.player;
		this.nameText = new TextFieldDisplayConcrete().setSize(18).setColor(16777215).setTextWidth(WIDTH).setWordWrap(true).setMultiLine(true).setAutoSize(TextFieldAutoSize.CENTER).setBold(true).setHTML(true);
		this.nameText.setStringBuilder(new LineBuilder().setParams(TextKey.GUILD_HALL_PORTAL_TITLE).setPrefix("<p align=\"center\">").setPostfix("</p>"));
		this.nameText.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		this.nameText.y = 6;
		addChild(this.nameText);
		if (loc3.guildName != null && loc3.guildName.length() > 0) {
			this.enterButton = new DeprecatedTextButton(16, TextKey.PANEL_ENTER);
			this.enterButton.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onEnterSpriteClick));
			addChild(this.enterButton);
			this.waiter.add(this.enterButton.textChanged);
			addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAdded));
		} else {
			this.noGuildText = new TextFieldDisplayConcrete().setSize(18).setColor(16711680).setTextWidth(WIDTH).setAutoSize(TextFieldAutoSize.CENTER).setHTML(true).setBold(true);
			this.noGuildText.setStringBuilder(new LineBuilder().setParams(TextKey.GUILD_HALL_PORTAL_NO_GUILD).setPrefix("<p align=\"center\">").setPostfix("</p>"));
			this.noGuildText.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
			this.waiter.add(this.noGuildText.textChanged);
			addChild(this.noGuildText);
		}
		this.waiter.complete.addOnce(new SignalConsumer<>(this::alignUI));
	}

	private void alignUI() {
		if (this.noGuildText != null) {
			this.noGuildText.y = HEIGHT - this.noGuildText.height - 12;
		}
		if (this.enterButton != null) {
			this.enterButton.x = WIDTH / 2 - this.enterButton.width / 2;
			this.enterButton.y = HEIGHT - this.enterButton.height - 4;
		}
	}

	private void onAdded(Event param1) {
		this.stageProxy.addEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemoved));
	}

	private void onRemoved(Event param1) {
		this.stageProxy.removeEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
	}

	private void onEnterSpriteClick(MouseEvent param1) {
		this.enterPortal();
	}

	private void onKeyDown(KeyboardEvent param1) {
		if (param1.keyCode == Parameters.data.interact && stage.focus == null) {
			this.enterPortal();
		}
	}

	private void enterPortal() {
		gs.gsc.usePortal(this.owner.objectId);
	}
}

package rotmg.view;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import flash.display.Sprite;
import flash.events.Event;
import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import flash.text.TextFormatAlign;
import org.osflash.signals.Signal;
import rotmg.GameSprite;
import rotmg.objects.Player;
import rotmg.parameters.Parameters;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.StringBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.RankText;
import rotmg.ui.panels.Panel;
import rotmg.util.Currency;
import rotmg.util.TextKey;
import rotmg.util.components.LegacyBuyButton;
import spark.filters.DropShadowFilter;

/**
 * Close match but commented out stuff
 */
public class NameChangerPanel extends Panel {

	public Signal chooseName;

	public boolean buy;

	private TextFieldDisplayConcrete title;

	private Sprite button;

	public NameChangerPanel(GameSprite param1, int param2) {
		super(param1);
		Player loc3 = null;
		String loc4 = null;
		this.chooseName = new Signal();
		if (this.hasMapAndPlayer()) {
			loc3 = gs.map.player;
			this.buy = loc3.nameChosen;
			loc4 = this.createNameText();
			if (this.buy) {
				this.handleAlreadyHasName(loc4);
			} else if (loc3.numStars < param2) {
				this.handleInsufficientRank(param2);
			} else {
				this.handleNoName();
			}
		}
		addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
	}

	private void onAddedToStage(Event param1) {
		if (this.button != null) {
			stage.addEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
		}
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemovedFromStage));
	}

	private boolean hasMapAndPlayer() {
		return gs.map != null && gs.map.player != null;
	}

	private String createNameText() {
		String loc1 = null;
		loc1 = gs.model.getName();
		this.title = new TextFieldDisplayConcrete().setSize(18).setColor(16777215).setTextWidth(WIDTH);
		this.title.setBold(true).setWordWrap(true).setMultiLine(true).setHorizontalAlign(TextFormatAlign.CENTER);
		this.title.filters.add(new DropShadowFilter(0, 0, 0));
		return loc1;
	}

	private void handleAlreadyHasName(String param1) {
		this.title.setStringBuilder(this.makeNameText(param1));
		this.title.y = 0;
		addChild(this.title);
		LegacyBuyButton loc2 = new LegacyBuyButton(TextKey.NAME_CHANGER_CHANGE, 16, Parameters.NAME_CHANGE_PRICE, Currency.GOLD);
		loc2.readyForPlacement.addOnce(new SignalConsumer<>(this::positionButton));
		this.button = loc2;
		addChild(this.button);
		this.addListeners();
	}

	private void positionButton() {
		this.button.x = WIDTH / 2 - this.button.width / 2;
		this.button.y = HEIGHT - this.button.height / 2 - 17;
	}

	private void handleNoName() {
		this.title.setStringBuilder(new LineBuilder().setParams(TextKey.NAME_CHANGER_TEXT));
		this.title.y = 6;
		addChild(this.title);
		/**DeprecatedTextButton loc1 = new DeprecatedTextButton(16, TextKey.NAME_CHANGER_CHOOSE);
		 loc1.textChanged.addOnce(this.positionTextButton);
		 this.button = loc1;*/
		addChild(this.button);
		this.addListeners();
	}

	private void positionTextButton() {
		this.button.x = WIDTH / 2 - this.button.width / 2;
		this.button.y = HEIGHT - this.button.height - 4;
	}

	private void addListeners() {
		this.button.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onButtonClick));
	}

	private void handleInsufficientRank(int param1) {
		Sprite loc2 = null;
		Sprite loc4 = null;
		this.title.setStringBuilder(new LineBuilder().setParams(TextKey.NAME_CHANGER_TEXT));
		addChild(this.title);
		loc2 = new Sprite();
		TextFieldDisplayConcrete loc3 = new TextFieldDisplayConcrete().setSize(16).setColor(16777215);
		loc3.setBold(true);
		loc3.setStringBuilder(new LineBuilder().setParams(TextKey.NAME_CHANGER_REQUIRE_RANK));
		loc3.filters.add(new DropShadowFilter(0, 0, 0));
		loc2.addChild(loc3);
		loc4 = new RankText(param1, false, false);
		loc4.x = loc3.width + 4;
		loc4.y = loc3.height / 2 - loc4.height / 2;
		loc2.addChild(loc4);
		loc2.x = WIDTH / 2 - loc2.width / 2;
		loc2.y = HEIGHT - loc2.height / 2 - 20;
		addChild(loc2);
	}

	private void onRemovedFromStage(Event param1) {
		stage.removeEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
	}

	private StringBuilder makeNameText(String param1) {
		return new LineBuilder().setParams(TextKey.NAME_CHANGER_NAME_IS, "{\"name\":param1}");
	}

	private void onKeyDown(KeyboardEvent param1) {
		if (param1.keyCode == Parameters.data.interact && stage.focus == null) {
			this.performAction();
		}
	}

	private void onButtonClick(MouseEvent param1) {
		this.performAction();
	}

	private void performAction() {
		this.chooseName.dispatch();
	}

	public void updateName(String param1) {
		this.title.setStringBuilder(this.makeNameText(param1));
		this.title.y = 0;
	}

}

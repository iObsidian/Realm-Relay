package rotmg.view;

import alde.flash.utils.EventConsumer;
import flash.display.Bitmap;
import flash.display.Sprite;
import flash.events.Event;
import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import org.osflash.signals.Signal;
import rotmg.GameSprite;
import rotmg.objects.SellableObject;
import rotmg.parameters.Parameters;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.DeprecatedTextButton;
import rotmg.ui.panels.Panel;
import rotmg.util.components.LegacyBuyButton;

//100% match but commented unimplemented stuff
public class MysteryBoxPanel extends Panel {

	private final int BUTTON_OFFSET = 17;
	public Signal buyItem;
	private SellableObject owner;
	private TextFieldDisplayConcrete nameText;
	private LegacyBuyButton buyButton;
	private DeprecatedTextButton infoButton;
	private Sprite icon;
	private Bitmap bitmap;

	public MysteryBoxPanel(GameSprite param1, int param2) {
		super(param1);
		/*this.buyItem = new Signal(SellableObject);
		Injector loc3 = StaticInjectorContext.getInjector();
		GetMysteryBoxesTask loc4 = loc3.getInstance(GetMysteryBoxesTask);
		loc4.start();
		this.nameText = new TextFieldDisplayConcrete().setSize(16).setColor(16777215).setTextWidth(WIDTH - 44);
		this.nameText.setBold(true);
		this.nameText.setStringBuilder(new LineBuilder().setParams(TextKey.SELLABLEOBJECTPANEL_TEXT));
		this.nameText.setWordWrap(true);
		this.nameText.setMultiLine(true);
		this.nameText.setAutoSize(TextFieldAutoSize.CENTER);
		this.nameText.filters = [new DropShadowFilter(0, 0, 0)];
		addChild(this.nameText);
		this.icon = new Sprite();
		addChild(this.icon);
		this.bitmap = new Bitmap(null);
		this.icon.addChild(this.bitmap);
		String loc5 = "MysteryBoxPanel.open";
		String loc6 = "MysteryBoxPanel.checkBackLater";
		String loc7 = "MysteryBoxPanel.mysteryBoxShop";
		MysteryBoxModel loc8 = loc3.getInstance(MysteryBoxModel);
		Account loc9 = loc3.getInstance(Account);
		if (loc8.isInitialized() || !loc9.isRegistered()) {
			this.infoButton = new DeprecatedTextButton(16, loc5);
			this.infoButton.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onInfoButtonClick));
			addChild(this.infoButton);
		} else {
			this.infoButton = new DeprecatedTextButton(16, loc6);
			addChild(this.infoButton);
		}
		this.nameText.setStringBuilder(new LineBuilder().setParams("Shop"));
		this.bitmap.bitmapData = ArenaViewAssetFactory.returnHostBitmap(param2).bitmapData;
		addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemovedFromStage));*/
	}

	public void setOwner(SellableObject param1) {
		if (param1 == this.owner) {
			return;
		}
		this.owner = param1;
		this.buyButton.setPrice(this.owner.price, this.owner.currency);
		String loc2 = this.owner.soldObjectName();
		this.nameText.setStringBuilder(new LineBuilder().setParams(loc2));
		this.bitmap.bitmapData = this.owner.getIcon();
	}

	private void onAddedToStage(Event param1) {
		stage.addEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
		this.icon.x = -4;
		this.icon.y = -8;
		this.nameText.x = 44;
	}

	private void onRemovedFromStage(Event param1) {
		stage.removeEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
		this.infoButton.removeEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onInfoButtonClick));
	}

	private void onInfoButtonClick(MouseEvent param1) {
		this.onInfoButton();
	}

	private void onInfoButton() {
		/*ShowPopupSignal loc5 = null;
		Injector loc1 = StaticInjectorContext.getInjector();
		MysteryBoxModel loc2 = MysteryBoxModel.getInstance();
		Account loc3 = WebAccount.getInstance();
		OpenDialogSignal loc4 = OpenDialogSignal.getInstance();
		if (loc2.isInitialized() && loc3.isRegistered()) {
			if (ShopConfiguration.USE_NEW_SHOP) {
				loc5 = ShowPopupSignal.getInstance();
				loc5.dispatch(new ShopPopupView());
			} else {
				loc4.dispatch(new MysteryBoxSelectModal());
			}
		} else if (!loc3.isRegistered()) {
			loc4.dispatch(new RegisterPromptDialog("SellableObjectPanelMediator.text", {"type":
			Currency.typeToName(Currency.GOLD)}));
		}*/
	}

	private void onKeyDown(KeyboardEvent param1) {
		if (param1.keyCode == Parameters.data.interact && stage.focus == null) {
			this.onInfoButton();
		}
	}

	public void draw() {
		this.nameText.y = this.nameText.height > 30 ? 0 : 12;
		this.infoButton.x = WIDTH / 2 - this.infoButton.width / 2;
		this.infoButton.y = HEIGHT - this.infoButton.height / 2 - this.BUTTON_OFFSET;
		if (!contains(this.infoButton)) {
			addChild(this.infoButton);
		}
	}
}

package rotmg.ui.panels;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import flash.events.MouseEvent;
import flash.text.TextFieldAutoSize;
import rotmg.GameSprite;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.DeprecatedTextButton;
import spark.filters.DropShadowFilter;


public class ButtonPanel extends Panel {

	protected DeprecatedTextButton button;
	private TextFieldDisplayConcrete titleText;

	public ButtonPanel(GameSprite param1, String param2, String param3) {
		super(param1);
		this.titleText = new TextFieldDisplayConcrete().setSize(18).setColor(16777215).setTextWidth(WIDTH).setHTML(true).setWordWrap(true).setMultiLine(true).setAutoSize(TextFieldAutoSize.CENTER);
		this.titleText.setBold(true);
		this.titleText.setStringBuilder(new LineBuilder().setParams(param2).setPrefix("<p align=\"center\">").setPostfix("</p>"));
		this.titleText.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		this.titleText.y = 6;
		addChild(this.titleText);
		this.button = new DeprecatedTextButton(16, param3);
		this.button.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onButtonClick));
		this.button.textChanged.addOnce(new SignalConsumer<>(this::alignButton));
		addChild(this.button);
	}

	private void alignButton() {
		this.button.x = WIDTH / 2 - this.button.width / 2;
		this.button.y = HEIGHT - this.button.height - 4;
	}

	protected void onButtonClick(MouseEvent param1) {
	}


}

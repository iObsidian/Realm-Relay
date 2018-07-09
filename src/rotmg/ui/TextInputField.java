package rotmg.ui;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import flash.display.CapsStyle;
import flash.display.JointStyle;
import flash.display.LineScaleMode;
import flash.display.Sprite;
import flash.events.Event;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.StaticStringBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import spark.filters.DropShadowFilter;

public class TextInputField extends Sprite {

	public static final int BACKGROUND_COLOR = 3355443;

	public static final int ERROR_BORDER_COLOR = 16549442;

	public static final int NORMAL_BORDER_COLOR = 4539717;

	public TextFieldDisplayConcrete nameText;

	public BaseSimpleText inputText;

	public TextFieldDisplayConcrete errorText;

	private int textInputFieldWidth = 0;

	public TextInputField(String param1, boolean param2, double param3, double param4, double param5, int param6, boolean param7) {
		super();
		this.textInputFieldWidth = this.textInputFieldWidth;
		this.nameText = new TextFieldDisplayConcrete().setSize(18).setColor(11776947);
		this.inputText = new BaseSimpleText(param5, 11776947, true, param3, param4);
		if (param1 != "") {
			this.nameText.setBold(true);
			this.nameText.setStringBuilder(new LineBuilder().setParams(param1));
			this.nameText.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
			addChild(this.nameText);
			this.inputText.y = 30;
		} else {
			this.inputText.y = 0;
		}
		if (this.textInputFieldWidth != 0) {
			this.nameText.setTextWidth(this.textInputFieldWidth);
			this.nameText.setMultiLine(true);
			this.nameText.setWordWrap(true);
			this.nameText.textChanged.add(new SignalConsumer<>(this::textFieldWasCreatedHandler));
		}
		this.nameText.setBold(true);
		this.nameText.setStringBuilder(new LineBuilder().setParams(param1));
		this.nameText.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		addChild(this.nameText);
		this.inputText = new BaseSimpleText(20, 11776947, true, 238, 30);
		this.inputText.y = 30;
		this.inputText.x = 6;
		this.inputText.border = false;
		this.inputText.displayAsPassword = param2;
		this.inputText.updateMetrics();
		this.inputText.setMultiLine(param7);
		if (param6 > 1) {
			this.inputText.maxChars = param6;
		}
		addChild(this.inputText);
		graphics.lineStyle(2, 4539717, 1, false, LineScaleMode.NORMAL, CapsStyle.ROUND, JointStyle.ROUND);
		graphics.beginFill(3355443, 1);
		graphics.drawRect(0, this.inputText.y, param3, param4);
		graphics.endFill();
		graphics.lineStyle();
		this.drawInputBorders(false);
		this.inputText.addEventListener(Event.CHANGE, new EventConsumer<>(this::onInputChange));
		this.errorText = new TextFieldDisplayConcrete().setSize(12).setColor(16549442);
		this.errorText.setMultiLine(true);
		this.errorText.y = this.inputText.y + param4 + 1;
		this.errorText.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		addChild(this.errorText);
	}

	public String text() {
		return this.inputText.text;
	}

	public void clearText() {
		this.inputText.text = "";
	}

	public double height() {
		return this.errorText.y + this.errorText.height + 10;
	}

	private void drawInputBorders(boolean param1) {
		int loc2 = param1 ? ERROR_BORDER_COLOR : NORMAL_BORDER_COLOR;
		graphics.clear();
		graphics.lineStyle(2, loc2, 1, false, LineScaleMode.NORMAL, CapsStyle.ROUND, JointStyle.ROUND);
		graphics.beginFill(BACKGROUND_COLOR, 1);
		graphics.drawRect(0, this.inputText.y, 238, 30);
		graphics.endFill();
		graphics.lineStyle();
	}

	public void setErrorHighlight(boolean param1) {
		this.drawInputBorders(param1);
	}

	private void textFieldWasCreatedHandler() {
		if (this.textInputFieldWidth != 0) {
			this.inputText.y = this.nameText.getTextHeight() + 8;
			this.drawInputBorders(false);
		}
	}

	public void onInputChange(Event param1) {
		this.errorText.setStringBuilder(new StaticStringBuilder(""));
	}

	public void setError(String param1, String param2) {
		this.errorText.setStringBuilder(new LineBuilder().setParams(param1, param2));
		this.inputText.addEventListener(Event.CHANGE, new EventConsumer<>(this::onClearError));
	}

	public void onClearError(Event param1) {
		this.inputText.removeEventListener(Event.CHANGE, new EventConsumer<>(this::onClearError));
		this.clearError();
	}

	public void clearError() {
		this.errorText.setStringBuilder(new StaticStringBuilder(""));
	}

}

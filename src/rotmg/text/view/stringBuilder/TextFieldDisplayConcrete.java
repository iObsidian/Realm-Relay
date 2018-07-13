package rotmg.text.view.stringBuilder;

import flash.display.Sprite;
import flash.text.TextField;
import flash.text.TextFormat;
import flash.text.TextLineMetrics;
import org.osflash.signals.Signal;
import rotmg.language.model.StringMap;
import rotmg.text.model.FontInfo;

public class TextFieldDisplayConcrete extends Sprite implements TextFieldDisplay {

	public static final String MIDDLE = "middle";

	public static final String BOTTOM = "bottom";

	private static final int GUTTER = 2;

	public final Signal<?> textChanged = new Signal();

	public TextField textField;

	private StringMap stringMap;

	private StringBuilder stringBuilder;

	private int size = 12;

	private int color;

	private FontInfo font;

	private boolean bold;

	private String autoSize = "left";

	private String horizontalAlign = "left";

	private String verticalAlign;

	private boolean multiline;

	private boolean wordWrap;

	private double textWidth = 0;

	private double textHeight = 0;

	private boolean html;

	private boolean displayAsPassword;

	private String debugName;

	private int leftMargin = 0;

	private int indent = 0;

	private int leading = 0;

	public TextFieldDisplayConcrete() {
		super();
	}

	private static double getOnlyTextHeight(TextLineMetrics param1) {
		return param1.height - param1.leading;
	}

	public TextFieldDisplayConcrete setIndent(int param1) {
		this.indent = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setLeading(int param1) {
		this.leading = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setLeftMargin(int param1) {
		this.leftMargin = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setDisplayAsPassword(boolean param1) {
		this.displayAsPassword = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setDebugName(String param1) {
		this.debugName = param1;
		//this.textField != null && (this.textField.name = this.debugName);
		return this;
	}

	public TextFieldDisplayConcrete setBold(boolean param1) {
		this.bold = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setHorizontalAlign(String param1) {
		this.horizontalAlign = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setAutoSize(String param1) {
		this.autoSize = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setMultiLine(boolean param1) {
		this.multiline = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setWordWrap(boolean param1) {
		this.wordWrap = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setTextWidth(double param1) {
		this.textWidth = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public TextFieldDisplayConcrete setHTML(boolean param1) {
		this.html = param1;
		return this;
	}

	public StringBuilder getStringBuilder() {
		return this.stringBuilder;
	}

	public TextFieldDisplayConcrete setStringBuilder(StringBuilder param1) {
		this.stringBuilder = param1;
		this.setTextIfAble();
		return this;
	}

	public TextFieldDisplayConcrete setPosition(double param1, double param2) {
		this.x = param1;
		this.y = param2;
		return this;
	}

	public TextFieldDisplayConcrete setVerticalAlign(String param1) {
		this.verticalAlign = param1;
		return this;
	}

	public void update() {
		this.setTextIfAble();
	}

	public void setFont(FontInfo param1) {
		this.font = param1;
	}

	public void setStringMap(StringMap param1) {
		this.stringMap = param1;
		this.setTextIfAble();
	}

	public void setTextField(TextField param1) {
		param1.width = this.textWidth;
		param1.height = this.textHeight;
		//this.debugName != null && (param1.name = this.debugName);
		this.updateTextOfInjectedTextField(param1);
		this.textField = param1;
		this.setProperties();
		addChild(this.textField);
	}

	private void setPropertiesIfHasTextField() {
		if (this.textField != null) {
			this.setProperties();
		}
	}

	private void setProperties() {
		this.setFormatProperties();
		this.setTextFieldProperties();
	}

	private void setTextFieldProperties() {
		if (this.textWidth != 0) {
			this.textField.width = this.textWidth;
		}
		if (this.textHeight != 0) {
			this.textField.height = this.textHeight;
		}
		this.textField.selectable = false;
		this.textField.textColor = this.color;
		this.textField.autoSize = this.autoSize;
		this.textField.multiline = this.multiline;
		this.textField.wordWrap = this.wordWrap;
		this.textField.displayAsPassword = this.displayAsPassword;
		this.textField.embedFonts = true;
	}

	private void setFormatProperties() {
		TextFormat loc1 = new TextFormat();
		loc1.size = this.size;
		loc1.font = this.font.getName();
		loc1.bold = this.bold;
		loc1.align = this.horizontalAlign;
		loc1.leftMargin = this.leftMargin;
		loc1.indent = this.indent;
		loc1.leading = this.leading;
		this.setTextFormat(loc1);
	}

	private void updateTextOfInjectedTextField(TextField param1) {
		if (this.textField != null) {
			param1.text = this.textField.text;
			removeChild(this.textField);
		}
	}

	private void setTextIfAble() {
		String loc1 = null;
		if (this.isAble()) {
			this.stringBuilder.setStringMap(this.stringMap);
			loc1 = this.stringBuilder.getString();
			this.setText(loc1);
			this.alignVertically();
			this.invalidateTextField();
			this.textChanged.dispatch();
		}
	}

	private void invalidateTextField() {
		//this.textField.height;
	}

	private void alignVertically() {
		TextLineMetrics loc1 = null;
		if (this.verticalAlign.equals(MIDDLE)) {
			this.setYToMiddle();
		} else if (this.verticalAlign.equals(BOTTOM)) {
			loc1 = this.textField.getLineMetrics(0);
			this.textField.y = -getOnlyTextHeight(loc1);
		}
	}

	public double getTextHeight() {
		return this.textField != null ? this.textField.height : 0;
	}

	public TextFieldDisplayConcrete setTextHeight(double param1) {
		this.textHeight = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	private void setYToMiddle() {
		TextFormat loc1 = this.textField.getTextFormat();
		double loc2 = this.getSpecificXHeight(loc1);
		double loc3 = this.getSpecificVerticalSpace(loc1);
		this.textField.y = -(this.textField.height - (loc2 / 2 + loc3 + GUTTER));
	}

	private double getSpecificXHeight(TextFormat param1) {
		return this.font.getXHeight(param1.size);
	}

	private double getSpecificVerticalSpace(TextFormat param1) {
		return this.font.getVerticalSpace(param1.size);
	}

	public void setTextFormat(TextFormat param1) {
		setTextFormat(param1, 0, 0);
	}

	public void setTextFormat(TextFormat param1, int param2, int param3) {
		this.textField.defaultTextFormat = param1;
		this.textField.setTextFormat(param1, param2, param3);
	}

	private boolean isAble() {
		return this.stringMap != null && this.stringBuilder != null && this.textField != null;
	}

	public double getVerticalSpace() {
		return this.font.getVerticalSpace(this.textField.getTextFormat().size);
	}

	public String getText() {
		return this.textField != null ? this.textField.text : "null";
	}

	public void setText(String param1) {
		if (this.html) {
			this.textField.htmlText = param1;
		} else {
			this.textField.text = param1;
		}
	}

	public int getColor() {
		return this.color;
	}

	public TextFieldDisplayConcrete setColor(int param1) {
		this.color = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public int getSize() {
		return this.size;
	}

	public TextFieldDisplayConcrete setSize(int param1) {
		this.size = param1;
		this.setPropertiesIfHasTextField();
		return this;
	}

	public boolean hasTextField() {
		return this.textField != null;
	}

	public boolean hasStringMap() {
		return this.stringMap != null;
	}

	public boolean hasFont() {
		return this.font != null;
	}

	public TextFormat getTextFormat(int param1, int param2) {
		return this.textField.getTextFormat(param1, param2);
	}


}

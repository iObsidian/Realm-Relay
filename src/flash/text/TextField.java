package flash.text;

import flash.display.InteractiveObject;

public class TextField extends InteractiveObject {

	public double width;
	public double height;

	public String text;

	public String name;
	public boolean selectable;
	public int textColor;
	public String autoSize;
	public boolean multiline;
	public boolean embedFonts;
	public boolean displayAsPassword;
	public boolean wordWrap;
	public String htmlText;
	public double y;
	public int textWidth;
	public TextFormat defaultTextFormat;
	public int maxChars;
	public boolean border;

	public TextLineMetrics getLineMetrics(int i) {
		return null;
	}

	public TextFormat getTextFormat() {
		return null;
	}

	public void setTextFormat(TextFormat f) {
	}

	public TextFormat getTextFormat(int param1, int param2) {
		return null;
	}

	public void setTextFormat(TextFormat param1, int param2, int param3) {
	}
}

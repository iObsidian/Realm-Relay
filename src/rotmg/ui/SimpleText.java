package rotmg.ui;

import flash.events.Event;
import flash.text.TextField;
import flash.text.TextFormat;

public class SimpleText extends TextField {


	//public static final Class MyriadPro = BaseSimpleText_MyriadPro;

	public int inputWidth;

	public int inputHeight;

	public int actualWidth;

	public int actualHeight;

	public SimpleText(int param1, int param2) {
		this(param1, param2, false, 0, 0);
	}

	public SimpleText(int param1, int param2, boolean param3, int param4, int param5) {
		super();
		this.inputWidth = param4;
		if (this.inputWidth != 0) {
			width = param4;
		}
		this.inputHeight = param5;
		if (this.inputHeight != 0) {
			height = param5;
		}
		/**Font.registerFont(MyriadPro);
		 Font loc6 = new MyriadPro();
		 TextFormat loc7 = this.defaultTextFormat;
		 loc7.font = loc6.fontName;
		 loc7.bold = false;
		 loc7.size = param1;
		 loc7.color = param2;
		 defaultTextFormat = loc7;
		 if (param3) {
		 selectable = true;
		 mouseEnabled = true;
		 type = TextFieldType.INPUT;
		 embedFonts = true;
		 border = true;
		 borderColor = param2;
		 setTextFormat(loc7);
		 addEventListener(Event.CHANGE, this.onChange);
		 } else {
		 selectable = false;
		 mouseEnabled = false;
		 }*/
	}

	public SimpleText(int i, int i1, boolean b, int i2, int i3, String myriad_pro) {

	}

	public void setFont(String param1) {

	}

	public void setSize(int param1) {

	}

	public void setColor(int param1) {

	}

	public void setBold(boolean param1) {

	}

	public void setAlignment(String param1) {

	}

	public void setText(String param1) {
		this.text = param1;
	}

	public void setMultiLine(boolean param1) {
		/**multiline = param1;
		 wordWrap = param1;*/
	}

	private void applyFormat(TextFormat param1) {
		/*setTextFormat(param1);
		defaultTextFormat = param1;*/
	}

	private void onChange(Event param1) {
		this.updateMetrics();
	}

	public void updateMetrics() {
		/**TextLineMetrics loc2 = null;
		 int loc3 = 0;
		 int loc4 = 0;
		 this.actualWidth = 0;
		 this.actualHeight = 0;
		 int loc1 = 0;
		 while (loc1 < numLines) {
		 loc2 = getLineMetrics(loc1);
		 loc3 = loc2.width + 4;
		 loc4 = loc2.height + 4;
		 if (loc3 > this.actualWidth) {
		 this.actualWidth = loc3;
		 }
		 this.actualHeight = this.actualHeight + loc4;
		 loc1++;
		 }
		 width = this.inputWidth == 0 ? (double) this.actualWidth : (double) this.inputWidth;
		 height = this.inputHeight == 0 ? (double) this.actualHeight : (double) this.inputHeight;*/
	}

	public void useTextDimensions() {
		/**width = this.inputWidth == 0 ? (double) textWidth + 4 : (double) this.inputWidth;
		 height = this.inputHeight == 0 ? (double) textHeight + 4 : (double) this.inputHeight;*/
	}
}

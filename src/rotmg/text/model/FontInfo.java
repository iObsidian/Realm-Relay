package rotmg.text.model;

import flash.text.TextField;
import flash.text.TextFieldAutoSize;
import flash.text.TextFormat;

public class FontInfo {

	private static final double renderingFontSize = 200;

	private static final double GUTTER = 2;

	protected String name;

	private int textColor = 0;

	private double xHeightRatio;

	private double verticalSpaceRatio;

	public FontInfo() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String param1) {
		this.name = param1;
		this.computeRatiosByRendering();
	}

	public double getXHeight(double param1) {
		return this.xHeightRatio * param1;
	}

	public double getVerticalSpace(double param1) {
		return this.verticalSpaceRatio * param1;
	}

	private void computeRatiosByRendering() {
		/**TextField loc1 = this.makeTextField();
		 BitmapData loc2 = new BitmapDataSpy(loc1.width, loc1.height);
		 loc2.draw(loc1);
		 int loc3 = 16777215;
		 Rectangle loc4 = loc2.getColorBoundsRect(loc3, this.textColor, true);
		 this.xHeightRatio = this.deNormalize(loc4.height);
		 this.verticalSpaceRatio = this.deNormalize(loc1.height - loc4.bottom - GUTTER);*/
	}

	private TextField makeTextField() {
		TextField loc1 = new TextField();
		loc1.autoSize = TextFieldAutoSize.LEFT;
		loc1.text = "x";
		loc1.textColor = this.textColor;
		loc1.setTextFormat(new TextFormat(this.name, renderingFontSize));
		return loc1;
	}

	private double deNormalize(double param1) {
		return param1 / renderingFontSize;
	}


}

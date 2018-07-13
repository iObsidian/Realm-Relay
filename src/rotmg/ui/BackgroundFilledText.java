package rotmg.ui;

import alde.flash.utils.Vector;
import flash.display.GraphicsPath;
import flash.display.GraphicsSolidFill;
import flash.display.IGraphicsData;
import flash.display.Sprite;
import flash.text.TextFieldAutoSize;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.util.GraphicsUtil;

public class BackgroundFilledText extends Sprite {

	protected static final int MARGIN = 4;

	public int bWidth = 0;

	protected TextFieldDisplayConcrete text;

	protected int w;

	protected GraphicsSolidFill enabledFill;

	protected GraphicsSolidFill disabledFill;

	protected GraphicsPath path;

	protected Vector<IGraphicsData> graphicsData;

	public BackgroundFilledText(int param1) {
		super();
		this.enabledFill = new GraphicsSolidFill(16777215, 1);
		this.disabledFill = new GraphicsSolidFill(8355711, 1);
		this.path = new GraphicsPath(new Vector<Integer>(), new Vector<Double>());

		graphicsData = new Vector<IGraphicsData>(this.enabledFill, this.path, GraphicsUtil.END_FILL);
		this.bWidth = param1;
	}

	protected void centerTextAndDrawButton() {
		this.w = this.bWidth != 0 ? this.bWidth : (int) (this.text.width + 12);
		this.text.x = this.w / 2;
		GraphicsUtil.clearPath(this.path);
		GraphicsUtil.drawCutEdgeRect(0, 0, this.w, (int) (this.text.height + MARGIN * 2), 4, new Vector<>(1, 1, 1, 1), this.path);
	}

	public void addText(int param1) {
		this.text = this.makeText().setSize(param1).setColor(3552822);
		this.text.setBold(true);
		this.text.setAutoSize(TextFieldAutoSize.CENTER);
		this.text.y = MARGIN;
		addChild(this.text);
	}

	protected TextFieldDisplayConcrete makeText() {
		return new TextFieldDisplayConcrete();
	}
}

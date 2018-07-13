package rotmg.util.components;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import flash.airglobal.Graphics;
import flash.display.*;
import flash.events.MouseEvent;
import flash.filters.ColorMatrixFilter;
import flash.text.TextField;
import flash.text.TextFieldAutoSize;
import flash.text.TextFormat;
import flash.text.TextFormatAlign;
import flash.ui.Mouse;
import rotmg.assets.services.IconFactory;
import rotmg.parameters.Parameters;
import rotmg.ui.view.SignalWaiter;
import rotmg.util.Currency;
import rotmg.util.GraphicsUtil;
import rotmg.util.MoreColorUtil;
import rotmg.util.components.api.BuyButton;

public class SimpleButton extends BuyButton {

	public static final BitmapData coin = IconFactory.makeCoin();
	public static final BitmapData fortune = IconFactory.makeFortune();
	public static final BitmapData fame = IconFactory.makeFame();
	public static final BitmapData guildFame = IconFactory.makeGuildFame();
	private static final int BEVEL = 4;
	private static final int PADDING = 2;
	private static final ColorMatrixFilter grayfilter = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);
	private final GraphicsSolidFill enabledFill = new GraphicsSolidFill(16777215, 1);
	private final GraphicsSolidFill disabledFill = new GraphicsSolidFill(8355711, 1);
	private final GraphicsPath graphicsPath = new GraphicsPath(new Vector<Integer>(), new Vector<Double>());
	private final SignalWaiter waiter = new SignalWaiter();
	public String prefix;
	public TextField text;
	public Bitmap icon;
	public int price = -1;
	public int currency = -1;
	public int _width = -1;
	private Vector<IGraphicsData> graphicsData;
	private boolean withOutLine = false;

	private int outLineColor = 5526612;

	private int fixedWidth = -1;

	private int fixedHeight = -1;

	private int textVertMargin = 4;

	public SimpleButton(String param1, int param2, int param3, boolean param4) {
		super();
		graphicsData = new Vector<IGraphicsData>(this.enabledFill, this.graphicsPath, GraphicsUtil.END_FILL);
		this.prefix = param1;
		this.text = new TextField();
		TextFormat loc5 = new TextFormat();
		loc5.size = 16;
		loc5.font = "Myriad Pro";
		loc5.bold = true;
		loc5.align = TextFormatAlign.LEFT;
		loc5.leftMargin = 0;
		loc5.indent = 0;
		loc5.leading = 0;
		this.text.textColor = 3552822;
		this.text.autoSize = TextFieldAutoSize.CENTER;
		this.text.selectable = false;
		this.text.defaultTextFormat = loc5;
		this.text.setTextFormat(loc5);
		this.waiter.complete.add(new SignalConsumer<>(this::updateUI));
		this.waiter.complete.addOnce(new SignalConsumer<>(this::readyForPlacementDispatch));
		addChild(this.text);
		this.icon = new Bitmap();
		addChild(this.icon);
		addEventListener(MouseEvent.MOUSE_OVER, new EventConsumer<>(this::onMouseOver));
		addEventListener(MouseEvent.ROLL_OUT, new EventConsumer<>(this::onRollOut));
		if (param2 != -1) {
			this.setPrice(param2, param3);
		} else {
			this.text.text = param1;
			this.updateUI();
		}
		this.withOutLine = param4;
	}

	public SimpleButton(String param1) {
	}

	public void setPrice(int param1, int param2) {
		if (this.price != param1 || this.currency != param2) {
			this.price = param1;
			this.currency = param2;
			this.text.text = this.prefix + param1;
			this.updateUI();
		}
	}

	public int getPrice() {
		return this.price;
	}

	public void setText(String param1) {
		this.text.text = param1;
		this.updateUI();
	}

	public void setEnabled(boolean param1) {
		if (param1 != mouseEnabled) {
			mouseEnabled = param1;
			filters = new Vector<>(grayfilter);
			this.draw();
		}
	}

	private void updateUI() {
		this.updateText();
		this.updateIcon();
		this.updateBackground();
		this.draw();
	}

	private void readyForPlacementDispatch() {
		this.updateUI();
		readyForPlacement.dispatch();
	}

	private void updateIcon() {
		switch (this.currency) {
			case Currency.GOLD:
				this.icon.bitmapData = coin;
				break;
			case Currency.FAME:
				this.icon.bitmapData = fame;
				break;
			case Currency.GUILD_FAME:
				this.icon.bitmapData = guildFame;
				break;
			case Currency.FORTUNE:
				this.icon.bitmapData = fortune;
				break;
			default:
				this.icon.bitmapData = null;
		}
		this.updateIconPosition();
	}

	private void updateBackground() {
		GraphicsUtil.clearPath(this.graphicsPath);
		GraphicsUtil.drawCutEdgeRect(0, 0, this.getWidth(), this.getHeight(), BEVEL, new Vector<>(1, 1, 1, 1, this.graphicsPath));
	}

	private void updateText() {
		this.text.x = (this.getWidth() - this.icon.width - this.text.width - PADDING) * 0.5;
		this.text.y = this.textVertMargin;
	}

	private void updateIconPosition() {
		this.icon.x = (int) (this.text.x + this.text.width);
		this.icon.y = (int) ((this.getHeight() - this.icon.height - 1) * 0.5);
	}

	private void onMouseOver(MouseEvent param1) {
		Mouse.cursor = "button";
		this.enabledFill.color = 16768133;
		this.draw();
	}

	private void onRollOut(MouseEvent param1) {
		Mouse.cursor = Parameters.data.cursorSelect;
		this.enabledFill.color = 16777215;
		this.draw();
	}

	public void draw() {
		this.graphicsData.put(0, !!mouseEnabled ? this.enabledFill : this.disabledFill);
		graphics.clear();
		graphics.drawGraphicsData(this.graphicsData);
		if (this.withOutLine) {
			this.drawOutline(graphics);
		}
	}

	private int getWidth() {
		return this.fixedWidth != -1 ? this.fixedWidth : (int) Math.max(this._width, this.text.width + this.icon.width + 4 * PADDING);
	}

	public void setWidth(int param1) {
		this._width = param1;
		this.updateUI();
	}

	private int getHeight() {
		return this.fixedHeight != -1 ? this.fixedHeight : (int) (this.text.height + this.textVertMargin * 2);
	}

	public void freezeSize() {
		this.fixedHeight = this.getHeight();
		this.fixedWidth = this.getWidth();
	}

	public void unfreezeSize() {
		this.fixedHeight = -1;
		this.fixedWidth = -1;
	}

	public void scaleButtonWidth(double param1) {
		this.fixedWidth = (int) (this.getWidth() * param1);
		this.updateUI();
	}

	public void scaleButtonHeight(double param1) {
		this.textVertMargin = (int) (this.textVertMargin * param1);
		this.updateUI();
	}

	public void setOutLineColor(int param1) {
		this.outLineColor = param1;
	}

	private void drawOutline(Graphics param1) {
		/**GraphicsSolidFill loc2 = new GraphicsSolidFill(0, 0.01);
		 GraphicsSolidFill loc3 = new GraphicsSolidFill(new EventConsumer<>(this::outLineColor), 0.6);
		 GraphicsStroke loc4 = new GraphicsStroke(4, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3, loc3);
		 GraphicsPath loc5 = new GraphicsPath();
		 GraphicsUtil.drawCutEdgeRect(0, 0, this.getWidth(), this.getHeight(), 4, GraphicsUtil.ALL_CUTS, loc5);
		 Vector loc6 = new IGraphicsData[]{loc4, loc2, loc5, GraphicsUtil.END_FILL, GraphicsUtil.END_STROKE};
		 param1.drawGraphicsData(loc6);*/
	}

}

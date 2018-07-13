package rotmg.util.components;

import alde.flash.utils.Vector;
import flash.airglobal.Graphics;
import flash.display.*;
import flash.events.MouseEvent;
import flash.filters.ColorMatrixFilter;
import rotmg.assets.services.IconFactory;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.StaticStringBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.view.SignalWaiter;
import rotmg.util.Currency;
import rotmg.util.MoreColorUtil;
import rotmg.util.components.api.BuyButton;

/**
 * Not fully implemented
 */
public class LegacyBuyButton extends BuyButton {

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
	public TextFieldDisplayConcrete text;
	public Bitmap icon;
	public int price = -1;
	public int currency = -1;
	public int _width = -1;
	private StaticStringBuilder staticStringBuilder;
	private LineBuilder lineBuilder;
	private Vector<IGraphicsData> graphicsData;
	private boolean withOutLine = false;

	private int outLineColor = 5526612;

	private int fixedWidth = -1;

	private int fixedHeight = -1;

	private int textVertMargin = 4;

	public LegacyBuyButton(String param1, int param2, int param3, int param4) {
		this(param1, param2, param3, param4, false, false);
	}

	public LegacyBuyButton(String param1, int param2, int param3, int param4, boolean param5, boolean param6) {
		this.staticStringBuilder = new StaticStringBuilder("");
		this.lineBuilder = new LineBuilder();


	}

	public void setPrice(int param1, int param2) {

	}

	public void setStringBuilder(StringBuilder param1) {

	}

	public int getPrice() {
		return this.price;
	}

	public void setText(String param1) {
		this.text.setStringBuilder(new StaticStringBuilder(param1));
		this.updateUI();
	}

	public void setEnabled(boolean param1) {

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

	}

	private void updateText() {
		this.text.x = (this.getWidth() - this.icon.width - this.text.width - PADDING) * 0.5;
		this.text.y = this.textVertMargin;
	}

	private void updateIconPosition() {

	}

	private void onMouseOver(MouseEvent param1) {
		this.enabledFill.color = 16768133;
		this.draw();
	}

	private void onRollOut(MouseEvent param1) {
		this.enabledFill.color = 16777215;
		this.draw();
	}

	public void draw() {

	}

	private int getWidth() {
		return this._width;
	}

	public void setWidth(int param1) {
		this._width = param1;
		this.updateUI();
	}

	private int getHeight() {
		return this.fixedHeight;
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

	}

	public void scaleButtonHeight(double param1) {

	}

	public void setOutLineColor(int param1) {
		this.outLineColor = param1;
	}

	private void drawOutline(Graphics param1) {

	}

}

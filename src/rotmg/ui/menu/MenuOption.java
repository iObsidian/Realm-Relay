package rotmg.ui.menu;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import flash.display.Bitmap;
import flash.display.BitmapData;
import flash.display.Sprite;
import flash.events.MouseEvent;
import flash.geom.ColorTransform;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.util.CachingColorTransformer;
import rotmg.util.MoreColorUtil;
import rotmg.util.TextureRedrawer;
import spark.filters.DropShadowFilter;

public class MenuOption extends Sprite {

	protected static final ColorTransform mouseOverCT = new ColorTransform(1, 220 / 255, 133 / 255);

	protected BitmapData origIconBitmapData;

	protected BitmapData iconBitmapData;

	protected Bitmap icon;

	protected TextFieldDisplayConcrete text;

	protected ColorTransform ct = null;

	public MenuOption(BitmapData param1, int param2, String param3) {
		super();
		this.origIconBitmapData = param1;
		this.iconBitmapData = TextureRedrawer.redraw(param1, this.redrawSize(), true, 0);
		this.icon = new Bitmap(this.iconBitmapData);
		this.icon.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		this.icon.x = -12;
		this.icon.y = -15;
		addChild(this.icon);
		this.text = new TextFieldDisplayConcrete().setSize(18).setColor(param2);
		this.text.setBold(true);
		this.text.setStringBuilder(new LineBuilder().setParams(param3));
		this.text.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		this.text.x = 20;
		this.text.y = -6;
		addChild(this.text);
		addEventListener(MouseEvent.MOUSE_OVER, new EventConsumer<>(this::onMouseOver));
		addEventListener(MouseEvent.MOUSE_OUT, new EventConsumer<>(this::onMouseOut));
	}

	public void setColorTransform(ColorTransform param1) {
		BitmapData loc2 = null;
		if (param1 == this.ct) {
			return;
		}
		this.ct = param1;
		if (this.ct == null) {
			this.icon.bitmapData = this.iconBitmapData;
			this.text.transform.colorTransform = MoreColorUtil.identity;
		} else {
			loc2 = CachingColorTransformer.transformBitmapData(this.origIconBitmapData, this.ct);
			loc2 = TextureRedrawer.redraw(loc2, this.redrawSize(), true, 0);
			this.icon.bitmapData = loc2;
			this.text.transform.colorTransform = this.ct;
		}
	}

	protected void onMouseOver(MouseEvent param1) {
		this.setColorTransform(mouseOverCT);
	}

	protected void onMouseOut(MouseEvent param1) {
		this.setColorTransform(null);
	}

	protected int redrawSize() {
		return 40 / (this.origIconBitmapData.width / 8);
	}
}

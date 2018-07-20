package rotmg.ui.tooltip;

import alde.flash.utils.Vector;
import flash.display.Bitmap;
import flash.display.BitmapData;
import rotmg.objects.GameObject;
import rotmg.util.BitmapUtil;

public class PortraitToolTip extends ToolTip {

	private Bitmap portrait;

	public PortraitToolTip(GameObject param1) {
		super(6036765, 1, 16549442, 1, false);
		this.portrait = new Bitmap();
		this.portrait.x = 0;
		this.portrait.y = 0;
		BitmapData loc2 = param1.getPortrait();
		loc2 = BitmapUtil.cropToBitmapData(loc2, 10, 10, loc2.width - 20, loc2.height - 20);
		this.portrait.bitmapData = loc2;
		addChild(this.portrait);
		filters = new Vector<>();
	}

}

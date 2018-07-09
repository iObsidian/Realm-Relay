package rotmg.ui;

import alde.flash.utils.Vector;
import flash.display.Bitmap;
import flash.display.Sprite;
import flash.text.TextFieldAutoSize;
import rotmg.text.view.stringBuilder.StaticStringBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.util.GuildUtil;
import rotmg.util.SpriteUtil;
import spark.filters.DropShadowFilter;

public class GuildText extends Sprite {

	private String name;

	private int rank;

	private Bitmap icon;

	private TextFieldDisplayConcrete guildName;

	public GuildText(String param1, int param2, int param3) {
		super();
		this.icon = new Bitmap(null);
		this.icon.y = -8;
		this.icon.x = -8;
		int loc4 = param3 == 0 ? 0 : (int) param3 - (this.icon.width - 16);
		this.guildName = new TextFieldDisplayConcrete().setSize(16).setColor(16777215).setTextWidth(loc4);
		this.guildName.setAutoSize(TextFieldAutoSize.LEFT);
		this.guildName.filters = new Vector(new DropShadowFilter(0, 0, 0));
		this.guildName.x = 24;
		this.guildName.y = 2;
		this.draw(param1, param2);
	}

	public GuildText(String s, int i) {
		super();
	}

	public void draw(String param1, int param2) {
		if (this.name.equals(param1) && param2 == param2) {
			return;
		}
		this.name = param1;
		this.rank = param2;
		if (this.name == null || this.name.equals("")) {
			SpriteUtil.safeRemoveChild(this, this.icon);
			SpriteUtil.safeRemoveChild(this, this.guildName);
		} else {
			this.icon.bitmapData = GuildUtil.rankToIcon(this.rank, 20);
			SpriteUtil.safeAddChild(this, this.icon);
			this.guildName.setStringBuilder(new StaticStringBuilder(this.name));
			SpriteUtil.safeAddChild(this, this.guildName);
		}
	}


}

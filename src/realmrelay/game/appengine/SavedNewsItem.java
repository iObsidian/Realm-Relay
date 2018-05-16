package realmrelay.game.appengine;

import realmrelay.game.util.AssetLibrary;
import realmrelay.game._as3.BitmapData;

public class SavedNewsItem {

	private static final String FAME = "fame";

	private String iconName;

	public String title;

	public String tagline;

	public String link;

	public int date;

	public SavedNewsItem(String param1, String param2, String param3, String param4, int param5) {
		super();
		this.iconName = param1;
		this.title = param2;
		this.tagline = param3;
		this.link = param4;
		this.date = param5;
	}

	private static BitmapData forumIcon() {
		BitmapData loc1 = AssetLibrary.getImageFromSet("lofiInterface2", 4);
		return TextureRedrawer.redraw(loc1, 80, true, 0);
	}

	private static BitmapData fameIcon() {
		BitmapData loc1 = AssetLibrary.getImageFromSet("lofiObj3", 224);
		return TextureRedrawer.redraw(loc1, 80, true, 0);
	}

	public BitmapData getIcon() {
		return this.iconName.equals(FAME) ? fameIcon() : forumIcon();
	}

	public boolean isCharDeath() {
		return this.iconName.equals(FAME);
	}

}

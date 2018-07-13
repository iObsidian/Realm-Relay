package rotmg.util;


import alde.flash.utils.XML;
import flash.display.BitmapData;
import flash.display.Sprite;
import flash.geom.ColorTransform;
import rotmg.objects.ObjectLibrary;
import spark.filters.DropShadowFilter;

public class FameUtil {

	public static final int MAX_STARS = 70;

	public static final Integer[] STARS = new Integer[]{20, 150, 400, 800, 2000};

	private static final ColorTransform lightBlueCT = new ColorTransform(138 / 255, 152 / 255, 222 / 255);

	private static final ColorTransform darkBlueCT = new ColorTransform(49 / 255, 77 / 255, 219 / 255);

	private static final ColorTransform redCT = new ColorTransform(193 / 255, 39 / 255, 45 / 255);

	private static final ColorTransform orangeCT = new ColorTransform(247 / 255, 147 / 255, 30 / 255);

	private static final ColorTransform yellowCT = new ColorTransform(255 / 255, 255 / 255, 0 / 255);

	public static final ColorTransform[] COLORS = new ColorTransform[]{lightBlueCT, darkBlueCT, redCT, orangeCT, yellowCT};

	public FameUtil() {
		super();
	}

	public static int maxStars() {
		return ObjectLibrary.playerChars.size() * STARS.length;
	}

	public static int numStars(int param1) {
		int loc2 = 0;
		while (loc2 < STARS.length && param1 >= STARS[loc2]) {
			loc2++;
		}
		return loc2;
	}

	public static int nextStarFame(int param1, int param2) {
		int loc3 = Math.max(param1, param2);
		int loc4 = 0;
		while (loc4 < STARS.length) {
			if (STARS[loc4] > loc3) {
				return STARS[loc4];
			}
			loc4++;
		}
		return -1;
	}

	public static int numAllTimeStars(int param1, int param2, XML param3) {
		int loc4 = 0;
		int loc5 = 0;
		for (XML loc6 : param3.children("ClassStats")) {
			if (param1 == loc6.getIntAttribute("objectType")) {
				loc5 = loc6.getIntValue("BestFame");
			} else {
				loc4 = loc4 + FameUtil.numStars(loc6.getIntValue("BestFame"));
			}
		}
		loc4 = loc4 + FameUtil.numStars(Math.max(loc5, param2));
		return loc4;
	}

	public static Sprite numStarsToBigImage(int param1) {
		Sprite loc2 = numStarsToImage(param1);
		loc2.filters.set(new DropShadowFilter(0, 0, 0, 1, 4, 4, 2));
		loc2.scaleX = 1.4;
		loc2.scaleY = 1.4;
		return loc2;
	}

	public static Sprite numStarsToImage(int param1) {
		/**Sprite loc2 = new StarGraphic();
		 if (param1 < ObjectLibrary.playerChars.size()) {
		 loc2.transform.colorTransform = lightBlueCT;
		 } else if (param1 < ObjectLibrary.playerChars.size() * 2) {
		 loc2.transform.colorTransform = darkBlueCT;
		 } else if (param1 < ObjectLibrary.playerChars.size() * 3) {
		 loc2.transform.colorTransform = redCT;
		 } else if (param1 < ObjectLibrary.playerChars.size() * 4) {
		 loc2.transform.colorTransform = orangeCT;
		 } else if (param1 < ObjectLibrary.playerChars.size() * 5) {
		 loc2.transform.colorTransform = yellowCT;
		 }
		 return loc2;*/

		return new Sprite();
	}

	public static Sprite numStarsToIcon(int param1) {
		Sprite loc2 = null;
		loc2 = numStarsToImage(param1);
		Sprite loc3 = new Sprite();
		loc3.graphics.beginFill(0, 0.4);
		int loc4 = (int) (loc2.width / 2 + 2);
		int loc5 = (int) (loc2.height / 2 + 2);
		loc3.graphics.drawCircle(loc4, loc5, loc4);
		loc2.x = 2;
		loc2.y = 1;
		loc3.addChild(loc2);
		loc3.filters.set(new DropShadowFilter(0.0, 0.0, 0.0, 0.5, 6.0, 6.0, 1.0));
		return loc3;
	}

	public static BitmapData getFameIcon() {
		BitmapData loc1 = AssetLibrary.getImageFromSet("lofiObj3", 224);
		return TextureRedrawer.redraw(loc1, 40, true, 0);
	}

}

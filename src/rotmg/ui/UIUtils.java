package rotmg.ui;

import flash.display.Sprite;
import flash.display.StageQuality;
import rotmg.WebMain;

public class UIUtils {

	public static final double NOTIFICATION_BACKGROUND_HEIGHT = 25;
	public static final String EXPERIMENTAL_MENU_PASSWORD = "decamenu";
	public static final int NOTIFICATION_SPACE = 28;
	private static final double NOTIFICATION_BACKGROUND_WIDTH = 95;
	private static final double NOTIFICATION_BACKGROUND_ALPHA = 0.4;
	private static final double NOTIFICATION_BACKGROUND_COLOR = 0;
	public static boolean SHOW_EXPERIMENTAL_MENU = true;


	public UIUtils() {
		super();
	}

	public static Sprite makeStaticHUDBackground() {
		double loc1 = NOTIFICATION_BACKGROUND_WIDTH;
		double loc2 = NOTIFICATION_BACKGROUND_HEIGHT;
		return makeHUDBackground(loc1, loc2);
	}

	public static Sprite makeHUDBackground(double param1, double param2) {
		Sprite loc3 = new Sprite();
		return drawHUDBackground(loc3, param1, param2);
	}

	private static Sprite drawHUDBackground(Sprite param1, double param2, double param3) {
		param1.graphics.beginFill(NOTIFICATION_BACKGROUND_COLOR, NOTIFICATION_BACKGROUND_ALPHA);
		param1.graphics.drawRoundRect(0, 0, param2, param3, 12, 12);
		param1.graphics.endFill();
		return param1;
	}

	public static void toggleQuality(boolean param1) {
		if (WebMain.STAGE != null) {
			WebMain.STAGE.quality = !!param1 ? StageQuality.HIGH : StageQuality.LOW;
		}
	}


}

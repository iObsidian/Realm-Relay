package rotmg.util;

import flash.display.DisplayObject;
import flash.display.DisplayObjectContainer;

public class SpriteUtil {

	public static void safeAddChild(DisplayObjectContainer param1, DisplayObject param2) {
		if (param1 != null && param2 != null && !param1.contains(param2)) {
			param1.addChild(param2);
		}
	}

	public static void safeRemoveChild(DisplayObjectContainer param1, DisplayObject param2) {
		if (param1 != null && param2 != null && param1.contains(param2)) {
			param1.removeChild(param2);
		}
	}


}

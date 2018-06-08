package rotmg.tutorial;

import rotmg.AGameSprite;

public class doneAction {

	public static void doneAction(AGameSprite param1, String param2) {
		if (param1.tutorial == null) {
			return;
		}
		param1.tutorial.doneAction(param2);
	}

}

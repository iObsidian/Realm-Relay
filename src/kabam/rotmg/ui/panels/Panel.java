package kabam.rotmg.ui.panels;

import flash.display.Sprite;
import kabam.rotmg.AGameSprite;
import rotmg.AGameSprite;

public class Panel extends Sprite {

	public static final int WIDTH = 200 - 12;

	public static final int HEIGHT = 100 - 16;

	public AGameSprite gs;

	public Panel(AGameSprite param1) {
		super();
		this.gs = param1;
	}

	public void draw() {
	}

}

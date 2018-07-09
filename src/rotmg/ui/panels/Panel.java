package rotmg.ui.panels;

import flash.display.Sprite;
<<<<<<< HEAD:src/kabam/rotmg/ui/panels/Panel.java
import kabam.rotmg.AGameSprite;
=======
import rotmg.AGameSprite;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/ui/panels/Panel.java

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

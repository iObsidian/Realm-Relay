package realmrelay.game.ui.panels;

import realmrelay.game._as3.Sprite;
import realmrelay.game.game.AGameSprite;

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

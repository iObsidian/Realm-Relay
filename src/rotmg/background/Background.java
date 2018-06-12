package rotmg.background;

import flash.display.Sprite;
import rotmg.map.Camera;

/**
 * 100% match
 */
public class Background extends Sprite {

	public static final int NO_BACKGROUND = 0;

	public static final int STAR_BACKGROUND = 1;

	public static final int NEXUS_BACKGROUND = 2;

	public static final int NUM_BACKGROUND = 3;

	public Background() {
		super();
		visible = false;
	}

	public static Background getBackground(int param1) {
		switch (param1) {
			case NO_BACKGROUND:
				return null;
			case STAR_BACKGROUND:
				return new StarBackground();
			case NEXUS_BACKGROUND:
				return new NexusBackground();
			default:
				return null;
		}
	}

	public void draw(Camera param1, int param2) {
	}

}

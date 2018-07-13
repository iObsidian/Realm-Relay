package rotmg.pets.view.components;

import flash.display.Sprite;

public class PopupWindowBackground extends Sprite {

	public static final String HORIZONTAL_DIVISION = "HORIZONTAL_DIVISION";

	public static final String VERTICAL_DIVISION = "VERTICAL_DIVISION";
	public static final int TYPE_DEFAULT_GREY = 0;
	public static final int TYPE_TRANSPARENT_WITH_HEADER = 1;
	public static final int TYPE_TRANSPARENT_WITHOUT_HEADER = 2;
	public static final int TYPE_DEFAULT_BLACK = 3;
	private static final int BEVEL = 4;


	public PopupWindowBackground() {
		super();
	}

	public void draw(double param1, double param2, double param3) {
		/*BevelRect loc4 = new BevelRect(param1, param2, BEVEL);
		GraphicsHelper loc5 = new GraphicsHelper();
		graphics.lineStyle(1, 16777215, 1, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3);
		if (param3 == TYPE_TRANSPARENT_WITH_HEADER) {
			graphics.lineStyle(1, 3552822, 1, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3);
			graphics.beginFill(3552822, 1);
			loc5.drawBevelRect(1, 1, new BevelRect(param1 - 2, 29, BEVEL), graphics);
			graphics.endFill();
			graphics.beginFill(3552822, 1);
			graphics.drawRect(1, 15, param1 - 2, 15);
			graphics.endFill();
			graphics.lineStyle(2, 16777215, 1, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3);
			graphics.beginFill(3552822, 0);
			loc5.drawBevelRect(0, 0, loc4, graphics);
			graphics.endFill();
		} else if (param3 == TYPE_TRANSPARENT_WITHOUT_HEADER) {
			graphics.lineStyle(2, 16777215, 1, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3);
			graphics.beginFill(3552822, 0);
			loc5.drawBevelRect(0, 0, loc4, graphics);
			graphics.endFill();
		} else if (param3 == TYPE_DEFAULT_GREY) {
			graphics.beginFill(3552822);
			loc5.drawBevelRect(0, 0, loc4, graphics);
			graphics.endFill();
		} else if (param3 == TYPE_DEFAULT_BLACK) {
			graphics.beginFill(0);
			loc5.drawBevelRect(0, 0, loc4, graphics);
			graphics.endFill();
		}*/
	}

	public void divide(String param1, int param2) {
		if (param1.equals(HORIZONTAL_DIVISION)) {
			this.divideHorizontally(param2);
		} else if (param1.equals(VERTICAL_DIVISION)) {
			this.divideVertically(param2);
		}
	}

	private void divideHorizontally(int param1) {
		graphics.lineStyle();
		graphics.endFill();
		graphics.moveTo(1, param1);
		graphics.beginFill(6710886, 1);
		graphics.drawRect(1, param1, width - 2, 2);
	}

	private void divideVertically(int param1) {
		graphics.lineStyle();
		graphics.moveTo(param1, 1);
		graphics.lineStyle(2, 6710886);
		graphics.lineTo(param1, height - 1);
	}


}

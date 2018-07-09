package rotmg.account.web.view;

import flash.display.Sprite;

public class ProgressBar extends Sprite {

	private static final int BEVEL = 4;

	private double _w = 100;

	private double _h = 10;

	private Sprite backbar;

	private Sprite fillbar;

	public ProgressBar(double param1, double param2) {
		super();
		this._w = param1;
		this._h = param2;
		this.backbar = new Sprite();
		this.fillbar = new Sprite();
		addChild(this.backbar);
		addChild(this.fillbar);
		this.update(0);
	}

	public void update(double param1) {
		this.drawRectToSprite(this.fillbar, 16777215, param1 * 0.01 * this._w);
		this.drawRectToSprite(this.backbar, 0, this._w);
	}

	private Sprite drawRectToSprite(Sprite param1, int param2, double param3) {
		param1.graphics.clear();
		param1.graphics.beginFill(param2);
		param1.graphics.drawRect(0, 0, param3, this._h);
		param1.graphics.endFill();
		return param1;
	}


}

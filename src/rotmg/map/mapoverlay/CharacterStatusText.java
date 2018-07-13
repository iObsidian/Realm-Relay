package rotmg.map.mapoverlay;

import alde.flash.utils.SignalConsumer;
import flash.display.Bitmap;
import flash.display.BitmapData;
import flash.display.Sprite;
import flash.geom.Matrix;
import flash.geom.Point;
import rotmg.map.Camera;
import rotmg.objects.GameObject;
import rotmg.text.view.stringBuilder.StringBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import spark.filters.GlowFilter;

public class CharacterStatusText extends Sprite implements IMapOverlayElement {

	public final int MAX_DRIFT = 40;

	public GameObject go;

	public Point offset;

	public int color;

	public int lifetime;

	public int offsetTime;

	private int startTime = 0;

	private TextFieldDisplayConcrete textDisplay;

	public CharacterStatusText(GameObject param1, int param2, int param3) {
		this(param1, param2, param3, 0);
	}

	public CharacterStatusText(GameObject param1, int param2, int param3, int param4) {
		super();
		this.go = param1;
		this.offset = new Point(0, -param1.texture.height * (param1.size / 100) * 5 - 20);
		this.color = param2;
		this.lifetime = param3;
		this.offsetTime = param4;
		this.textDisplay = new TextFieldDisplayConcrete().setSize(24).setColor(param2).setBold(true);
		this.textDisplay.filters.set(new GlowFilter(0, 1, 4, 4, 2, 1));
		addChild(this.textDisplay);
		visible = false;
	}

	public boolean draw(Camera param1, int param2) {
		if (this.startTime == 0) {
			this.startTime = param2 + this.offsetTime;
		}
		if (param2 < this.startTime) {
			visible = false;
			return true;
		}
		int loc3 = param2 - this.startTime;
		if (loc3 > this.lifetime || this.go != null && this.go.map == null) {
			return false;
		}
		if (this.go == null || !this.go.drawn) {
			visible = false;
			return true;
		}
		visible = true;
		x = (this.go != null ? this.go.posS.get(0) : 0) + (this.offset != null ? this.offset.x : 0);
		double loc4 = loc3 / this.lifetime * this.MAX_DRIFT;
		y = (this.go != null ? this.go.posS.get(1) : 0) + (this.offset != null ? this.offset.y : 0) - loc4;
		return true;
	}

	public GameObject getGameObject() {
		return this.go;
	}

	public void dispose() {
		parent.removeChild(this);
	}

	public void setStringBuilder(StringBuilder param1) {
		this.textDisplay.textChanged.add(new SignalConsumer<>(this::onTextChanged));
		this.textDisplay.setStringBuilder(param1);
	}

	private void onTextChanged() {
		Bitmap loc2 = null;
		BitmapData loc1 = new BitmapData(this.textDisplay.width, this.textDisplay.height, true, 0);
		loc2 = new Bitmap(loc1);
		loc1.draw(this.textDisplay, new Matrix());
		loc2.x = (int) (loc2.x - loc2.width * 0.5);
		loc2.y = (int) (loc2.y - loc2.height * 0.5);
		addChild(loc2);
		removeChild(this.textDisplay);
		this.textDisplay = null;
	}


}

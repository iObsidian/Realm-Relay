package rotmg.model;

import rotmg.objects.GameObject;

public class AddSpeechBalloonVO {

	public GameObject go;

	public String text;

	public String name;

	public boolean isTrade;

	public boolean isGuild;

	public int background;

	public double backgroundAlpha;

	public int outline;

	public int outlineAlpha;

	public int textColor;

	public int lifetime;

	public boolean bold;

	public boolean hideable;

	public AddSpeechBalloonVO(GameObject param1, String param2, String param3, boolean param4, boolean param5, int param6, double param7, int param8, int param9, int param10, int param11, boolean param12, boolean param13) {
		super();
		this.go = param1;
		this.text = param2;
		this.name = param3;
		this.isTrade = param4;
		this.isGuild = param5;
		this.background = param6;
		this.backgroundAlpha = param7;
		this.outline = param8;
		this.outlineAlpha = param9;
		this.textColor = param10;
		this.lifetime = param11;
		this.bold = param12;
		this.hideable = param13;
	}


}

package realmrelay.game.map;

import realmrelay.game.XML;

public class AnimateProperties {

	public static final int NO_ANIMATE = 0;

	public static final int WAVE_ANIMATE = 1;

	public static final int FLOW_ANIMATE = 2;

	public AnimateProperties() {
		super();
	}

	public int type = 0;
	public float dx = 0;
	public float dy = 0;

	public void parseXML(XML param1) {
		switch (param1.name()) {
		case "Wave":
			this.type = WAVE_ANIMATE;
			break;
		case "Flow":
			this.type = FLOW_ANIMATE;
		}
		this.dx = param1.getFloatAttribute("dx");
		this.dy = param1.getFloatAttribute("dy");

	}

}

package rotmg.map;

import alde.flash.utils.XML;

public class AnimateProperties {

	public static final int NO_ANIMATE = 0;

	public static final int WAVE_ANIMATE = 1;

	public static final int FLOW_ANIMATE = 2;
	public int type = 0;
	public double dx = 0;
	public double dy = 0;

	public AnimateProperties() {
		super();
	}

	public void parseXML(XML param1) {
		switch (param1.name()) {
			case "Wave":
				this.type = WAVE_ANIMATE;
				break;
			case "Flow":
				this.type = FLOW_ANIMATE;
		}
		this.dx = param1.getDoubleAttribute("dx");
		this.dy = param1.getDoubleAttribute("dy");

	}

}

package rotmg.map;

import alde.flash.utils.XML;
import flash.display.BitmapData;
import rotmg.objects.TextureData;
import rotmg.objects.TextureDataConcrete;

import java.util.List;

/**
 * 75% done : requires AnimateProperties
 */
public class GroundProperties {

	public int type;
	public String id;
	public String displayId;
	public boolean noWalk = true;
	public int minDamage = 0;
	public int maxDamage = 0;
	public AnimateProperties animate;
	public int blendPriority = -1;
	public int compositePriority = 0;
	public double speed = 1.0F;
	public double xOffset = 0;
	public double yOffset = 0;
	public double slideAmount = 0;
	public boolean push = false;
	public boolean sink = false;
	public boolean sinking = false;
	public boolean randomOffset = false;
	public boolean hasEdge = false;
	public boolean sameTypeEdgeMode = false;
	public TextureData topTD = null;
	public AnimateProperties topAnimate = null;
	private TextureData edgeTD = null;
	private TextureData cornerTD = null;
	private TextureData innerCornerTD = null;
	private List<BitmapData> edges = null;
	private List<BitmapData> innerCorners = null;

	public GroundProperties(XML xml) {
		this.animate = new AnimateProperties();
		this.type = xml.getIntAttribute("type");
		this.displayId = this.id = xml.getAttribute("id");

		if (xml.hasOwnProperty("DisplayId")) {
			this.displayId = xml.getValue("DisplayId");
		}
		this.id = xml.getAttribute("id");
		this.noWalk = xml.hasOwnProperty("NoWalk");
		if (xml.hasOwnProperty("MinDamage")) {
			this.minDamage = xml.getIntValue("MinDamage");
		}
		if (xml.hasOwnProperty("MaxDamage")) {
			this.maxDamage = xml.getIntValue("MaxDamage");
		}
		this.push = xml.hasOwnProperty("Push");

		if (xml.hasOwnProperty("Animate")) {
			this.animate.parseXML(xml.child("Animate"));
		}

		if (xml.hasOwnProperty("BlendPriority")) {
			this.blendPriority = xml.getIntValue("BlendPriority");
		}
		if (xml.hasOwnProperty("CompositePriority")) {
			this.compositePriority = xml.getIntValue("CompositePriority");
		}
		if (xml.hasOwnProperty("Speed")) {
			this.speed = xml.getDoubleValue("Speed");
		}
		if (xml.hasOwnProperty("SlideAmount")) {
			this.slideAmount = xml.getDoubleValue("SlideAmount");
		}
		this.xOffset = xml.hasOwnProperty("XOffset") ? xml.getDoubleValue("XOffset") : 0;
		this.yOffset = xml.hasOwnProperty("YOffset") ? xml.getDoubleValue("YOffset") : 0;
		this.push = xml.hasOwnProperty("Push");
		this.sink = xml.hasOwnProperty("Sink");
		this.sinking = xml.hasOwnProperty("Sinking");
		this.randomOffset = xml.hasOwnProperty("RandomOffset");
		if (xml.hasOwnProperty("Edge")) {
			this.hasEdge = true;
			this.edgeTD = new TextureDataConcrete(xml.child("Edge"));
			if (xml.hasOwnProperty("Corner")) {
				this.cornerTD = new TextureDataConcrete(xml.child("Corner"));
			}
			if (xml.hasOwnProperty("InnerCorner")) {
				this.innerCornerTD = new TextureDataConcrete(xml.child("InnerCorner"));
			}
		}
		this.sameTypeEdgeMode = xml.hasOwnProperty("SameTypeEdgeMode");
		if (xml.hasOwnProperty("Top")) {
			this.topTD = new TextureDataConcrete(xml.child("Top"));
			this.topAnimate = new AnimateProperties();
			if (xml.hasOwnProperty("TopAnimate")) {
				this.topAnimate.parseXML(xml.child("TopAnimate"));
			}
		}
	}

	@Override
	public String toString() {
		return "GroundProperties{" + "type=" + type + ", id='" + id + '}';
	}
}

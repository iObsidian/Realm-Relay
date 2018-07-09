package rotmg.objects;

import alde.flash.utils.XML;

public class EffectProperties {

	public String id;
	public String particle;
	public double cooldown;
	public int color;
	public int color2;
	public double rate;
	public double speed;
	public double speedVariance;
	public double spread;
	public double life;
	public double lifeVariance;
	public int size;
	public double friction;
	public double rise;
	public double riseVariance;
	public double riseAcc;
	public int rangeX;
	public int rangeY;
	public double zOffset;
	public double minRadius;
	public double maxRadius;
	public int amount;
	public String bitmapFile;
	public int bitmapIndex;

	public EffectProperties(XML xml) {
		this.id = xml.toString();
		this.particle = xml.getAttribute("particle");
		this.cooldown = xml.getDoubleAttribute("cooldown");

		this.color = xml.getIntValue("color");
		this.color2 = xml.getIntAttribute("color2");
		this.rate = xml.getDoubleAttribute("rate", 5F);
		this.speed = xml.getDoubleAttribute("speed", 0F);
		this.speedVariance = xml.getDoubleAttribute("speedVariance", 0.5F);
		this.spread = xml.getDoubleAttribute("spread", 0F);
		this.life = xml.getDoubleAttribute("life", 1F);
		this.lifeVariance = xml.getDoubleAttribute("lifeVariance", 0F);
		this.size = xml.getIntAttribute("size", 3);
		this.rise = xml.getDoubleAttribute("rise", 3F);
		this.riseVariance = xml.getDoubleAttribute("riseVariance", 0F);
		this.riseAcc = xml.getDoubleAttribute("riseAcc", 0F);
		this.rangeX = xml.getIntAttribute("rangeX", 0);
		this.rangeY = xml.getIntAttribute("rangeY", 0);
		this.zOffset = xml.getDoubleAttribute("zOffset", 0F);
		this.minRadius = xml.getDoubleAttribute("minRadius", 0F);
		this.maxRadius = xml.getDoubleAttribute("maxRadius", 1F);
		this.amount = xml.getIntAttribute("amount", 1);
		this.bitmapFile = xml.getAttribute("bitmapFile");
		this.bitmapIndex = xml.getIntAttribute("bitmapIndex");
	}

}

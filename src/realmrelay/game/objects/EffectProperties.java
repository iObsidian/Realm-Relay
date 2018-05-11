package realmrelay.game.objects;

import realmrelay.game.XML;

public class EffectProperties {

	public EffectProperties(XML xml) {
		this.id = xml.toString();
		this.particle = xml.getAttribute("particle");
		this.cooldown = xml.getFloatAttribute("cooldown");
		this.color = xml.getIntValue("color");
		this.color2 = xml.getIntAttribute("color2");
		this.rate = xml.getFloatAttribute("rate", 5F);
		this.speed = xml.getFloatAttribute("speed", 0F);
		this.speedVariance = xml.getFloatAttribute("speedVariance", 0.5F);
		this.spread = xml.getFloatAttribute("spread", 0F);
		this.life = xml.getFloatAttribute("life", 1F);
		this.lifeVariance = xml.getFloatAttribute("lifeVariance", 0F);
		this.size = xml.getIntAttribute("size", 3);
		this.rise = xml.getFloatAttribute("rise", 3F);
		this.riseVariance = xml.getFloatAttribute("riseVariance", 0F);
		this.riseAcc = xml.getFloatAttribute("riseAcc", 0F);
		this.rangeX = xml.getIntAttribute("rangeX", 0);
		this.rangeY = xml.getIntAttribute("rangeY", 0);
		this.zOffset = xml.getFloatAttribute("zOffset", 0F);
		this.minRadius = xml.getFloatAttribute("minRadius", 0F);
		this.maxRadius = xml.getFloatAttribute("maxRadius", 1F);
		this.amount = xml.getIntAttribute("amount", 1);
		this.bitmapFile = xml.getAttribute("bitmapFile");
		this.bitmapIndex = xml.getIntAttribute("bitmapIndex");
	}

	public String id;
	public String particle;
	public float cooldown;
	public int color;
	public int color2;
	public float rate;
	public float speed;
	public float speedVariance;
	public float spread;
	public float life;
	public float lifeVariance;
	public int size;
	public float friction;
	public float rise;
	public float riseVariance;
	public float riseAcc;
	public int rangeX;
	public int rangeY;
	public float zOffset;
	public float minRadius;
	public float maxRadius;
	public int amount;
	public String bitmapFile;
	public int bitmapIndex;

}

package rotmg.objects;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import flash.utils.Dictionary;
import rotmg.util.ConditionEffect;

/**
 * This is a VERY close match to the client.
 * <p>
 * Only thing is XML attribute '@intensity' is gotten as a Value. (it's like that in the client)
 */
public class ProjectileProperties {

	public int bulletType;

	public String objectId;

	public int lifetime;

	public int speed;

	public int size;

	public int minDamage;

	public int maxDamage;

	public Vector<Integer> effects = null;

	public boolean multiHit;

	public boolean passesCover;

	public boolean armorPiercing;

	public boolean particleTrail;

	public int particleTrailIntensity = -1;

	public int particleTrailLifetimeMS = -1;

	public int particleTrailColor = 16711935;

	public boolean wavy;

	public boolean parametric;

	public boolean boomerang;

	public double amplitude;

	public double frequency;

	public double magnitude;

	public Dictionary<Integer, Boolean> isPetEffect;

	public boolean faceDir;

	public boolean noRotation;


	public ProjectileProperties(XML param1) {
		this.bulletType = param1.getIntAttribute("id");
		this.objectId = param1.getValue("ObjectId");
		this.lifetime = param1.getIntValue("LifetimeMS");
		this.speed = param1.getIntValue("Speed");
		this.size = param1.hasOwnProperty("Size") ? param1.getIntValue("Size") : -1;
		if (param1.hasOwnProperty("Damage")) {
			this.minDamage = this.maxDamage = param1.getIntValue("Damage");
		} else {
			this.minDamage = param1.getIntValue("MinDamage");
			this.maxDamage = param1.getIntValue("MaxDamage");
		}
		for (XML xml : param1.children("ConditionEffect")) {
			if (this.effects == null) {
				this.effects = new Vector<>();
			}
			this.effects.add(ConditionEffect.getConditionEffectFromName(xml.getTextValue()));
			if (xml.getAttribute("target").equals("1")) {
				if (this.isPetEffect == null) {
					this.isPetEffect = new Dictionary<>();
				}
				this.isPetEffect.put(ConditionEffect.getConditionEffectFromName(xml.getTextValue()), true);
			}
		}
		this.multiHit = param1.hasOwnProperty("MultiHit");
		this.passesCover = param1.hasOwnProperty("PassesCover");
		this.armorPiercing = param1.hasOwnProperty("ArmorPiercing");
		this.particleTrail = param1.hasOwnProperty("ParticleTrail");
		if (param1.child("ParticleTrail") != null) {

			XML particleTrail = param1.child("ParticleTrail");

			if (particleTrail.hasOwnProperty("intensity")) {
				this.particleTrailIntensity = param1.child("ParticleTrail").getIntAttribute("intensity") * 100;
			}

			if (particleTrail.hasOwnProperty("lifetimeMS")) {
				this.particleTrailLifetimeMS = param1.child("ParticleTrail").getIntAttribute("lifetimeMS");
			}
		}
		this.particleTrailColor = this.particleTrail ? param1.getIntValue("ParticleTrail") : 16711935;
		if (this.particleTrailColor == 0) {
			this.particleTrailColor = 16711935;
		}
		this.wavy = param1.hasOwnProperty("Wavy");
		this.parametric = param1.hasOwnProperty("Parametric");
		this.boomerang = param1.hasOwnProperty("Boomerang");
		this.amplitude = param1.hasOwnProperty("Amplitude") ? param1.getIntValue("Amplitude") : 0;
		this.frequency = param1.hasOwnProperty("Frequency") ? param1.getIntValue("Frequency") : 1;
		this.magnitude = param1.hasOwnProperty("Magnitude") ? param1.getIntValue("Magnitude") : 3;
		this.faceDir = param1.hasOwnProperty("FaceDir");
		this.noRotation = param1.hasOwnProperty("NoRotation");
	}


}

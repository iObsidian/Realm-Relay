package rotmg.objects;

import alde.flash.utils.XML;
import flash.utils.Dictionary;
import rotmg.sound.SoundEffectLibrary;

/**
 * Static is a reserved keywor.d Changed to 'isStatic'.
 * <p>
 * This class is a 100% match
 *
 * @author Alde
 */
public class ObjectProperties {

	public int type;
	public String id;
	public String displayId;
	public int shadowSize;
	public boolean isPlayer = false;
	public boolean isEnemy = false;
	public boolean drawOnGround = false;
	public boolean drawUnder = false;
	public boolean occupySquare = false;
	public boolean fullOccupy = false;
	public boolean enemyOccupySquare = false;
	public boolean isStatic = false; // from 'static' (utils keyword)
	public boolean noMiniMap = false;
	public boolean noHealthBar = false;
	public int healthBar = 0;
	public boolean protectFromGroundDamage = false;
	public boolean protectFromSink = false;
	public double z = 0;
	public boolean flying = false;
	public int color = -1;
	public boolean showName = false;
	public boolean dontFaceAttacks = false;
	public boolean dontFaceMovement = false;
	public double bloodProb = 0.0F;
	public int bloodColor = 16711680;
	public int shadowColor = 0;
	public Dictionary<Integer, String> sounds; // ID, Sound name
	public TextureData portrait = null;
	public int minSize = 100;
	public int maxSize = 100;
	public int sizeStep = 5;
	public WhileMovingProperties whileMoving = null;
	public String belonedDungeon = "";
	public String oldSound = null;
	public Dictionary<Integer, ProjectileProperties> projectiles; // ID, Properties
	public double angleCorrection = 0;
	public double rotation = 0;

	public ObjectProperties(XML param1) {
		this.projectiles = new Dictionary<>();

		if (param1 == null) {
			return;
		}

		this.type = param1.getIntAttribute("type");
		this.id = param1.getAttribute("id");
		this.displayId = this.id;

		if (param1.hasOwnProperty("DisplayId")) {
			this.displayId = param1.getValue("DisplayId");
		}

		this.shadowSize = param1.hasOwnProperty("ShadowSize") ? param1.getIntValue("ShadowSize") : 100;
		this.isPlayer = param1.hasOwnProperty("Player");
		this.isEnemy = param1.hasOwnProperty("Enemy");
		this.drawOnGround = param1.hasOwnProperty("DrawOnGround");
		if (this.drawOnGround || param1.hasOwnProperty("DrawUnder")) {
			this.drawUnder = true;
		}
		this.occupySquare = param1.hasOwnProperty("OccupySquare");
		this.fullOccupy = param1.hasOwnProperty("FullOccupy");
		this.enemyOccupySquare = param1.hasOwnProperty("EnemyOccupySquare");
		this.isStatic = param1.hasOwnProperty("Static");
		this.noMiniMap = param1.hasOwnProperty("NoMiniMap");
		this.noHealthBar = param1.hasOwnProperty("NoHealthBar");
		if (param1.hasOwnProperty("HealthBar")) {
			this.healthBar = param1.getIntValue("HealthBar");
		}
		this.protectFromGroundDamage = param1.hasOwnProperty("ProtectFromGroundDamage");
		this.protectFromSink = param1.hasOwnProperty("ProtectFromSink");
		this.flying = param1.hasOwnProperty("Flying");
		this.showName = param1.hasOwnProperty("ShowName");
		this.dontFaceAttacks = param1.hasOwnProperty("DontFaceAttacks");
		this.dontFaceMovement = param1.hasOwnProperty("DontFaceMovement");
		if (param1.hasOwnProperty("Z")) {
			this.z = param1.getDoubleValue("Z");
		}
		if (param1.hasOwnProperty("Color")) {
			this.color = param1.getIntValue("Color");
		}
		if (param1.hasOwnProperty("Size")) {
			this.minSize = this.maxSize = param1.getIntValue("Size");
		} else {
			if (param1.hasOwnProperty("MinSize")) {
				this.minSize = param1.getIntValue("MinSize");
			}
			if (param1.hasOwnProperty("MaxSize")) {
				this.maxSize = param1.getIntValue("MaxSize");
			}
			if (param1.hasOwnProperty("SizeStep")) {
				this.sizeStep = param1.getIntValue("SizeStep");
			}
		}
		this.oldSound = param1.hasOwnProperty("OldSound") ? param1.getValue("OldSound") : null;
		for (XML projectile : param1.children("Projectile")) {
			this.projectiles.put(projectile.getIntAttribute("id"), new ProjectileProperties(projectile));
		}
		this.angleCorrection = param1.hasOwnProperty("AngleCorrection")
				? (double) (param1.getDoubleValue("AngleCorrection") * Math.PI / 4)
				: 0;

		this.rotation = param1.hasOwnProperty("Rotation") ? param1.getDoubleValue("Rotation") : 0;

		if (param1.hasOwnProperty("BloodProb")) {
			this.bloodProb = param1.getDoubleValue("BloodProb");
		}
		if (param1.hasOwnProperty("BloodColor")) {
			this.bloodColor = param1.getIntValue("BloodColor");
		}
		if (param1.hasOwnProperty("ShadowColor")) {
			this.shadowColor = param1.getIntValue("ShadowColor");
		}

		for (XML loc : param1.children("Sound")) {
			if (this.sounds == null) {
				this.sounds = new Dictionary<>();
			}
			this.sounds.put(loc.getIntAttribute("id"), loc.element.getNodeValue());
		}

		if (param1.hasOwnProperty("Portrait")) {
			this.portrait = new TextureDataConcrete(param1.child("Portrait"));
		}
		if (param1.hasOwnProperty("WhileMoving")) {
			this.whileMoving = new WhileMovingProperties(param1.child("WhileMoving"));
		}
	}

	// Load sounds from RotMG API
	public void loadSounds() {
		if (this.sounds == null) {
			return;
		}
		for (String sound : this.sounds.values()) {
			SoundEffectLibrary.load(sound);
		}
	}

	public int getSize() {
		if (this.minSize == this.maxSize) {
			return this.minSize;
		}
		int size = (this.maxSize - this.minSize) / this.sizeStep;
		return this.minSize + (int) (Math.random() * size) * this.sizeStep;
	}


	@Override
	public String toString() {
		return "ObjectProperties{" +
				"type=" + type +
				", id='" + id + '\'' +
				'}';
	}
}

class WhileMovingProperties {

	public double z = 0.0F;

	public boolean flying = false;

	WhileMovingProperties(XML param1) {
		if (param1.hasOwnProperty("Z")) {
			this.z = param1.getDoubleValue("Z");
		}
		this.flying = param1.hasOwnProperty("Flying");
	}
}

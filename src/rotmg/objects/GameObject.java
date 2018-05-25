package rotmg.objects;

import alde.flash.utils.XML;
import flash.display.*;
import flash.geom.Point;
import flash.geom.Vector3D;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import rotmg.messaging.data.WorldPosData;
import rotmg.objects.*;
import rotmg.objects.animation.AnimatedChar;
import rotmg.objects.animation.Animations;
import rotmg.objects.animation.AnimationsData;
import rotmg.parameters.Parameters;
import rotmg.particles.ParticleEffect;
import rotmg.sound.SoundEffectLibrary;
import rotmg.ui.SimpleText;
import rotmg.util.*;

import java.util.List;

public class GameObject extends BasicObject {

	private static final double ZERO_LIMIT = 0.00001;

	private static final double NEGATIVE_ZERO_LIMIT = -ZERO_LIMIT;

	//protected static final ColorMatrixFilter PAUSED_FILTER = new ColorMatrixFilter(com.company.util.MoreColorUtil.greyscaleFilterMatrix);

	public static final int ATTACK_PERIOD = 300;

	public ObjectProperties props;

	public String name;

	public double radius = 0.5;

	public double facing = 0;

	public boolean flying = false;

	public double attackAngle = 0;

	public int attackStart = 0;

	public AnimatedChar animatedChar = null;

	public BitmapData texture = null;

	public BitmapData mask = null;

	public List<TextureData> randomTextureData = null;

	public ParticleEffect effect = null;

	public Animations animations = null;

	public boolean dead = false;

	protected BitmapData portrait = null;

	public int maxHP = 200;

	public int hp = 200;

	public int size = 100;

	public int level = -1;

	public int defense = 0;

	public List<Integer> slotTypes = null;

	public List<Integer> equipment = null;

	public int condition = 0;

	protected int tex1Id = 0;

	protected int tex2Id = 0;

	public boolean isInteractive = false;

	public int objectType;

	private int nextBulletId = 1;

	private double sizeMult = 1;

	public int sinkLevel = 0;

	public BitmapData hallucinatingTexture = null;

	public int connectType = -1;

	protected int lastTickUpdateTime = 0;

	protected int myLastTickId = -1;

	protected Point posAtTick;

	protected Point tickPosition;

	protected Vector3D moveVec;

	public SimpleText nameText = null;

	public BitmapData nameBitmapData = null;

	private GraphicsBitmapFill nameFill = null;

	private GraphicsPath namePath = null;

	protected GraphicsBitmapFill bitmapFill;

	protected GraphicsPath path;

	protected List<Double> vS;

	protected List<Double> uvt;

	protected Matrix fillMatrix;

	private List<BitmapData> icons = null;

	private List<GraphicsBitmapFill> iconFills = null;

	private List<GraphicsPath> iconPaths = null;

	protected GraphicsGradientFill shadowGradientFill = null;

	protected GraphicsPath shadowPath = null;

	public GameObject(XML objectXML) {
		int i = 0;
		this.props = ObjectLibrary.defaultProps;
		this.posAtTick = new Point();
		this.tickPosition = new Point();
		this.moveVec = new Vector3D();
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.vS = new List<Double>();
		this.uvt = new List<Double>();
		this.fillMatrix = new Matrix();
		super();
		if (objectXML == null) {
			return;
		}
		this.objectType = int(objectXML. @type);
		this.props = ObjectLibrary.propsLibrary[this.objectType];
		hasShadow = this.props.shadowSize > 0;
		TextureData textureData = ObjectLibrary.typeToTextureData[this.objectType];
		this.texture = textureData.texture;
		this.mask = textureData.mask;
		this.animatedChar = textureData.animatedChar;
		this.randomTextureData = textureData.randomTextureData;
		if (textureData.effectProps != null) {
			this.effect = ParticleEffect.fromProps(textureData.effectProps, this);
		}
		if (this.texture != null) {
			this.sizeMult = this.texture.height / 8;
		}
		if (objectXML.hasOwnProperty("Model")) {
			this.obj3D = Model3D.getObject3D(String(objectXML.Model));
		}
		AnimationsData animationsData = ObjectLibrary.typeToAnimationsData[this.objectType];
		if (animationsData != null) {
			this.animations = new Animations(animationsData);
		}
		z = this.props.z;
		this.flying = this.props.flying;
		if (objectXML.hasOwnProperty("MaxHitPoints")) {
			this.hp = this.maxHP = objectXML.MaxHitPoints;
		}
		if (objectXML.hasOwnProperty("Defense")) {
			this.defense = objectXML.Defense;
		}
		if (objectXML.hasOwnProperty("SlotTypes")) {
			this.slotTypes = ConversionUtil.toIntVector(objectXML.SlotTypes);
			this.equipment = new List<Integer>(this.slotTypes.length);
			for (i = 0; i < this.equipment.length; i++) {
				this.equipment[i] = -1;
			}
		}
		if (objectXML.hasOwnProperty("Tex1")) {
			this.tex1Id = objectXML.Tex1;
		}
		if (objectXML.hasOwnProperty("Tex2")) {
			this.tex2Id = objectXML.Tex2;
		}
		this.props.loadSounds();
	}

	public int damageWithDefense(int origDamage, int targetDefense, boolean armorPiercing, int targetCondition) {
		int def = targetDefense;
		if (armorPiercing || (targetCondition & ConditionEffect.ARMORBROKEN_BIT) != 0) {
			def = 0;
		} else if ((targetCondition & ConditionEffect.ARMORED_BIT) != 0) {
			def = def * 2;
		}
		int min = origDamage * 3 / 20;
		int d = Math.max(min, origDamage - def);
		if ((targetCondition & ConditionEffect.INVULNERABLE_BIT) != 0) {
			d = 0;
		}
		return d;
	}

	public void setObjectId(int objectId) {
		TextureData textureData = null;
		objectId = objectId;
		if (this.randomTextureData != null) {
			textureData = this.randomTextureData.get(objectId % this.randomTextureData.size());
			this.texture = textureData.texture;
			this.mask = textureData.mask;
			this.animatedChar = textureData.animatedChar;
		}
	}

	public void setAltTexture(int altTextureId) {
		TextureData altTextureData = null;
		TextureData textureData = ObjectLibrary.typeToTextureData.get(this.objectType);
		if (altTextureId == 0) {
			altTextureData = textureData;
		} else {
			altTextureData = textureData.getAltTextureData(altTextureId);
			if (altTextureData == null) {
				return;
			}
		}
		this.texture = altTextureData.texture;
		this.mask = altTextureData.mask;
		this.animatedChar = altTextureData.animatedChar;
		if (this.effect != null) {
			map.removeObj(this.effect.objectId);
			this.effect = null;
		}
		if (altTextureData.effectProps != null) {
			this.effect = ParticleEffect.fromProps(altTextureData.effectProps, this);
			if (map != null) {
				map.addObj(this.effect, x, y);
			}
		}
	}

	public void setTex1(int tex1Id) {
		if (tex1Id == this.tex1Id) {
			return;
		}
		this.tex1Id = tex1Id;
		this.texturingCache = new Dictionary();
		this.portrait = null;
	}

	public void setTex2(int tex2Id) {
		if (tex2Id == this.tex2Id) {
			return;
		}
		this.tex2Id = tex2Id;
		this.texturingCache = new Dictionary();
		this.portrait = null;
	}

	public void playSound(int id) {
		SoundEffectLibrary.play(this.props.sounds[id]);
	}

	public void dispose() {
		Object obj = null;
		BitmapData bitmapData = null;
		Dictionary dict = null;
		Object obj2 = null;
		BitmapData bitmapData2 = null;
		super.dispose();
		this.texture = null;
		if (this.portrait != null) {
			this.portrait.dispose();
			this.portrait = null;
		}
		if (this.texturingCache != null) {
			for (obj:
			     this.texturingCache) {
				bitmapData = obj as BitmapData;
				if (bitmapData != null) {
					bitmapData.dispose();
				} else {
					dict = obj as Dictionary;
					for (obj2:
					     dict) {
						bitmapData2 = obj2 as BitmapData;
						if (bitmapData2 != null) {
							bitmapData2.dispose();
						}
					}
				}
			}
			this.texturingCache = null;
		}
		if (this.obj3D != null) {
			this.obj3D.dispose();
			this.obj3D = null;
		}
		this.slotTypes = null;
		this.equipment = null;
		this.nameText = null;
		if (this.nameBitmapData != null) {
			this.nameBitmapData.dispose();
			this.nameBitmapData = null;
		}
		this.nameFill = null;
		this.namePath = null;
		this.bitmapFill = null;
		this.path.commands = null;
		this.path.data = null;
		this.vS = null;
		this.uvt = null;
		this.fillMatrix = null;
		this.icons = null;
		this.iconFills = null;
		this.iconPaths = null;
		this.shadowGradientFill = null;
		if (this.shadowPath != null) {
			this.shadowPath.commands = null;
			this.shadowPath.data = null;
			this.shadowPath = null;
		}
	}

	public boolean isQuiet() {
		return (this.condition & ConditionEffect.QUIET_BIT) != 0;
	}

	public boolean isWeak() {
		return (this.condition & ConditionEffect.WEAK_BIT) != 0;
	}

	public boolean isSlowed() {
		return (this.condition & ConditionEffect.SLOWED_BIT) != 0;
	}

	public boolean isSick() {
		return (this.condition & ConditionEffect.SICK_BIT) != 0;
	}

	public boolean isDazed() {
		return (this.condition & ConditionEffect.DAZED_BIT) != 0;
	}

	public boolean isStunned() {
		return (this.condition & ConditionEffect.STUNNED_BIT) != 0;
	}

	public boolean isBlind() {
		return (this.condition & ConditionEffect.BLIND_BIT) != 0;
	}

	public boolean isDrunk() {
		return (this.condition & ConditionEffect.DRUNK_BIT) != 0;
	}

	public boolean isConfused() {
		return (this.condition & ConditionEffect.CONFUSED_BIT) != 0;
	}

	public boolean isStunImmune() {
		return (this.condition & ConditionEffect.STUN_IMMUNE_BIT) != 0;
	}

	public boolean isInvisible() {
		return (this.condition & ConditionEffect.INVISIBLE_BIT) != 0;
	}

	public boolean isParalyzed() {
		return (this.condition & ConditionEffect.PARALYZED_BIT) != 0;
	}

	public boolean isSpeedy() {
		return (this.condition & ConditionEffect.SPEEDY_BIT) != 0;
	}

	public boolean isNinjaSpeedy() {
		return (this.condition & ConditionEffect.NINJA_SPEEDY_BIT) != 0;
	}

	public boolean isHallucinating() {
		return (this.condition & ConditionEffect.HALLUCINATING_BIT) != 0;
	}

	public boolean isHealing() {
		return (this.condition & ConditionEffect.HEALING_BIT) != 0;
	}

	public boolean isDamaging() {
		return (this.condition & ConditionEffect.DAMAGING_BIT) != 0;
	}

	public boolean isBerserk() {
		return (this.condition & ConditionEffect.BERSERK_BIT) != 0;
	}

	public boolean isPaused() {
		return (this.condition & ConditionEffect.PAUSED_BIT) != 0;
	}

	public boolean isStasis() {
		return (this.condition & ConditionEffect.STASIS_BIT) != 0;
	}

	public boolean isInvincible() {
		return (this.condition & ConditionEffect.INVINCIBLE_BIT) != 0;
	}

	public boolean isInvulnerable() {
		return (this.condition & ConditionEffect.INVULNERABLE_BIT) != 0;
	}

	public boolean isArmored() {
		return (this.condition & ConditionEffect.ARMORED_BIT) != 0;
	}

	public boolean isArmorBroken() {
		return (this.condition & ConditionEffect.ARMORBROKEN_BIT) != 0;
	}

	public function isSafe(size:int=20) :boolean

	{
		GameObject go = null;
		int dx = 0;
		int dy = 0;
		for (go:
		     map.goDict) {
			if (go is Character &&go.props.isEnemy)
			{
				dx = x > go.x_ ?int(x - go.x):int(go.x - x);
				dy = y > go.y_ ?int(y - go.y):int(go.y - y);
				trace((go as Character).objectType, dx, dy);
				if (dx < size && dy < size) {
					return false;
				}
			}
		}
		return true;
	}

	public String getName() {
		return this.name == null || this.name == "" ? ObjectLibrary.typeToDisplayId[this.objectType] : this.name;
	}

	public int getColor() {
		return BitmapUtil.mostCommonColor(this.texture);
	}

	public int getBulletId() {
		int ret = this.nextBulletId;
		this.nextBulletId = (this.nextBulletId + 1) % 128;
		return ret;
	}

	public Number distTo(WorldPosData pos) {
		double dx = pos.x - x;
		double dy = pos.y - y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public boolean addTo(Map map, Number x, Number y) {
		map = map;
		this.posAtTick.x = this.tickPosition.x = x;
		this.posAtTick.y = this.tickPosition.y = y;
		if (!this.moveTo(x, y)) {
			map = null;
			return false;
		}
		if (this.effect != null) {
			map.addObj(this.effect, x, y);
		}
		return true;
	}

	public void removeFromMap() {
		if (this.props. static &&square != null)
		{
			if (square.obj == this) {
				square.obj = null;
			}
			square = null;
		}
		if (this.effect != null) {
			map.removeObj(this.effect.objectId);
		}
		super.removeFromMap();
		this.dispose();
	}

	public boolean moveTo(Number x, Number y) {
		Square square = map.getSquare(x, y);
		if (square == null) {
			return false;
		}
		x = x;
		y = y;
		if (this.props. static)
		{
			if (square != null) {
				square.obj = null;
			}
			square.obj = this;
		}
		square = square;
		if (this.obj3D != null) {
			this.obj3D.setPosition(x, y, 0, this.props.rotation);
		}
		return true;
	}

	public boolean update(int time, int dt) {
		int tickDT = 0;
		double pX = NaN;
		double pY = NaN;
		boolean moving = false;
		if (!(this.moveVec.x == 0 && this.moveVec.y == 0)) {
			if (this.myLastTickId < map.gs.gsc.lastTickId) {
				this.moveVec.x = 0;
				this.moveVec.y = 0;
				this.moveTo(this.tickPosition.x, this.tickPosition.y);
			} else {
				tickDT = time - this.lastTickUpdateTime;
				pX = this.posAtTick.x + tickDT * this.moveVec.x;
				pY = this.posAtTick.y + tickDT * this.moveVec.y;
				this.moveTo(pX, pY);
				moving = true;
			}
		}
		if (this.props.whileMoving != null) {
			if (!moving) {
				z = this.props.z;
				this.flying = this.props.flying;
			} else {
				z = this.props.whileMoving.z;
				this.flying = this.props.whileMoving.flying;
			}
		}
		return true;
	}

	public void onGoto(Number x, Number y, int time) {
		this.moveTo(x, y);
		this.lastTickUpdateTime = time;
		this.tickPosition.x = x;
		this.tickPosition.y = y;
		this.posAtTick.x = x;
		this.posAtTick.y = y;
		this.moveVec.x = 0;
		this.moveVec.y = 0;
	}

	public void onTickPos(Number x, Number y, int tickTime, int tickId) {
		if (this.myLastTickId < map.gs.gsc.lastTickId) {
			this.moveTo(this.tickPosition.x, this.tickPosition.y);
		}
		this.lastTickUpdateTime = map.gs.lastUpdate;
		this.tickPosition.x = x;
		this.tickPosition.y = y;
		this.posAtTick.x = x;
		this.posAtTick.y = y;
		this.moveVec.x = (this.tickPosition.x - this.posAtTick.x) / tickTime;
		this.moveVec.y = (this.tickPosition.y - this.posAtTick.y) / tickTime;
		this.myLastTickId = tickId;
	}

	public void damage(int origType, int damageAmount, List<Integer> effects, boolean kill, Projectile proj) {
		int offsetTime = 0;
		int conditionEffect = 0;
		ConditionEffect ce = null;
		boolean pierced = false;
		if (kill) {
			this.dead = true;
		} else if (effects != null) {
			offsetTime = 0;
			for (conditionEffect:
			     effects) {
				ce = null;
				switch (conditionEffect) {
					case ConditionEffect.NOTHING:
						break;
					case ConditionEffect.QUIET:
					case ConditionEffect.WEAK:
					case ConditionEffect.SLOWED:
					case ConditionEffect.SICK:
					case ConditionEffect.DAZED:
					case ConditionEffect.BLIND:
					case ConditionEffect.HALLUCINATING:
					case ConditionEffect.DRUNK:
					case ConditionEffect.CONFUSED:
					case ConditionEffect.STUN_IMMUNE:
					case ConditionEffect.INVISIBLE:
					case ConditionEffect.PARALYZED:
					case ConditionEffect.SPEEDY:
					case ConditionEffect.BLEEDING:
					case ConditionEffect.STASIS:
					case ConditionEffect.STASIS_IMMUNE:
					case ConditionEffect.ARMORBROKEN:
					case ConditionEffect.NINJA_SPEEDY:
						ce = ConditionEffect.effects[conditionEffect];
						break;
					case ConditionEffect.STUNNED:
						if (this.isStunImmune()) {
							map.mapOverlay.addStatusText(new CharacterStatusText(this, "Immune", 16711680, 3000));
						} else {
							ce = ConditionEffect.effects[conditionEffect];
						}
				}
				if (ce != null) {
					if ((this.condition | ce.bit) != this.condition) {
						this.condition = this.condition | ce.bit;
						map.mapOverlay.addStatusText(new CharacterStatusText(this, ce.name, 16711680, 3000, offsetTime));
						offsetTime = offsetTime + 500;
					}
				}
			}
		}
		List<Integer> colors = BloodComposition.getBloodComposition(this.objectType, this.texture, this.props.bloodProb, this.props.bloodColor);
		if (this.dead) {
			map.addObj(new ExplosionEffect(colors, this.size, 30), x, y);
		} else if (proj != null) {
			map.addObj(new HitEffect(colors, this.size, 10, proj.angle, proj.projProps.speed), x, y);
		} else {
			map.addObj(new ExplosionEffect(colors, this.size, 10), x, y);
		}
		if (damageAmount > 0) {
			pierced = this.isArmorBroken() || proj != null && proj.projProps.armorPiercing;
			map.mapOverlay.addStatusText(new CharacterStatusText(this, "-" + damageAmount, !!pierced ?int(9437439):int
			(16711680), 1000));
		}
	}

	protected SimpleText generateNameText(String name) {
		SimpleText nameText = new SimpleText(16, 16777215, false, 0, 0, "Myriad Pro");
		nameText.setBold(true);
		nameText.text = name;
		nameText.updateMetrics();
		return nameText;
	}

	protected BitmapData generateNameBitmapData(SimpleText nameText) {
		BitmapData nameBitmapData = new BitmapData(nameText.width, 64, true, 0);
		nameBitmapData.draw(nameText, null);
		nameBitmapData.applyFilter(nameBitmapData, nameBitmapData.rect, PointUtil.ORIGIN, new GlowFilter(0, 1, 3, 3, 2, 1));
		return nameBitmapData;
	}

	public void drawName(List<IGraphicsData> graphicsData, Camera camera) {
		if (this.nameBitmapData == null) {
			this.nameText = this.generateNameText(this.name);
			this.nameBitmapData = this.generateNameBitmapData(this.nameText);
			this.nameFill = new GraphicsBitmapFill(null, new Matrix(), false, false);
			this.namePath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new List<Double>());
		}
		int w = this.nameBitmapData.width / 2 + 1;
		int h = 30;
		List<Double> nameVSs = this.namePath.data;
		nameVSs.length = 0;
		nameVSs.add(posS[0] - w, posS[1], posS[0] + w, posS[1], posS[0] + w, posS[1] + h, posS[0] - w, posS[1] + h);
		this.nameFill.bitmapData = this.nameBitmapData;
		Matrix m = this.nameFill.matrix;
		m.identity();
		m.translate(nameVSs[0], nameVSs[1]);
		graphicsData.add(this.nameFill);
		graphicsData.add(this.namePath);
		graphicsData.add(GraphicsUtil.END_FILL);
	}

	protected BitmapData getHallucinatingTexture() {
		if (this.hallucinatingTexture == null) {
			this.hallucinatingTexture = AssetLibrary.getImageFromSet("lofiChar8x8",int(Math.random() * 239));
		}
		return this.hallucinatingTexture;
	}

	protected BitmapData getTexture(Camera camera, int time) {
		double p = NaN;
		int action = 0;
		MaskedImage image = null;
		int walkPer = 0;
		BitmapData animTexture = null;
		int w = 0;
		BitmapData newTexture = null;
		BitmapData texture = this.texture;
		int size = this.size;
		BitmapData mask = null;
		if (this.animatedChar != null) {
			p = 0;
			action = AnimatedChar.STAND;
			if (time < this.attackStart + ATTACK_PERIOD) {
				if (!this.props.dontFaceAttacks) {
					this.facing = this.attackAngle;
				}
				p = (time - this.attackStart) % ATTACK_PERIOD / ATTACK_PERIOD;
				action = AnimatedChar.ATTACK;
			} else if (this.moveVec.x != 0 || this.moveVec.y != 0) {
				walkPer = 0.5 / this.moveVec.length;
				walkPer = walkPer + (400 - walkPer % 400);
				if (this.moveVec.x > ZERO_LIMIT || this.moveVec.x < NEGATIVE_ZERO_LIMIT || this.moveVec.y > ZERO_LIMIT || this.moveVec.y < NEGATIVE_ZERO_LIMIT) {
					this.facing = Math.atan2(this.moveVec.y, this.moveVec.x);
					action = AnimatedChar.WALK;
				} else {
					action = AnimatedChar.STAND;
				}
				p = time % walkPer / walkPer;
			}
			image = this.animatedChar.imageFromFacing(this.facing, camera, action, p);
			texture = image.image;
			mask = image.mask;
		} else if (this.animations != null) {
			animTexture = this.animations.getTexture(time);
			if (animTexture != null) {
				texture = animTexture;
			}
		}
		if (this.props.drawOnGround || this.obj3D != null) {
			return texture;
		}
		if (camera.isHallucinating) {
			w = texture == null ?int(8):int(texture.width);
			texture = this.getHallucinatingTexture();
			mask = null;
			size = this.size * Math.min(1.5, w / texture.width);
		}
		if (this.isStasis()) {
			texture = CachingColorTransformer.filterBitmapData(texture, PAUSED_FILTER);
		}
		if (this.flash != null) {
			if (!this.flash.doneAt(time)) {
				texture = this.flash.apply(texture, time);
			} else {
				this.flash = null;
			}
		}
		if (this.tex1Id == 0 && this.tex2Id == 0) {
			texture = TextureRedrawer.redraw(texture, size, false, 0, 0);
		} else {
			newTexture = null;
			if (this.texturingCache == null) {
				this.texturingCache = new Dictionary();
			} else {
				newTexture = this.texturingCache[texture];
			}
			if (newTexture == null) {
				newTexture = TextureRedrawer.resize(texture, mask, size, false, this.tex1Id, this.tex2Id);
				newTexture = TextureRedrawer.outlineGlow(newTexture, 0, 0);
				this.texturingCache[texture] = newTexture;
			}
			texture = newTexture;
		}
		return texture;
	}

	public void useAltTexture(String file, int index) {
		this.texture = AssetLibrary.getImageFromSet(file, index);
		this.sizeMult = this.texture.height / 8;
	}

	public BitmapData getPortrait() {
		BitmapData portraitTexture = null;
		int size = 0;
		if (this.portrait == null) {
			portraitTexture = this.props.portrait != null ? this.props.portrait.getTexture() : this.texture;
			size = 4 / portraitTexture.width * 100;
			this.portrait = TextureRedrawer.resize(portraitTexture, this.mask, size, true, this.tex1Id, this.tex2Id);
			this.portrait = TextureRedrawer.outlineGlow(this.portrait, 0, 0);
		}
		return this.portrait;
	}

	public void setAttack(int containerType, Number attackAngle) {
		this.attackAngle = attackAngle;
		this.attackStart = getTimer();
	}

	public void draw(List<IGraphicsData> graphicsData, Camera camera, int time) {
		BitmapData texture = this.getTexture(camera, time);
		if (this.props.drawOnGround) {
			if (square.faces.length == 0) {
				return;
			}
			this.path.data = square.faces[0].face.vout;
			this.bitmapFill.bitmapData = texture;
			square.baseTexMatrix.calculateTextureMatrix(this.path.data);
			this.bitmapFill.matrix = square.baseTexMatrix.tToS;
			graphicsData.add(this.bitmapFill);
			graphicsData.add(this.path);
			graphicsData.add(GraphicsUtil.END_FILL);
			return;
		}
		if (this.obj3D != null) {
			this.obj3D.draw(graphicsData, camera, this.props.color, texture);
			return;
		}
		int w = texture.width;
		int h = texture.height;
		int h2 = square.sink + this.sinkLevel;
		if (h2 > 0 && (this.flying || square.obj != null && square.obj.props.protectFromSink)) {
			h2 = 0;
		}
		this.vS.length = 0;
		this.vS.add(posS[3] - w / 2, posS[4] - h + h2, posS[3] + w / 2, posS[4] - h + h2, posS[3] + w / 2, posS[4], posS[3] - w / 2, posS[4]);
		this.path.data = this.vS;
		this.bitmapFill.bitmapData = texture;
		this.fillMatrix.identity();
		this.fillMatrix.translate(this.vS[0], this.vS[1]);
		this.bitmapFill.matrix = this.fillMatrix;
		graphicsData.add(this.bitmapFill);
		graphicsData.add(this.path);
		graphicsData.add(GraphicsUtil.END_FILL);
		if (!this.isPaused() && this.condition && !Parameters.screenShotMode) {
			this.drawConditionIcons(graphicsData, camera, time);
		}
		if (this.props.showName && this.name != null && this.name.length != 0) {
			this.drawName(graphicsData, camera);
		}
	}

	public void drawConditionIcons(List<IGraphicsData> graphicsData, Camera camera, int time) {
		BitmapData icon = null;
		GraphicsBitmapFill fill = null;
		GraphicsPath path = null;
		double x = NaN;
		double y = NaN;
		Matrix m = null;
		if (this.icons == null) {
			this.icons = new List<BitmapData>();
			this.iconFills = new List<GraphicsBitmapFill>();
			this.iconPaths = new List<GraphicsPath>();
		}
		this.icons.length = 0;
		int index = time / 500;
		ConditionEffect.getConditionEffectIcons(this.condition, this.icons, index);
		double centerX = posS[3];
		double centerY = this.vS[1];
		int len = this.icons.length;
		for (int i = 0; i < len; i++) {
			icon = this.icons[i];
			if (i >= this.iconFills.length) {
				this.iconFills.add(new GraphicsBitmapFill(null, new Matrix(), false, false));
				this.iconPaths.add(new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new List<Double>()));
			}
			fill = this.iconFills[i];
			path = this.iconPaths[i];
			fill.bitmapData = icon;
			x = centerX - icon.width * len / 2 + i * icon.width;
			y = centerY - icon.height / 2;
			path.data.length = 0;
			path.data.add(x, y, x + icon.width, y, x + icon.width, y + icon.height, x, y + icon.height);
			m = fill.matrix;
			m.identity();
			m.translate(x, y);
			graphicsData.add(fill);
			graphicsData.add(path);
			graphicsData.add(GraphicsUtil.END_FILL);
		}
	}

	public void drawShadow(List<IGraphicsData> graphicsData, Camera camera, int time) {
		if (this.shadowGradientFill == null) {
			this.shadowGradientFill = new GraphicsGradientFill(GradientType.RADIAL,[this.props.shadowColor, this.props.shadowColor],[
			0.5, 0],null, new Matrix());
			this.shadowPath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new List<Double>());
		}
		double s = this.size / 100 * (this.props.shadowSize / 100) * this.sizeMult;
		double w = 30 * s;
		double h = 15 * s;
		this.shadowGradientFill.matrix.createGradientBox(w * 2, h * 2, 0, posS[0] - w, posS[1] - h);
		graphicsData.add(this.shadowGradientFill);
		this.shadowPath.data.length = 0;
		this.shadowPath.data.add(posS[0] - w, posS[1] - h, posS[0] + w, posS[1] - h, posS[0] + w, posS[1] + h, posS[0] - w, posS[1] + h);
		graphicsData.add(this.shadowPath);
		graphicsData.add(GraphicsUtil.END_FILL);
	}

	public String toString() {
		return "[" + getQualifiedClassName(this) + " id: " + objectId + " type: " + ObjectLibrary.typeToDisplayId[this.objectType] + " pos: " + x + ", " + y + "]";
	}
}
}
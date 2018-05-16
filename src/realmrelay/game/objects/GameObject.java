package realmrelay.game.objects;

import javafx.scene.Camera;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import realmrelay.game._as3.Point;
import realmrelay.game._as3.XML;
import realmrelay.game.map.Map;
import realmrelay.game.messaging.data.WorldPosData;
import realmrelay.game.objects.animation.AnimatedChar;
import realmrelay.game.objects.animation.Animations;
import realmrelay.game.parameters.Parameters;
import realmrelay.game.particles.ParticleEffect;
import realmrelay.game.util.*;
import realmrelay.game._as3.BitmapData;

import java.util.HashMap;
import java.util.List;

public class GameObject extends BasicObject {

	/**
	 * protected static ColorMatrixFilter PAUSED_FILTER = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);
	 * protected static ColorMatrixFilter CURSED_FILTER = new ColorMatrixFilter(MoreColorUtil.redFilterMatrix);
	 * protected static Matrix IDENTITY_MATRIX = new Matrix();
	 */

	private static double ZERO_LIMIT = 0.00001F;
	private static double NEGATIVE_ZERO_LIMIT = -ZERO_LIMIT;
	public static int ATTACK_PERIOD = 300;
	private static int DEFAULT_HP_BAR_Y_OFFSET = 6;

	public GameObject(XML objectXML) {
		this.props = ObjectLibrary.defaultProps;
		this.posAtTick = new Point();
		this.tickPosition = new Point();
		/*this.moveVec = new Vector3D();
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.vS = new double[0];
		this.uvt = new double[0];
		this.fillMatrix = new Matrix();
		super();*/
		if (objectXML == null) {
			return;
		}
		this.objectType = objectXML.getIntValue("type");
		this.props = ObjectLibrary.propsLibrary.get(this.objectType);
		hasShadow = this.props.shadowSize > 0;
		TextureData textureData = ObjectLibrary.typeToTextureData.get(this.objectType);
		this.texture = textureData.texture;
		this.mask = textureData.mask;
		this.animatedChar = textureData.animatedChar;
		this.randomTextureData = textureData.randomTextureData;
		if (textureData.effectProps != null) {
			this.effect = ParticleEffect.fromProps(textureData.effectProps, this);
		}
		if (this.texture != null) {
			this.sizeMult = this.texture.height() / 8;
		}
		/*if (objectXML.hasOwnProperty("Model")) {
			this.obj3D = Model3D.getObject3D(objectXML.getValue("Model"));
		}
		AnimationsData animationsData = ObjectLibrary.typeToAnimationsData.get(this.objectType);
		if (animationsData != null) {
			this.animations = new Animations(animationsData);
		}**/
		z = this.props.z;
		this.flying = this.props.flying;
		if (objectXML.hasOwnProperty("MaxHitPoints")) {
			this.hp = this.maxHP = objectXML.getIntValue("MaxHitPoints");
		}
		if (objectXML.hasOwnProperty("Defense")) {
			this.defense = objectXML.getIntValue("Defense");
		}
		if (objectXML.hasOwnProperty("SlotTypes")) {
			this.slotTypes = ConversionUtil.toIntVector(objectXML.getValue("SlotTypes"));
			this.equipment = new int[this.slotTypes.length];
			for (int i = 0; i < this.equipment.length; i++) {
				this.equipment[i] = -1;
			}
		}
		if (objectXML.hasOwnProperty("Tex1")) {
			this.tex1Id = objectXML.getIntValue("Tex1");
		}
		if (objectXML.hasOwnProperty("Tex2")) {
			this.tex2Id = objectXML.getIntValue("Tex2");
		}
		this.props.loadSounds();
	}

	public BitmapData nameBitmapData = null;
	public ShockerEffect shockEffect;
	public ObjectProperties props;
	public String name;
	public double radius = 0.5F;
	public double facing = 0;
	public boolean flying = false;
	public double attackAngle = 0;
	public int attackStart = 0;
	public AnimatedChar animatedChar = null;
	public BitmapData texture = null;
	public BitmapData mask = null;
	public List<TextureData> randomTextureData = null;
	public Object3D obj3D = null;
	public Object3DStage3D object3d = null;
	public ParticleEffect effect = null;
	public Animations animations = null;
	public boolean dead = false;
	public int deadCounter = 0;
	public int maxHP = 200;
	public int hp = 200;
	public int size = 100;
	public int level = -1;
	public int defense = 0;
	public int[] slotTypes = null;
	public int[] equipment = null;
	public int[] lockedSlot = null;
	public int[] condition;
	public boolean isInteractive = false;
	public int objectType;
	public int sinkLevel = 0;
	public BitmapData hallucinatingTexture = null;
	public FlashDescription flash = null;
	public int connectType = -1;
	protected BitmapData portrait = null;
	protected HashMap<BitmapData, BitmapData> texturingCache = null;
	protected int tex1Id = 0;
	protected int tex2Id = 0;
	protected int lastTickUpdateTime = 0;
	protected int myLastTickId = -1;
	protected Point posAtTick;
	protected Point tickPosition;
	public Vector3D moveVec;
	protected GraphicsBitmapFill bitmapFill;
	protected GraphicsPath path;
	protected double[] vS;
	protected double[] uvt;
	protected Matrix fillMatrix;
	protected GraphicsGradientFill shadowGradientFill = null;
	protected GraphicsPath shadowPath = null;
	private GraphicsBitmapFill nameFill = null;
	private GraphicsPath namePath = null;
	private boolean isShocked;
	private boolean isShockedTransformSet = false;
	private boolean isCharging;
	private boolean isChargingTransformSet = false;
	private int nextBulletId = 1;
	private double sizeMult = 1;
	private boolean isStunImmune = false;
	private boolean isStasisImmune = false;
	private boolean isParalyzeImmune = false;
	private boolean isDazedImmune = false;
	private boolean ishpScaleSet = false;
	private GraphicsSolidFill hpbarBackFill = null;
	private GraphicsPath hpbarBackPath = null;
	private GraphicsSolidFill hpbarFill = null;
	private GraphicsPath hpbarPath = null;
	private List<BitmapData> icons = null;
	private List<GraphicsBitmapFill> iconFills = null;
	private List<GraphicsPath> iconPaths = null;
	public Vector3D aimAssistPoint = null;
	public GameObject aimAssistTarget = null;
	public boolean jittery = false;
	public boolean mobInfoShown = false;

	public boolean isQuiet() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.QUIET_BIT) != 0;
	}

	public boolean isWeak() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.WEAK_BIT) != 0;
	}

	public boolean isSlowed() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.SLOWED_BIT) != 0;
	}

	public boolean isSick() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.SICK_BIT) != 0;
	}

	public boolean isDazed() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.DAZED_BIT) != 0;
	}

	public boolean isStunned() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.STUNNED_BIT) != 0;
	}

	public boolean isBlind() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.BLIND_BIT) != 0;
	}

	public boolean isDrunk() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.DRUNK_BIT) != 0;
	}

	public boolean isConfused() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.CONFUSED_BIT) != 0;
	}

	public boolean isStunImmune() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.STUN_IMMUNE_BIT) != 0 || this.isStunImmune;
	}

	public boolean isInvisible() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.INVISIBLE_BIT) != 0;
	}

	public boolean isParalyzed() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.PARALYZED_BIT) != 0;
	}

	public boolean isSpeedy() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.SPEEDY_BIT) != 0;
	}

	public boolean isNinjaSpeedy() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.NINJA_SPEEDY_BIT) != 0;
	}

	public boolean isHallucinating() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.HALLUCINATING_BIT) != 0;
	}

	public boolean isHealing() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.HEALING_BIT) != 0;
	}

	public boolean isDamaging() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.DAMAGING_BIT) != 0;
	}

	public boolean isBerserk() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.BERSERK_BIT) != 0;
	}

	public boolean isPaused() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.PAUSED_BIT) != 0;
	}

	public boolean isStasis() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.STASIS_BIT) != 0;
	}

	public boolean isStasisImmune() {
		return this.isStasisImmune || (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.STASIS_IMMUNE_BIT) != 0;
	}

	public boolean isInvincible() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.INVINCIBLE_BIT) != 0;
	}

	public boolean isInvulnerable() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.INVULNERABLE_BIT) != 0;
	}

	public boolean isArmored() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.ARMORED_BIT) != 0;
	}

	public boolean isArmorBroken() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.ARMORBROKEN_BIT) != 0;
	}

	public boolean isArmorBrokenImmune() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.ARMORBROKEN_IMMUNE_BIT) != 0;
	}

	public boolean isSlowedImmune() {
		return (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.SLOWED_IMMUNE_BIT) != 0;
	}

	public boolean isUnstable() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.UNSTABLE_BIT) != 0;
	}

	public boolean isShowPetEffectIcon() {
		return (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.PET_EFFECT_ICON) != 0;
	}

	public boolean isDarkness() {
		return (this.condition[ConditionEffect.CE_FIRST_BATCH] & ConditionEffect.DARKNESS_BIT) != 0;
	}

	public boolean isParalyzeImmune() {
		return this.isParalyzeImmune || (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.PARALYZED_IMMUNE_BIT) != 0;
	}

	public boolean isDazedImmune() {
		return this.isDazedImmune || (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.DAZED_IMMUNE_BIT) != 0;
	}

	public boolean isPetrified() {
		return (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.PETRIFIED_BIT) != 0;
	}

	public boolean isPetrifiedImmune() {
		return (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.PETRIFIED_IMMUNE_BIT) != 0;
	}

	public boolean isCursed() {
		return (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.CURSE_BIT) != 0;
	}

	public boolean isCursedImmune() {
		return (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.CURSE_IMMUNE_BIT) != 0;
	}

	public boolean isSilenced() {
		return (this.condition[ConditionEffect.CE_SECOND_BATCH] & ConditionEffect.SILENCED_BIT) != 0;
	}


	public String getName() {
		return this.name == null || this.name.equals("") ? ObjectLibrary.typeToDisplayId.get(this.objectType) : this.name;
	}

	public int getColor() {
		return BitmapUtil.mostCommonColor(this.texture);
	}

	public int getBulletId() {
		int ret = this.nextBulletId;
		this.nextBulletId = (this.nextBulletId + 1) % 128;
		return ret;
	}

	public double distTo(WorldPosData pos) {
		double dx = pos.x - x;
		double dy = pos.y - y;
		return (double) Math.sqrt(dx * dx + dy * dy);
	}

	@Override

	public boolean addTo(Map map, double x, double y) {
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

	@Override

	public void removeFromMap() {
		if (this.props.isStatic && square != null) {
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

	public boolean moveTo(double x, double y) {
		Square square = map.getSquare(x, y);
		if (square == null) {
			return false;
		}
		x = x;
		y = y;
		if (this.props.isStatic) {
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

	@Override

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

	public void onGoto(double x, double y, int time) {
		this.moveTo(x, y);
		this.lastTickUpdateTime = time;
		this.tickPosition.x = x;
		this.tickPosition.y = y;
		this.posAtTick.x = x;
		this.posAtTick.y = y;
		this.moveVec.x = 0;
		this.moveVec.y = 0;
	}

	public void onTickPos(double x, double y, int tickTime, int tickId) {
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

	public void damage(int origType, int damageAmount, int[] effects, boolean kill, Projectile proj) {
		int offsetTime = 0;
		ConditionEffect ce = null;
		boolean pierced = false;
		if (kill) {
			this.dead = true;
		} else if (effects != null) {
			offsetTime = 0;
			for (int conditionEffect : effects) {
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
		List<int> colors = BloodComposition.getBloodComposition(this.objectType, this.texture, this.props.bloodProb, this.props.bloodColor);
		if (this.dead) {
			map.addObj(new ExplosionEffect(colors, this.size, 30), x, y);
		} else if (proj != null) {
			map.addObj(new HitEffect(colors, this.size, 10, proj.angle, proj.projProps.speed), x, y);
		} else {
			map.addObj(new ExplosionEffect(colors, this.size, 10), x, y);
		}
		if (damageAmount > 0) {
			pierced = this.isArmorBroken() || proj != null && proj.projProps.armorPiercing;
			map.mapOverlay.addStatusText(new CharacterStatusText(this, "-" + damageAmount, !!pierced ? int(9437439) : int
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
			this.namePath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new List<double>());
		}
		int w = this.nameBitmapData.width() / 2 + 1;
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
			this.hallucinatingTexture = AssetLibrary.getImageFromSet("lofiChar8x8", int(Math.random() * 239));
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
			w = texture == null ? int(8) : int(texture.width);
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
				this.texturingCache = new HashMap<>();
			} else {
				newTexture = this.texturingCache.get(texture);
			}
			if (newTexture == null) {
				newTexture = TextureRedrawer.resize(texture, mask, size, false, this.tex1Id, this.tex2Id);
				newTexture = TextureRedrawer.outlineGlow(newTexture, 0, 0);
				this.texturingCache.put(texture, newTexture);
			}
			texture = newTexture;
		}
		return texture;
	}

	public void useAltTexture(String file, int index) {
		this.texture = AssetLibrary.getImageFromSet(file, index);
		this.sizeMult = this.texture.height() / 8;
	}

	public BitmapData getPortrait() {
		BitmapData portraitTexture = null;
		int size = 0;
		if (this.portrait == null) {
			portraitTexture = this.props.portrait != null ? this.props.portrait.getTexture() : this.texture;
			size = 4 / portraitTexture.width() * 100;
			this.portrait = TextureRedrawer.resize(portraitTexture, this.mask, size, true, this.tex1Id, this.tex2Id);
			this.portrait = TextureRedrawer.outlineGlow(this.portrait, 0, 0);
		}
		return this.portrait;
	}

	public void setAttack(int containerType, double attackAngle) {
		this.attackAngle = attackAngle;
		this.attackStart = getTimer();
	}

	@Override
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


	//public void drawConditionIcons(List<IGraphicsData> graphicsData, Camera camera, int time)

	//public void drawShadow(List<IGraphicsData> graphicsData, Camera camera, int time) {

	public boolean isSafe() {
		return isSafe(20);
	}

	public boolean isSafe(int param1) {
		int loc3 = 0;
		int loc4 = 0;
		for (GameObject loc2 : map.goDict.values()) {
			if ((loc2 instanceof Character) && loc2.props.isEnemy) {
				loc3 = (int) (x > loc2.x ? x - loc2.x : loc2.x - x);
				loc4 = (int) (y > loc2.y ? y - loc2.y : loc2.y - y);
				if (loc3 < param1 && loc4 < param1) {
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * Main dye
	 */
	public void setTexture(int param1) {
		if (param1 == this.tex1Id) {
			return;
		}
		this.tex1Id = param1;
		this.texturingCache = new HashMap<>();
		this.portrait = null;
	}


	public void setObjectId(int param1) {
		TextureData loc2 = null;
		objectId = param1;
		if (this.randomTextureData != null) {
			loc2 = this.randomTextureData.objectId % this.randomTextureData.size()];
			this.texture = loc2.texture;
			this.mask = loc2.mask;
			this.animatedChar = loc2.animatedChar;
			if (this.object3d != null) {
				this.object3d.setBitMapData(this.texture);
			}
		}
	}


	/*
	Secondary dye
	 */
	public void setAltTexture(int param1) {
		if (param1 == this.tex2Id) {
			return;
		}
		this.tex2Id = param1;
		this.texturingCache = new HashMap<>();
		this.portrait = null;
	}

}

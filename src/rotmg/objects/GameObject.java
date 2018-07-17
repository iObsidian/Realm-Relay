package rotmg.objects;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import flash.display.*;
import flash.filters.ColorMatrixFilter;
import flash.geom.ColorTransform;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Vector3D;
import flash.utils.Dictionary;
import rotmg.engine3d.Model3D;
import rotmg.engine3d.Object3D;
import rotmg.map.Camera;
import rotmg.map.Map;
import rotmg.map.mapoverlay.CharacterStatusText;
import rotmg.messaging.data.WorldPosData;
import rotmg.objects.animation.AnimatedChar;
import rotmg.objects.animation.Animations;
import rotmg.objects.animation.AnimationsData;
import rotmg.objects.particles.ExplosionEffect;
import rotmg.objects.particles.HitEffect;
import rotmg.objects.particles.ShockerEffect;
import rotmg.parameters.Parameters;
import rotmg.particles.ParticleEffect;
import rotmg.pets.data.PetVO;
import rotmg.pets.data.PetsModel;
import rotmg.sound.SoundEffectLibrary;
import rotmg.stage3D.GraphicsFillExtra;
import rotmg.stage3D.graphic3D.Object3DStage3D;
import rotmg.text.view.BitmapTextFactory;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.StaticStringBuilder;
import rotmg.text.view.stringBuilder.StringBuilder;
import rotmg.util.*;
import rotmg.util.redrawers.GlowRedrawer;

import static flash.utils.timer.getTimer.getTimer;


public class GameObject extends BasicObject {

	public static final int ATTACK_PERIOD = 300;
	protected static final ColorMatrixFilter PAUSED_FILTER = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);
	protected static final ColorMatrixFilter CURSED_FILTER = new ColorMatrixFilter(MoreColorUtil.redFilterMatrix);
	protected static final Matrix IDENTITY_MATRIX = new Matrix();
	private static final double ZERO_LIMIT = 0.00001;
	private static final double NEGATIVE_ZERO_LIMIT = -ZERO_LIMIT;
	private static final int DEFAULT_HP_BAR_Y_OFFSET = 6;

	public BitmapData nameBitmapData = null;
	public ShockerEffect shockEffect;
	public ObjectProperties props;
	public String name = "";
	public double radius = 0.5;
	public double facing = 0;
	public boolean flying = false;
	public double attackAngle = 0;
	public int attackStart = 0;
	public AnimatedChar animatedChar = null;
	public BitmapData texture = null;
	public BitmapData mask = null;
	public Vector<TextureData> randomTextureData = null;
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
	public Vector<Integer> slotTypes = null;
	public Vector<Integer> equipment = null;
	public Vector<Integer> lockedSlot = null;
	public Vector<Integer> condition;
	public boolean isInteractive = false;
	public int objectType;
	public int sinkLevel = 0;
	public BitmapData hallucinatingTexture = null;
	public FlashDescription flash = null;
	public int connectType = -1;
	protected BitmapData portrait = null;
	protected Dictionary<BitmapData, BitmapData> texturingCache = null;
	protected int tex1Id = 0;

	protected int tex2Id = 0;
	protected int lastTickUpdateTime = 0;
	protected int myLastTickId = -1;
	protected Point posAtTick;
	protected Point tickPosition;
	protected Vector3D moveVec;
	protected GraphicsBitmapFill bitmapFill;
	protected GraphicsPath path;
	protected Vector<Double> vS;
	protected Vector<Double> uvt;
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
	private boolean isParalyzeImmune = false;
	private boolean isDazedImmune = false;
	private boolean isStasisImmune = false;
	private boolean ishpScaleSet = false;
	private GraphicsSolidFill hpbarBackFill = null;
	private GraphicsPath hpbarBackPath = null;
	private GraphicsSolidFill hpbarFill = null;
	private GraphicsPath hpbarPath = null;
	private Vector<BitmapData> icons = null;
	private Vector<GraphicsBitmapFill> iconFills = null;
	private Vector<GraphicsPath> iconPaths = null;

	public GameObject(XML param1) {
		super();
		int loc4 = 0;
		this.props = ObjectLibrary.defaultProps;
		this.condition = new Vector<Integer>(0, 0);
		this.posAtTick = new Point();
		this.tickPosition = new Point();
		this.moveVec = new Vector3D();
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.vS = new Vector<Double>();
		this.uvt = new Vector<Double>();
		this.fillMatrix = new Matrix();
		if (param1 == null) {
			return;
		}
		this.objectType = param1.getIntAttribute("type");
		this.props = ObjectLibrary.propsLibrary.get(this.objectType);
		hasShadow = this.props.shadowSize > 0;
		TextureData loc2 = ObjectLibrary.typeToTextureData.get(this.objectType);
		this.texture = loc2.texture;
		this.mask = loc2.mask;
		this.animatedChar = loc2.animatedChar;
		this.randomTextureData = loc2.randomTextureData;
		if (loc2.effectProps != null) {
			this.effect = ParticleEffect.fromProps(loc2.effectProps, this);
		}
		if (this.texture != null) {
			this.sizeMult = this.texture.height / 8;
		}
		if (param1.hasOwnProperty("Model")) {
			this.obj3D = Model3D.getObject3D(param1.getValue("Model"));
			this.object3d = Model3D.getStage3dObject3D(param1.getValue("Model"));
			if (this.texture != null) {
				this.object3d.setBitMapData(this.texture);
			}
		}
		AnimationsData loc3 = ObjectLibrary.typeToAnimationsData.get(this.objectType);
		if (loc3 != null) {
			this.animations = new Animations(loc3);
		}
		z = this.props.z;
		this.flying = this.props.flying;
		if (param1.hasOwnProperty("MaxHitPoints")) {
			this.hp = this.maxHP = param1.getIntValue("MaxHitPoints");
		}
		if (param1.hasOwnProperty("Defense")) {
			this.defense = param1.getIntValue("Defense");
		}
		if (param1.hasOwnProperty("SlotTypes")) {
			this.slotTypes = ConversionUtil.toIntVector(param1.getValue("SlotTypes"));
			this.equipment = new Vector<Integer>(this.slotTypes.length);
			loc4 = 0;
			while (loc4 < this.equipment.length) {
				this.equipment.put(loc4, -1);
				loc4++;
			}
			this.lockedSlot = new Vector<Integer>(this.slotTypes.length);
		}
		if (param1.hasOwnProperty("Tex1")) {
			this.tex1Id = param1.getIntValue("Tex1");
		}
		if (param1.hasOwnProperty("Tex2")) {
			this.tex2Id = param1.getIntValue("Tex2");
		}
		if (param1.hasOwnProperty("StunImmune")) {
			this.isStunImmune = true;
		}
		if (param1.hasOwnProperty("ParalyzeImmune")) {
			this.isParalyzeImmune = true;
		}
		if (param1.hasOwnProperty("DazedImmune")) {
			this.isDazedImmune = true;
		}
		if (param1.hasOwnProperty("StasisImmune")) {
			this.isStasisImmune = true;
		}
		this.props.loadSounds();
	}

	public static int damageWithDefense(int param1, int param2, boolean param3, Vector<Integer> param4) {
		int loc5 = param2;
		if (param3 || (param4.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.ARMORBROKEN_BIT) != 0) {
			loc5 = 0;
		} else if ((param4.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.ARMORED_BIT) != 0) {
			loc5 = loc5 * 2;
		}
		int loc6 = param1 * 3 / 20;
		int loc7 = Math.max(loc6, param1 - loc5);
		if ((param4.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.INVULNERABLE_BIT) != 0) {
			loc7 = 0;
		}
		if ((param4.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.PETRIFIED_BIT) != 0) {
			loc7 = (int) (loc7 * 0.9);
		}
		if ((param4.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.CURSE_BIT) != 0) {
			loc7 = (int) (loc7 * 1.2);
		}
		return loc7;
	}

	public void setObjectId(int param1) {
		TextureData loc2 = null;
		objectId = param1;
		if (this.randomTextureData != null) {
			loc2 = this.randomTextureData.get(0); //objectId % this.randomTextureData.length
			this.texture = loc2.texture;
			this.mask = loc2.mask;
			this.animatedChar = loc2.animatedChar;
			if (this.object3d != null) {
				this.object3d.setBitMapData(this.texture);
			}
		}
	}

	public void setTexture(int param1) {
		TextureData loc2 = ObjectLibrary.typeToTextureData.get(param1);
		if (loc2 == null) {
			System.err.println("Error, could not find data for object : " + param1);
		} else {
			this.texture = loc2.texture;
			this.mask = loc2.mask;
			this.animatedChar = loc2.animatedChar;
		}
	}

	public void setAltTexture(int param1) {
		TextureData loc3 = null;
		TextureData loc2 = ObjectLibrary.typeToTextureData.get(this.objectType);
		if (param1 == 0) {
			loc3 = loc2;
		} else {
			loc3 = loc2.getAltTextureData(param1);
			if (loc3 == null) {
				return;
			}
		}
		this.texture = loc3.texture;
		this.mask = loc3.mask;
		this.animatedChar = loc3.animatedChar;
		if (this.effect != null) {
			map.removeObj(this.effect.objectId);
			this.effect = null;
		}
		if (!Parameters.data.noParticlesMaster && loc3.effectProps != null) {
			this.effect = ParticleEffect.fromProps(loc3.effectProps, this);
			if (map != null) {
				map.addObj(this.effect, x, y);
			}
		}
	}

	public void setTex1(int param1) {
		if (param1 == this.tex1Id) {
			return;
		}
		this.tex1Id = param1;
		this.texturingCache = new Dictionary();
		this.portrait = null;
	}

	public void setTex2(int param1) {
		if (param1 == this.tex2Id) {
			return;
		}
		this.tex2Id = param1;
		this.texturingCache = new Dictionary();
		this.portrait = null;
	}

	public void playSound(int param1) {
		SoundEffectLibrary.play(this.props.sounds.get(param1));
	}

	@Override
	public void dispose() {
		BitmapData loc2 = null;
		Dictionary loc3 = null;
		BitmapData loc5 = null;
		this.texture = null;
		if (this.portrait != null) {
			this.portrait.dispose();
			this.portrait = null;
		}
		if (this.texturingCache != null) {
			for (Object loc1 : this.texturingCache) {
				loc2 = (BitmapData) loc1;
				if (loc2 != null) {
					loc2.dispose();
				} else {
					loc3 = (Dictionary) loc1;
					for (Object loc4 : loc3) {
						loc5 = (BitmapData) loc4;
						if (loc5 != null) {
							loc5.dispose();
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
		if (this.object3d != null) {
			this.object3d.dispose();
			this.object3d = null;
		}
		this.slotTypes = null;
		this.equipment = null;
		this.lockedSlot = null;
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
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.QUIET_BIT) != 0;
	}

	public boolean isWeak() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.WEAK_BIT) != 0;
	}

	public boolean isSlowed() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.SLOWED_BIT) != 0;
	}

	public boolean isSick() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.SICK_BIT) != 0;
	}

	public boolean isDazed() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.DAZED_BIT) != 0;
	}

	public boolean isStunned() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.STUNNED_BIT) != 0;
	}

	public boolean isBlind() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.BLIND_BIT) != 0;
	}

	public boolean isDrunk() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.DRUNK_BIT) != 0;
	}

	public boolean isConfused() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.CONFUSED_BIT) != 0;
	}

	public boolean isStunImmune() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.STUN_IMMUNE_BIT) != 0 || this.isStunImmune;
	}

	public boolean isInvisible() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.INVISIBLE_BIT) != 0;
	}

	public boolean isParalyzed() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.PARALYZED_BIT) != 0;
	}

	public boolean isSpeedy() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.SPEEDY_BIT) != 0;
	}

	public boolean isNinjaSpeedy() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.NINJA_SPEEDY_BIT) != 0;
	}

	public boolean isHallucinating() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.HALLUCINATING_BIT) != 0;
	}

	public boolean isHealing() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.HEALING_BIT) != 0;
	}

	public boolean isDamaging() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.DAMAGING_BIT) != 0;
	}

	public boolean isBerserk() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.BERSERK_BIT) != 0;
	}

	public boolean isPaused() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.PAUSED_BIT) != 0;
	}

	public boolean isStasis() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.STASIS_BIT) != 0;
	}

	public boolean isStasisImmune() {
		return this.isStasisImmune || (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.STASIS_IMMUNE_BIT) != 0;
	}

	public boolean isInvincible() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.INVINCIBLE_BIT) != 0;
	}

	public boolean isInvulnerable() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.INVULNERABLE_BIT) != 0;
	}

	public boolean isArmored() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.ARMORED_BIT) != 0;
	}

	public boolean isArmorBroken() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.ARMORBROKEN_BIT) != 0;
	}

	public boolean isArmorBrokenImmune() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.ARMORBROKEN_IMMUNE_BIT) != 0;
	}

	public boolean isSlowedImmune() {
		return (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.SLOWED_IMMUNE_BIT) != 0;
	}

	public boolean isUnstable() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.UNSTABLE_BIT) != 0;
	}

	public boolean isShowPetEffectIcon() {
		return (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.PET_EFFECT_ICON) != 0;
	}

	public boolean isDarkness() {
		return (this.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.DARKNESS_BIT) != 0;
	}

	public boolean isParalyzeImmune() {
		return this.isParalyzeImmune || (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.PARALYZED_IMMUNE_BIT) != 0;
	}

	public boolean isDazedImmune() {
		return this.isDazedImmune || (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.DAZED_IMMUNE_BIT) != 0;
	}

	public boolean isPetrified() {
		return (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.PETRIFIED_BIT) != 0;
	}

	public boolean isPetrifiedImmune() {
		return (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.PETRIFIED_IMMUNE_BIT) != 0;
	}

	public boolean isCursed() {
		return (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.CURSE_BIT) != 0;
	}

	public boolean isCursedImmune() {
		return (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.CURSE_IMMUNE_BIT) != 0;
	}

	public boolean isSilenced() {
		return (this.condition.get(ConditionEffect.CE_SECOND_BATCH) & ConditionEffect.SILENCED_BIT) != 0;
	}


	public String getName() {
		return this.name == null || this.name.equals("") ? ObjectLibrary.typeToDisplayId.get(this.objectType) : this.name;
	}

	public int getColor() {
		if (this.props.color != -1) {
			return this.props.color;
		}
		return BitmapUtil.mostCommonColor(this.texture);
	}

	public int getBulletId() {
		int loc1 = this.nextBulletId;
		this.nextBulletId = (this.nextBulletId + 1) % 128;
		return loc1;
	}

	public double distTo(WorldPosData param1) {
		double loc2 = param1.x - x;
		double loc3 = param1.y - y;
		return Math.sqrt(loc2 * loc2 + loc3 * loc3);
	}

	public void toggleShockEffect(boolean param1) {
		if (param1) {
			this.isShocked = true;
		} else {
			this.isShocked = false;
			this.isShockedTransformSet = false;
		}
	}

	public void toggleChargingEffect(boolean param1) {
		if (param1) {
			this.isCharging = true;
		} else {
			this.isCharging = false;
			this.isChargingTransformSet = false;
		}
	}

	public boolean addTo(Map param1, double param2, double param3) {
		map = param1;
		this.posAtTick.x = this.tickPosition.x = param2;
		this.posAtTick.y = this.tickPosition.y = param3;
		if (!this.moveTo(param2, param3)) {
			map = null;
			return false;
		}
		if (this.effect != null) {
			map.addObj(this.effect, param2, param3);
		}
		return true;
	}

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

	public boolean moveTo(double par1, double par2) {

		int param1 = (int) par1;
		int param2 = (int) par2;

		Square loc3 = map.getSquare(param1, param2);
		if (loc3 == null) {
			return false;
		}
		x = param1;
		y = param2;
		if (this.props.isStatic) {
			if (square != null) {
				square.obj = null;
			}
			loc3.obj = this;
		}
		square = loc3;
		if (this.obj3D != null) {
			this.obj3D.setPosition(x, y, 0, this.props.rotation);
		}
		if (this.object3d != null) {
			this.object3d.setPosition(x, y, 0, this.props.rotation);
		}
		return true;
	}

	public boolean update(int param1, int param2) {
		int loc4 = 0;
		double loc5 = 0;
		double loc6 = 0;
		boolean loc3 = false;
		if (!(this.moveVec.x == 0 && this.moveVec.y == 0)) {
			if (this.myLastTickId < map.gs.gsc.lastTickId) {
				this.moveVec.x = 0;
				this.moveVec.y = 0;
				this.moveTo(this.tickPosition.x, this.tickPosition.y);
			} else {
				loc4 = param1 - this.lastTickUpdateTime;
				loc5 = this.posAtTick.x + loc4 * this.moveVec.x;
				loc6 = this.posAtTick.y + loc4 * this.moveVec.y;
				this.moveTo(loc5, loc6);
				loc3 = true;
			}
		}
		if (this.props.whileMoving != null) {
			if (!loc3) {
				z = this.props.z;
				this.flying = this.props.flying;
			} else {
				z = this.props.whileMoving.z;
				this.flying = this.props.whileMoving.flying;
			}
		}
		return true;
	}

	public void onGoto(double param1, double param2, int param3) {
		this.moveTo(param1, param2);
		this.lastTickUpdateTime = param3;
		this.tickPosition.x = param1;
		this.tickPosition.y = param2;
		this.posAtTick.x = param1;
		this.posAtTick.y = param2;
		this.moveVec.x = 0;
		this.moveVec.y = 0;
	}

	public void onTickPos(double param1, double param2, int param3, int param4) {
		if (this.myLastTickId < map.gs.gsc.lastTickId) {
			this.moveTo(this.tickPosition.x, this.tickPosition.y);
		}
		this.lastTickUpdateTime = map.gs.lastUpdate;
		this.tickPosition.x = param1;
		this.tickPosition.y = param2;
		this.posAtTick.x = x;
		this.posAtTick.y = y;
		this.moveVec.x = (this.tickPosition.x - this.posAtTick.x) / param3;
		this.moveVec.y = (this.tickPosition.y - this.posAtTick.y) / param3;
		this.myLastTickId = param4;
	}

	public void damage(boolean param1, int param2, Vector<Integer> param3, boolean param4, Projectile param5) {
		damage(param1, param2, param3, param4, param5, false);
	}

	public void damage(boolean param1, int param2, Vector<Integer> param3, boolean param4, Projectile param5, boolean param6) {
		int loc8 = 0;
		ConditionEffect loc10 = null;
		CharacterStatusText loc11 = null;
		PetsModel loc12 = null;
		PetVO loc13 = null;
		String loc14 = null;
		Vector<Integer> loc15 = null;
		boolean loc16 = false;
		boolean loc7 = false;
		if (param4) {
			this.dead = true;
		} else if (param3 != null) {
			loc8 = 0;
			for (int loc9 : param3) {
				loc10 = null;
				if (param5 != null && param5.projProps.isPetEffect != null && param5.projProps.isPetEffect.get(loc9) != null) {
					loc12 = PetsModel.getInstance();
					loc13 = loc12.getActivePet();
					if (loc13 != null) {
						loc10 = ConditionEffect.effects.get(loc9);
						this.showConditionEffectPet(loc8, loc10.name);
						loc8 = loc8 + 500;
					}
				} else {
					switch (loc9) {
						case ConditionEffect.NOTHING:
							break;
						case ConditionEffect.WEAK:
						case ConditionEffect.SICK:
						case ConditionEffect.BLIND:
						case ConditionEffect.HALLUCINATING:
						case ConditionEffect.DRUNK:
						case ConditionEffect.CONFUSED:
						case ConditionEffect.STUN_IMMUNE:
						case ConditionEffect.INVISIBLE:
						case ConditionEffect.SPEEDY:
						case ConditionEffect.BLEEDING:
						case ConditionEffect.STASIS_IMMUNE:
						case ConditionEffect.NINJA_SPEEDY:
						case ConditionEffect.UNSTABLE:
						case ConditionEffect.DARKNESS:
						case ConditionEffect.PETRIFIED_IMMUNE:
						case ConditionEffect.SILENCED:
							loc10 = ConditionEffect.effects.get(loc9);
							break;
						case ConditionEffect.QUIET:
							if (map.player == this) {
								map.player.mp = 0;
							}
							loc10 = ConditionEffect.effects.get(loc9);
							break;
						case ConditionEffect.STASIS:
							if (this.isStasisImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.SLOWED:
							if (this.isSlowedImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.ARMORBROKEN:
							if (this.isArmorBrokenImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.STUNNED:
							if (this.isStunImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.DAZED:
							if (this.isDazedImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.PARALYZED:
							if (this.isParalyzeImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.PETRIFIED:
							if (this.isPetrifiedImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.CURSE:
							if (this.isCursedImmune()) {
								loc11 = new CharacterStatusText(this, 16711680, 3000);
								loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECT_IMMUNE));
								map.mapOverlay.addStatusText(loc11);
							} else {
								loc10 = ConditionEffect.effects.get(loc9);
							}
							break;
						case ConditionEffect.GROUND_DAMAGE:
							loc7 = true;
					}
					if (loc10 != null) {
						if (loc9 < ConditionEffect.NEW_CON_THREASHOLD) {
							if ((this.condition.get(ConditionEffect.CE_FIRST_BATCH) | loc10.bit) == this.condition.get(ConditionEffect.CE_FIRST_BATCH)) {
								continue;
							}
							this.condition.put(ConditionEffect.CE_FIRST_BATCH, this.condition.get(ConditionEffect.CE_FIRST_BATCH) | loc10.bit);
						} else {
							if ((this.condition.get(ConditionEffect.CE_SECOND_BATCH) | loc10.bit) == this.condition.get(ConditionEffect.CE_SECOND_BATCH)) {
								continue;
							}
							this.condition.put(ConditionEffect.CE_SECOND_BATCH, this.condition.get(ConditionEffect.CE_SECOND_BATCH) | loc10.bit);
						}
						loc14 = loc10.localizationKey;
						this.showConditionEffect(loc8, loc14);
						loc8 = loc8 + 500;
					}
				}
			}
		}
		if (!(this.props.isEnemy && Parameters.data.disableEnemyParticles) && !(!this.props.isEnemy && Parameters.data.disablePlayersHitParticles)) {
			loc15 = BloodComposition.getBloodComposition(this.objectType, this.texture, this.props.bloodProb, this.props.bloodColor);
			if (this.dead) {
				map.addObj(new ExplosionEffect(loc15, this.size, 30), x, y);
			} else if (param5 != null) {
				map.addObj(new HitEffect(loc15, this.size, 10, param5.angle, param5.projProps.speed), x, y);
			} else {
				map.addObj(new ExplosionEffect(loc15, this.size, 10), x, y);
			}
		}
		if (!param1 && (Parameters.data.noEnemyDamage && this.props.isEnemy || Parameters.data.noAllyDamage && this.props.isPlayer)) {
			return;
		}
		if (param2 > 0) {
			loc16 = this.isArmorBroken() || param5 != null && param5.projProps.armorPiercing || loc7 || param6;
			this.showDamageText(param2, loc16);
		}
	}

	public void showConditionEffect(int param1, String param2) {
		CharacterStatusText loc3 = new CharacterStatusText(this, 16711680, 3000, param1);
		loc3.setStringBuilder(new LineBuilder().setParams(param2));
		map.mapOverlay.addStatusText(loc3);
	}

	public void showConditionEffectPet(int param1, String param2) {
		CharacterStatusText loc3 = new CharacterStatusText(this, 16711680, 3000, param1);
		loc3.setStringBuilder(new StaticStringBuilder("Pet " + param2));
		map.mapOverlay.addStatusText(loc3);
	}

	public void showDamageText(int param1, boolean param2) {
		String loc3 = "-" + param1;
		CharacterStatusText loc4 = new CharacterStatusText(this, param2 ? 9437439 : 16711680, 1000);
		loc4.setStringBuilder(new StaticStringBuilder(loc3));
		map.mapOverlay.addStatusText(loc4);
	}

	protected BitmapData makeNameBitmapData() {
		StringBuilder loc1 = new StaticStringBuilder(this.name);
		BitmapTextFactory loc2 = BitmapTextFactory.getInstance();
		return loc2.make(loc1, 16, 16777215, true, IDENTITY_MATRIX, true);
	}

	public void drawName(Vector<IGraphicsData> param1, Camera param2) {
		if (this.nameBitmapData == null) {
			this.nameBitmapData = this.makeNameBitmapData();
			this.nameFill = new GraphicsBitmapFill(null, new Matrix(), false, false);
			this.namePath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Vector<Double>());
		}
		int loc3 = this.nameBitmapData.width / 2 + 1;
		int loc4 = 30;
		Vector<Double> loc5 = this.namePath.data;
		loc5.length = 0;
		loc5.add(posS.get(0) - loc3, posS.get(1), posS.get(0) + loc3, posS.get(1), posS.get(0) + loc3, posS.get(1) + loc4, posS.get(0) - loc3, posS.get(1) + loc4);
		this.nameFill.bitmapData = this.nameBitmapData;
		Matrix loc6 = this.nameFill.matrix;
		loc6.identity();
		loc6.translate(loc5.get(0), loc5.get(1));
		param1.add(this.nameFill);
		param1.add(this.namePath);
		param1.add(GraphicsUtil.END_FILL);
	}

	protected BitmapData getHallucinatingTexture() {
		if (this.hallucinatingTexture == null) {
			this.hallucinatingTexture = AssetLibrary.getImageFromSet("lofiChar8x8", (int) (Math.random() * 239));
		}
		return this.hallucinatingTexture;
	}

	protected BitmapData getTexture(Camera param1, int param2) {
		Pet loc6 = null;
		double loc7 = 0;
		int loc8 = 0;
		MaskedImage loc9 = null;
		int loc10 = 0;
		BitmapData loc11 = null;
		int loc12 = 0;
		BitmapData loc13 = null;
		if (this instanceof Pet) {
			loc6 = (Pet) this;
			if (this.condition.get(ConditionEffect.CE_FIRST_BATCH) != 0 && !this.isPaused()) {
				if (loc6.skinId != 32912) {
					loc6.setSkin(32912);
				}
			} else if (!loc6.isDefaultAnimatedChar) {
				loc6.setDefaultSkin();
			}
		}
		BitmapData loc3 = this.texture;
		int loc4 = this.size;
		BitmapData loc5 = null;
		if (this.animatedChar != null) {
			loc7 = 0;
			loc8 = AnimatedChar.STAND;
			if (param2 < this.attackStart + ATTACK_PERIOD) {
				if (!this.props.dontFaceAttacks) {
					this.facing = this.attackAngle;
				}
				loc7 = (param2 - this.attackStart) % ATTACK_PERIOD / ATTACK_PERIOD;
				loc8 = AnimatedChar.ATTACK;
			} else if (this.moveVec.x != 0 || this.moveVec.y != 0) {
				loc10 = (int) (0.5 / this.moveVec.length);
				loc10 = loc10 + (400 - loc10 % 400);
				if (this.moveVec.x > ZERO_LIMIT || this.moveVec.x < NEGATIVE_ZERO_LIMIT || this.moveVec.y > ZERO_LIMIT || this.moveVec.y < NEGATIVE_ZERO_LIMIT) {
					if (!this.props.dontFaceMovement) {
						this.facing = Math.atan2(this.moveVec.y, this.moveVec.x);
					}
					loc8 = AnimatedChar.WALK;
				} else {
					loc8 = AnimatedChar.STAND;
				}
				loc7 = param2 % loc10 / loc10;
			}
			loc9 = this.animatedChar.imageFromFacing(this.facing, param1, loc8, loc7);
			loc3 = loc9.image;
			loc5 = loc9.mask;
		} else if (this.animations != null) {
			loc11 = this.animations.getTexture(param2);
			if (loc11 != null) {
				loc3 = loc11;
			}
		}
		if (this.props.drawOnGround || this.obj3D != null) {
			return loc3;
		}
		if (param1.isHallucinating) {
			loc12 = loc3 == null ? 8 : loc3.width;
			loc3 = this.getHallucinatingTexture();
			loc5 = null;
			loc4 = (int) (this.size * Math.min(1.5, loc12 / loc3.width));
		}
		if (!(this instanceof Pet)) {
			if (this.isStasis() || this.isPetrified()) {
				loc3 = CachingColorTransformer.filterBitmapData(loc3, PAUSED_FILTER);
			}
		}
		if (this.tex1Id == 0 && this.tex2Id == 0) {
			if (this.isCursed() && Parameters.data.curseIndication) {
				loc3 = TextureRedrawer.redraw(loc3, loc4, false, 16711680);
			} else {
				loc3 = TextureRedrawer.redraw(loc3, loc4, false, 0);
			}
		} else {
			loc13 = null;
			if (this.texturingCache == null) {
				this.texturingCache = new Dictionary();
			} else {
				loc13 = this.texturingCache.get(loc3);
			}
			if (loc13 == null) {
				loc13 = TextureRedrawer.resize(loc3, loc5, loc4, false, this.tex1Id, this.tex2Id);
				loc13 = GlowRedrawer.outlineGlow(loc13, 0);
				this.texturingCache.put(loc3, loc13);
			}
			loc3 = loc13;
		}
		return loc3;
	}

	public void useAltTexture(String param1, int param2) {
		this.texture = AssetLibrary.getImageFromSet(param1, param2);
		this.sizeMult = this.texture.height / 8;
	}

	public BitmapData getPortrait() {
		BitmapData loc1 = null;
		int loc2 = 0;
		if (this.portrait == null) {
			loc1 = this.props.portrait != null ? this.props.portrait.getTexture() : this.texture;
			loc2 = 4 / loc1.width * 100;
			this.portrait = TextureRedrawer.resize(loc1, this.mask, loc2, true, this.tex1Id, this.tex2Id);
			this.portrait = GlowRedrawer.outlineGlow(this.portrait, 0);
		}
		return this.portrait;
	}

	public void setAttack(int param1, double param2) {
		this.attackAngle = param2;
		this.attackStart = getTimer();
	}

	public void draw3d(Vector<Object3DStage3D> param1) {
		if (this.object3d != null) {
			param1.add(this.object3d);
		}
	}

	protected void drawHpBar(Vector<IGraphicsData> param1, int param2) {
		double loc6 = 0;
		double loc7 = 0;
		if (this.hpbarPath == null) {
			this.hpbarBackFill = new GraphicsSolidFill();
			this.hpbarBackPath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Vector<Double>());
			this.hpbarFill = new GraphicsSolidFill();
			this.hpbarPath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Vector<Double>());
		}
		if (this.hp > this.maxHP) {
			this.maxHP = this.hp;
		}
		this.hpbarBackFill.color = 1118481;
		int loc3 = 20;
		int loc4 = 5;
		this.hpbarBackPath.data.length = 0;
		double loc5 = 1.2;
		this.hpbarBackPath.data.add(posS.get(0) - loc3 - loc5, posS.get(1) + param2 - loc5, posS.get(0) + loc3 + loc5, posS.get(1) + param2 - loc5, posS.get(0) + loc3 + loc5, posS.get(1) + param2 + loc4 + loc5, posS.get(0) - loc3 - loc5, posS.get(1) + param2 + loc4 + loc5);
		param1.add(this.hpbarBackFill);
		param1.add(this.hpbarBackPath);
		param1.add(GraphicsUtil.END_FILL);
		if (this.hp > 0) {
			loc6 = this.hp / this.maxHP;
			loc7 = loc6 * 2 * loc3;
			this.hpbarPath.data.length = 0;
			this.hpbarPath.data.add(posS.get(0) - loc3, posS.get(1) + param2, posS.get(0) - loc3 + loc7, posS.get(1) + param2, posS.get(0) - loc3 + loc7, posS.get(1) + param2 + loc4, posS.get(0) - loc3, posS.get(1) + param2 + loc4);
			this.hpbarFill.color = loc6 < 0.5 ? loc6 < 0.2 ? 14684176 : 16744464 : 1113856;
			param1.add(this.hpbarFill);
			param1.add(this.hpbarPath);
			param1.add(GraphicsUtil.END_FILL);
		}
		GraphicsFillExtra.setSoftwareDrawSolid(this.hpbarFill, true);
		GraphicsFillExtra.setSoftwareDrawSolid(this.hpbarBackFill, true);
	}

	public void draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		BitmapData loc9 = null;
		int loc10 = 0;
		int loc11 = 0;
		int loc12 = 0;
		BitmapData loc4 = this.getTexture(param2, param3);
		if (this.props.drawOnGround) {
			if (square.faces.length == 0) {
				return;
			}
			this.path.data = square.faces.get(0).face.vout;
			this.bitmapFill.bitmapData = loc4;
			square.baseTexMatrix.calculateTextureMatrix(this.path.data);
			this.bitmapFill.matrix = square.baseTexMatrix.tToS;
			param1.add(this.bitmapFill);
			param1.add(this.path);
			param1.add(GraphicsUtil.END_FILL);
			return;
		}
		boolean loc5 = this.props != null && (this.props.isEnemy || this.props.isPlayer) && !this.isInvincible() && (this.props.isPlayer || !this.isInvulnerable()) && !this.props.noMiniMap;
		if (this.obj3D != null) {
			if (loc5 && this.bHPBarParamCheck() && this.props.healthBar != 0) {
				this.drawHpBar(param1, this.props.healthBar);
			}
			if (!Parameters.isGpuRender()) {
				this.obj3D.draw(param1, param2, this.props.color, loc4);
				return;
			}
			if (Parameters.isGpuRender()) {
				//param1.add(null);
				return;
			}
		}
		int loc6 = loc4.width;
		int loc7 = loc4.height;
		int loc8 = square.sink + this.sinkLevel;
		if (loc8 > 0 && (this.flying || square.obj != null && square.obj.props.protectFromSink)) {
			loc8 = 0;
		}
		if (Parameters.isGpuRender()) {
			if (loc8 != 0) {
				GraphicsFillExtra.setSinkLevel(this.bitmapFill, Math.max(loc8 / loc7 * 1.65 - 0.02, 0));
				loc8 = (int) (-loc8 + 0.02);
			} else if (GraphicsFillExtra.getSinkLevel(this.bitmapFill) != 0) {
				GraphicsFillExtra.clearSink(this.bitmapFill);
			}
		}
		this.vS.length = 0;
		this.vS.add(posS.get(3) - loc6 / 2, posS.get(4) - loc7 + loc8, posS.get(3) + loc6 / 2, posS.get(4) - loc7 + loc8, posS.get(3) + loc6 / 2, posS.get(4), posS.get(3) - loc6 / 2, posS.get(4));
		this.path.data = this.vS;
		if (this.flash != null) {
			if (!this.flash.doneAt(param3)) {
				if (Parameters.isGpuRender()) {
					this.flash.applyGPUTextureColorTransform(loc4, param3);
				} else {
					loc4 = this.flash.apply(loc4, param3);
				}
			} else {
				this.flash = null;
			}
		}
		if (this.isShocked && !this.isShockedTransformSet) {
			if (Parameters.isGpuRender()) {
				GraphicsFillExtra.setColorTransform(loc4, new ColorTransform(-1, -1, -1, 1, 255, 255, 255, 0));
			} else {
				loc9 = loc4.clone();
				loc9.colorTransform(loc9.rect, new ColorTransform(-1, -1, -1, 1, 255, 255, 255, 0));
				loc9 = CachingColorTransformer.filterBitmapData(loc9, new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix));
				loc4 = loc9;
			}
			this.isShockedTransformSet = true;
		}
		if (this.isCharging && !this.isChargingTransformSet) {
			if (Parameters.isGpuRender()) {
				GraphicsFillExtra.setColorTransform(loc4, new ColorTransform(1, 1, 1, 1, 255, 255, 255, 0));
			} else {
				loc9 = loc4.clone();
				loc9.colorTransform(loc9.rect, new ColorTransform(1, 1, 1, 1, 255, 255, 255, 0));
				loc4 = loc9;
			}
			this.isChargingTransformSet = true;
		}
		this.bitmapFill.bitmapData = loc4;
		this.fillMatrix.identity();
		this.fillMatrix.translate(this.vS.get(0), this.vS.get(1));
		this.bitmapFill.matrix = this.fillMatrix;
		param1.add(this.bitmapFill);
		param1.add(this.path);
		param1.add(GraphicsUtil.END_FILL);
		if (!this.isPaused() && (this.condition.get(ConditionEffect.CE_FIRST_BATCH) != 0 || this.condition.get(ConditionEffect.CE_SECOND_BATCH) != 0) && !Parameters.screenShotMode && !(this instanceof Pet)) {
			this.drawConditionIcons(param1, param2, param3);
		}
		if (this.props.showName && this.name != null && this.name.length() != 0) {
			this.drawName(param1, param2);
		}
		if (loc5) {
			loc10 = loc4.getPixel32(loc4.width / 4, loc4.height / 4) | loc4.getPixel32(loc4.width / 2, loc4.height / 2) | loc4.getPixel32(loc4.width * 3 / 4, loc4.height * 3 / 4);
			loc11 = loc10 >> 24;
			if (loc11 != 0) {
				hasShadow = true;
				loc12 = this.props.isPlayer && this != map.player ? 12 : 0;
				if (this.bHPBarParamCheck() && this.props.healthBar != -1) {
					this.drawHpBar(param1, this.props.healthBar != 0 ? this.props.healthBar : loc12 + DEFAULT_HP_BAR_Y_OFFSET);
				}
			} else {
				hasShadow = false;
			}
		}
	}

	private boolean bHPBarParamCheck() {
		return Parameters.data.HPBar != 0 && (Parameters.data.HPBar == 1 || Parameters.data.HPBar == 2 && this.props.isEnemy || Parameters.data.HPBar == 3 && (this == map.player || this.props.isEnemy) || Parameters.data.HPBar == 4 && this == map.player || Parameters.data.HPBar == 5 && this.props.isPlayer);
	}

	public void drawConditionIcons(Vector<IGraphicsData> param1, Camera param2, int param3) {
		BitmapData loc9 = null;
		GraphicsBitmapFill loc10 = null;
		GraphicsPath loc11 = null;
		double loc12 = 0;
		double loc13 = 0;
		Matrix loc14 = null;
		if (this.icons == null) {
			this.icons = new Vector<BitmapData>();
			this.iconFills = new Vector<GraphicsBitmapFill>();
			this.iconPaths = new Vector<GraphicsPath>();
		}
		this.icons.length = 0;
		int loc4 = param3 / 500;
		ConditionEffect.getConditionEffectIcons(this.condition.get(ConditionEffect.CE_FIRST_BATCH), this.icons, loc4);
		ConditionEffect.getConditionEffectIcons2(this.condition.get(ConditionEffect.CE_SECOND_BATCH), this.icons, loc4);
		double loc5 = posS.get(3);
		double loc6 = this.vS.get(1);
		int loc7 = this.icons.length;
		int loc8 = 0;
		while (loc8 < loc7) {
			loc9 = this.icons.get(loc8);
			if (loc8 >= this.iconFills.length) {
				this.iconFills.add(new GraphicsBitmapFill(null, new Matrix(), false, false));
				this.iconPaths.add(new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Vector<Double>()));
			}
			loc10 = this.iconFills.get(loc8);
			loc11 = this.iconPaths.get(loc8);
			loc10.bitmapData = loc9;
			loc12 = loc5 - loc9.width * loc7 / 2 + loc8 * loc9.width;
			loc13 = loc6 - loc9.height / 2;
			loc11.data.length = 0;
			loc11.data.add(loc12, loc13, loc12 + loc9.width, loc13, loc12 + loc9.width, loc13 + loc9.height, loc12, loc13 + loc9.height);
			loc14 = loc10.matrix;
			loc14.identity();
			loc14.translate(loc12, loc13);
			param1.add(loc10);
			param1.add(loc11);
			param1.add(GraphicsUtil.END_FILL);
			loc8++;
		}
	}


	public void drawShadow(Vector<IGraphicsData> param1, Camera param2, int param3) {
		if (this.shadowGradientFill == null) {
			this.shadowGradientFill = new GraphicsGradientFill(GradientType.RADIAL, new Vector<Integer>(this.props.shadowColor, this.props.shadowColor), new Vector<Double>(0.5, 0.0), null, new Matrix());
			this.shadowPath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Vector<Double>());
		}
		double loc4 = this.size / 100 * (this.props.shadowSize / 100) * this.sizeMult;
		double loc5 = 30 * loc4;
		double loc6 = 15 * loc4;
		this.shadowGradientFill.matrix.createGradientBox(loc5 * 2, loc6 * 2, 0, posS.get(0) - loc5, posS.get(1) - loc6);
		param1.add(this.shadowGradientFill);
		this.shadowPath.data.length = 0;
		this.shadowPath.data.add(posS.get(0) - loc5, posS.get(1) - loc6, posS.get(0) + loc5, posS.get(1) - loc6, posS.get(0) + loc5, posS.get(1) + loc6, posS.get(0) - loc5, posS.get(1) + loc6);
		param1.add(this.shadowPath);
		param1.add(GraphicsUtil.END_FILL);
	}

	public void clearTextureCache() {
		this.texturingCache = new Dictionary<>();
	}

	public String toString() {
		return "[" + this.getClass().getSimpleName() + " id: " + objectId + " type: " + ObjectLibrary.typeToDisplayId.get(this.objectType) + " pos: " + x + ", " + y + "]";
	}
}
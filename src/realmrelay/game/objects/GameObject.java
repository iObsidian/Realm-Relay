package realmrelay.game.objects;

import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import realmrelay.game.XML;
import realmrelay.game.objects.animation.AnimatedChar;
import realmrelay.game.particles.ParticleEffect;
import realmrelay.game.util.ConversionUtil;
import realmrelay.packets.data.unused.BitmapData;

import java.awt.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class GameObject extends BasicObject {

	/**protected static ColorMatrixFilter PAUSED_FILTER = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);
	protected static ColorMatrixFilter CURSED_FILTER = new ColorMatrixFilter(MoreColorUtil.redFilterMatrix);
	protected static Matrix IDENTITY_MATRIX = new Matrix();*/
	private static float ZERO_LIMIT = 0.00001F;
	private static float NEGATIVE_ZERO_LIMIT = -ZERO_LIMIT;
	public static int ATTACK_PERIOD = 300;
	private static int DEFAULT_HP_BAR_Y_OFFSET = 6;

	public GameObject(XML objectXML) {
		this.props = ObjectLibrary.defaultProps;
		this.posAtTick = new Point();
		this.tickPosition = new Point();
		/*this.moveVec = new Vector3D();
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.vS = new float[0];
		this.uvt = new float[0];
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
	public float radius = 0.5F;
	public float facing = 0;
	public boolean flying = false;
	public float attackAngle = 0;
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
	protected Dictionary texturingCache = null;
	protected int tex1Id = 0;
	protected int tex2Id = 0;
	protected int lastTickUpdateTime = 0;
	protected int myLastTickId = -1;
	protected Point posAtTick;
	protected Point tickPosition;
	public Vector3D moveVec;
	protected GraphicsBitmapFill bitmapFill;
	protected GraphicsPath path;
	protected float[] vS;
	protected float[] uvt;
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
	private float sizeMult = 1;
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

	/**
	 * Main dye
	 */
	public void setTex1(int param1) {
		if (param1 == this.tex1Id) {
			return;
		}
		this.tex1Id = param1;
		this.texturingCache = new HashMap<>();
		this.portrait = null;
	}

	/*
	Secondary dye
	 */
	public void setTex2(int param1) {
		if (param1 == this.tex2Id) {
			return;
		}
		this.tex2Id = param1;
		this.texturingCache = new HashMap<>();
		this.portrait = null;
	}

}

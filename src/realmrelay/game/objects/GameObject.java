package realmrelay.game.objects;

import java.awt.Point;
import java.util.Dictionary;
import java.util.List;

import realmrelay.packets.data.unused.BitmapData;

public class GameObject extends BasicObject {

    private static final float ZERO_LIMIT = 0.00001F;
    private static final float NEGATIVE_ZERO_LIMIT = -ZERO_LIMIT;
    /**
     * protected static final ColorMatrixFilter PAUSED_FILTER = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);
     */
    public static final int ATTACK_PERIOD = 300;
    public ObjectProperties props;
    public String name;
    public float radius = 0.5F;
    public float facing = 0;
    public boolean flying = false;
    public float attackAngle = 0;
    public int attackStart = 0;
    //public AnimatedChars animatedChar = null;
    public BitmapData texture = null;
    public BitmapData mask = null;
    public List<TextureData> randomTextureData = null;
    /**
     * public Object3D obj3D = null;
     * public ParticleEffect effect = null;
     * public Animations animations = null;
     */
    public boolean dead = false;
    protected BitmapData portrait = null;
    protected Dictionary texturingCache = null;
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
    private float sizeMult = 1;
    public int sinkLevel = 0;
    public BitmapData hallucinatingTexture = null;
    /*public FlashDescription flash = null;*/
    public int connectType = -1;
    protected int lastTickUpdateTime = 0;
    protected int myLastTickId = -1;
    protected Point posAtTick;
    protected Point tickPosition;
    /*protected Vector3D moveVec;
    public SimpleText nameText = null;*/
    public BitmapData nameBitmapData = null;
    /*private GraphicsBitmapFill nameFill = null;
    private GraphicsPath namePath = null;
    protected GraphicsBitmapFill bitmapFill;
    protected GraphicsPath path;*/
    protected List<Float> vS;
    protected List<Float> uvt;
    /*protected Matrix fillMatrix;*/
    private List<BitmapData> icons = null;
	/*private List<GraphicsBitmapFill> iconFills = null;
	private List<GraphicsPath> iconPaths = null;
	protected GraphicsGradientFill shadowGradientFill = null;
	protected GraphicsPath shadowPath = null;*/


}

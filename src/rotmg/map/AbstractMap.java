package rotmg.map;


import alde.flash.utils.Vector;
import flash.display.Sprite;
import flash.geom.Point;
import flash.utils.Dictionary;
import org.osflash.signals.Signal;
import rotmg.AGameSprite;
import rotmg.background.Background;
import rotmg.map.mapoverlay.MapOverlay;
import rotmg.map.partyoverlay.PartyOverlay;
import rotmg.objects.*;
import rotmg.util.IntPoint;

/**
 * 90% match (except I made everything abstract)
 * made some stuff static for Oryx2D
 */
public abstract class AbstractMap extends Sprite {

	public static final Vector<Square> squares = new Vector<Square>();
	public static final Dictionary<Integer, BasicObject> boDict = new Dictionary<>();
	public static final Dictionary<Integer, GameObject> goDict = new Dictionary<>();

	public AGameSprite gs;
	public String name;
	public Player player = null;
	public boolean showDisplays;
	public int width;
	public int height;
	public int back;
	public Background background = null;
	public Sprite map;
	public HurtOverlay hurtOverlay = null;
	public GradientOverlay gradientOverlay = null;
	public MapOverlay mapOverlay = null;
	public PartyOverlay partyOverlay = null;
	public Vector<Square> squareList;
	public Dictionary<IntPoint, Merchant> merchLookup;
	public Party party = null;
	public Quest quest = null;
	public Signal<Boolean> signalRenderSwitch;
	public boolean isPetYard = false;
	protected boolean allowPlayerTeleport;
	protected boolean wasLastFrameGpu = false;

	public AbstractMap() {
		this.map = new Sprite();
		this.squareList = new Vector<Square>();
		this.merchLookup = new Dictionary<>();
		this.signalRenderSwitch = new Signal<Boolean>();
	}

	public abstract void setProps(int param1, int param2, String param3, int param4, boolean param5, boolean param6);

	public abstract void addObj(BasicObject param1, double param2, double param3);

	public abstract void setGroundTile(int param1, int param2, int param3);

	public abstract void initialize();

	public abstract void dispose();

	public abstract void update(int param1, int param2);

	public Point pSTopW(double param1, double param2) {
		return null;
	}

	public abstract void removeObj(int param1);

	public abstract void draw(Camera param1, int param2);

	public boolean allowPlayerTeleport() {
		return this.name != Map.NEXUS && this.allowPlayerTeleport;
	}

}

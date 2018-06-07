package rotmg.map;


import alde.flash.utils.Dictionary;
import alde.flash.utils.Vector;
import flash.display.Sprite;
import flash.geom.Point;
import javafx.scene.layout.Background;
import org.osflash.signals.Signal;
import rotmg.game.AGameSprite;
import rotmg.map.mapoverlay.MapOverlay;
import rotmg.map.partyoverlay.PartyOverlay;
import rotmg.objects.*;

/**
 * 100% match
 */
public class AbstractMap extends Sprite {

	public Dictionary<Integer, GameObject> goDict;

	public AGameSprite gs;

	public String name;

	public Player player = null;

	public boolean showDisplays;

	public int width;

	public int height;

	public int back;

	protected boolean allowPlayerTeleport;

	public Background background = null;

	public Sprite map;

	public HurtOverlay hurtOverlay = null;

	public GradientOverlay gradientOverlay = null;

	public MapOverlay mapOverlay = null;

	public PartyOverlay partyOverlay = null;

	public Vector<Square> squareList;

	public Vector<Square> squares;

	public Dictionary boDict;

	public Object merchLookup;

	public Party party = null;

	public Quest quest = null;

	public Signal<Boolean> signalRenderSwitch;

	protected boolean wasLastFrameGpu = false;

	public boolean isPetYard = false;

	public AbstractMap() {
		this.goDict = new Dictionary();
		this.map = new Sprite();
		this.squareList = new Vector<Square>();
		this.squares = new Vector<Square>();
		this.boDict = new Dictionary();
		this.merchLookup = new Object();
		this.signalRenderSwitch = new Signal<Boolean>();
	}

	public void setProps(int param1, int param2, String param3, int param4, boolean param5, boolean param6) {
	}

	public void addObj(BasicObject param1, double param2, double param3) {
	}

	public void setGroundTile(int param1, int param2, int param3) {
	}

	public void initialize() {
	}

	public void dispose() {
	}

	public void update(int param1, int param2) {
	}

	public Point pSTopW(double param1, double param2) {
		return null;
	}

	public void removeObj(int param1) {
	}

	public void draw(Camera param1, int param2) {
	}

	public boolean allowPlayerTeleport() {
		return this.name != Map.NEXUS && this.allowPlayerTeleport;
	}

}

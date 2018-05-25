package rotmg.map;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.osflash.signals.Signal;

import flash.display.Sprite;
import javafx.scene.layout.Background;
import rotmg.game.AGameSprite;
import rotmg.objects.BasicObject;
import rotmg.objects.GameObject;
import rotmg.objects.Player;
import rotmg.objects.Square;

public abstract class AbstractMap extends Sprite {

	public HashMap<Integer, GameObject> goDict;

	public AGameSprite gs;

	public String name;

	public Player player = null;

	public boolean showDisplays;

	public int width;

	public int height;

	//BasicObject dictionary
	public HashMap<Integer, BasicObject> boDict;

	public int back;

	protected boolean allowPlayerTeleport;

	public Background background = null;

	public Sprite map;

	public ArrayList<Square> squareList;

	public ArrayList<Square> squares;

	public Object merchLookup;

	public Quest quest = null;

	public Signal signalRenderSwitch;

	protected boolean wasLastFrameGpu = false;

	public boolean isPetYard = false;

	public abstract void setProps(int width, int height, String name);

	public abstract void addObj(BasicObject go, double x, double y);

	public abstract void setGroundTile(double x, double y, int type);

	public abstract Square getSquare(double x, double y);

	public abstract Point pSTopW(Number param1, Number param2);

	public abstract void removeObj(int id);

	public abstract void draw(Camera camera, int param2);

	public abstract void setProps(int param1, int param2, String param3, int param4, boolean param5, boolean param6);

	public abstract void addObj(BasicObject param1, Number param2, Number param3);

	public abstract void setGroundTile(int param1, int param2, int param3);

	public abstract void initialize();

	public abstract void dispose();

	public abstract void update(int param1, int param2);

	public boolean allowPlayerTeleport() {
		return this.name != Map.NEXUS && this.allowPlayerTeleport;
	}

}

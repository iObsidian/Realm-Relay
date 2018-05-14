package realmrelay.game.map;

import javafx.scene.Camera;
import realmrelay.game.game.AGameSprite;
import realmrelay.game.objects.BasicObject;
import realmrelay.game.objects.GameObject;
import realmrelay.game.objects.Player;
import realmrelay.game.objects.Square;

import java.awt.*;
import java.util.HashMap;

public abstract class AbstractMap {

	//GameObject dictionary
	public HashMap<Integer, GameObject> goDict;

	public AGameSprite gs;

	public String name;

	public HashMap<Integer, Square> squareList_;
	public HashMap<Integer, Square> squares;

	public Player player = null;

	public boolean showDisplays;

	public int width;
	public int height;

	//BasicObject dictionary
	public HashMap<Integer, BasicObject> boDict;

	public Object merchLookup;

	//public Party party = null;

	public Quest quest = null;

	//public Signal signalRenderSwitch;

	protected boolean wasLastFrameGpu = false;

	public boolean isPetYard = false;

	public abstract void setProps(int width, int height, String name);

	public abstract void addObj(BasicObject go, double x, double y);

	public abstract void setGroundTile(double x, double y, int type);

	public abstract Square getSquare(double x, double y);

	public abstract Point pSTopW(Number param1, Number param2);

	public abstract void removeObj(int id);

	public abstract void draw(Camera camera, int param2);

}

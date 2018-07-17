package rotmg.objects;

import alde.flash.utils.Vector;
import flash.display.IGraphicsData;
import rotmg.map.Camera;
import rotmg.map.Map;
import rotmg.stage3D.graphic3D.Object3DStage3D;

import java.util.ArrayList;
import java.util.List;

public class BasicObject {

	private static int nextFakeObjectId = 0;

	public Map map;

	public Square square;

	public int objectId;

	public double x;

	public double y;

	public double z;

	public boolean hasShadow;

	public boolean drawn;

	public List<Double> posW;

	public List<Double> posS;

	public double sortVal;

	public BasicObject() {
		super();
		this.posW = new ArrayList<Double>();
		this.posS = new ArrayList<Double>();
		this.clear();
	}

	public static int getNextFakeObjectId() {
		return 2130706432 | nextFakeObjectId++;
	}

	public void clear() {
		this.map = null;
		this.square = null;
		this.objectId = -1;
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.hasShadow = false;
		this.drawn = false;
		this.posW.clear();
		this.posS.clear();
		this.sortVal = 0;
	}

	public void dispose() {
		this.map = null;
		this.square = null;
		this.posW = null;
		this.posS = null;
	}

	public boolean update(int time, int dt) {
		return true;
	}

	public void draw3d(Vector<Object3DStage3D> param1) {
	}

	public void draw(Vector<IGraphicsData> graphicsData, Camera camera, int time) {
	}

	public void drawShadow(Vector<IGraphicsData> graphicsData, Camera camera, int time) {
	}

	public void computeSortVal(Camera camera) {
		this.posW.clear();
		this.posW.add(this.x);
		this.posW.add(this.y);
		this.posW.add(0.0);
		this.posW.add(this.x);
		this.posW.add(this.y);
		this.posW.add(this.z);
		this.posS.clear();
		camera.wToS.transformVectors(this.posW, this.posS);
		this.sortVal = this.posS.get(1);
	}

	public boolean addTo(Map map, double par1, double par2) {

		int x = (int) par1;
		int y = (int) par2;

		this.map = map;
		this.square = this.map.getSquare(x, y);
		if (this.square == null) {
			this.map = null;
			return false;
		}
		this.x = x;
		this.y = y;
		return true;
	}

	public void removeFromMap() {
		this.map = null;
		this.square = null;
	}
}

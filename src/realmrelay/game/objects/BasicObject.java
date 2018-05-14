package realmrelay.game.objects;

import javafx.scene.Camera;
import realmrelay.game.map.Map;

/**
 * This class matches 100%
 *
 * @author Alde
 */
public class BasicObject {

	private static int nextFakeObjectId_ = 0;

	public static int getNextFakeObjectId() {
		return 2130706432 | nextFakeObjectId_++;
	}

	public BasicObject() {
		this.posW = new double[0];
		this.posS = new double[0];
	}

	public Map map;
	public Square square;
	public int objectId;
	public double x;
	public double y;
	public double z;
	public boolean hasShadow;
	public boolean drawn;
	public double posW[];
	public double posS[];
	public double sortVal;

	public void clear() {
		this.map = null;
		this.square = null;
		this.objectId = -1;
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.hasShadow = false;
		this.drawn = false;
		this.posW = new double[0];
		this.posS = new double[0];
		this.sortVal = 0;
	}

	public void dispose() {
		this.map = null;
		this.square = null;
		this.posW = null;
		this.posS = null;
	}


	public boolean update(int param1, int param2) {
		return true;
	}

	public void draw3d(List<Object3DStage3D> param1) {
	}

	public void draw(List<IGraphicsData> param1, Camera param2, int param3) {
	}

	public void drawShadow(List<IGraphicsData> param1, Camera param2, int param3) {
	}

	public void computeSortVal(Camera param1) {
		this.posW.length = 0;
		this.posW.add(this.x, this.y, 0, this.x, this.y, this.z);
		this.posS.length = 0;
		param1.wToS.transformVectors(this.posW, this.posS);
		this.sortVal = this.posS[1];
	}

	public void computeSortValNoCamera(double param1) {
		this.posW.length = 0;
		this.posW.add(this.x, this.y, 0, this.x, this.y, this.z);
		this.posS.length = 0;
		this.posS.add(this.x * param1, this.y * param1, 0, this.x * param1, this.y * param1, 0);
		this.sortVal = this.posS[1];
	}

	public boolean addTo(Map map, double x, double y) {
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

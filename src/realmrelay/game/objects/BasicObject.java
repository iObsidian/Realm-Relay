package realmrelay.game.objects;

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
        this.posW = new float[0];
        this.posS = new float[0];
    }

    public Map map;
    public Square square;
    public int objectId;
    public float x;
    public float y;
    public float z;
    public boolean hasShadow;
    public boolean drawn;
    public float posW[];
    public float posS[];
    public float sortVal;

    public void clear() {
        this.map = null;
        this.square = null;
        this.objectId = -1;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.hasShadow = false;
        this.drawn = false;
        this.posW = new float[0];
        this.posS = new float[0];
        this.sortVal = 0;
    }

    public void dispose() {
        this.map = null;
        this.square = null;
        this.posW = null;
        this.posS = null;
    }

    public boolean addTo(Map map, float x, float y) {
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

}

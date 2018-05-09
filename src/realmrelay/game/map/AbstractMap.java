package realmrelay.game.map;

import java.awt.Point;
import java.util.HashMap;

import javafx.scene.Camera;
import realmrelay.game.objects.BasicObject;
import realmrelay.game.objects.GameObject;
import realmrelay.game.objects.Square;

public abstract class AbstractMap {

    //GameObject dictionary
    public HashMap<Integer, GameObject> goDict;

    public HashMap<Integer, Square> squareList_;
    public HashMap<Integer, Square> squares;

    //BasicObject dictionary
    public HashMap<Integer, BasicObject> boDict;

    public String name;

    public int width;
    public int height;

    public abstract void setProps(int width, int height, String name);

    public abstract void addObj(BasicObject go, float x, float y);

    public abstract void setGroundTile(float x, float y, int type);

    public abstract Square getSquare(float x, float y);

    public abstract Point pSTopW(Number param1, Number param2);

    public abstract void removeObj(int id);

    public abstract void draw(Camera camera, int param2);

}

package rotmg.map;

import flash.geom.Point;
import rotmg.game.AGameSprite;
import rotmg.objects.BasicObject;
import rotmg.objects.GameObject;
import rotmg.objects.Square;

import java.util.HashMap;

public class Map extends AbstractMap {

	public static final String CLOTH_BAZAAR = "Cloth Bazaar";
	public static final String NEXUS = "Nexus";
	public static final String DAILY_QUEST_ROOM = "Daily Quest Room";
	public static final String DAILY_LOGIN_ROOM = "Daily Login Room";
	public static final String PET_YARD_1 = "Pet Yard";
	public static final String PET_YARD_2 = "Pet Yard 2";
	public static final String PET_YARD_3 = "Pet Yard 3";
	public static final String PET_YARD_4 = "Pet Yard 4";
	public static final String PET_YARD_5 = "Pet Yard 5";
	public static final String GUILD_HALL = "Guild Hall";
	public static final String NEXUS_EXPLANATION = "Nexus_Explanation";
	public static final String VAULT = "Vault";

	public Map(AGameSprite gameSprite) {
		this.squares = new HashMap<Integer, Square>();
	}

	@Override
	public Square getSquare(double x, double y) {

		if (x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}

		int loc = (int) x + (int) y * width;
		Square square = squares.get(loc);
		if (square == null) {
			square = new Square(this, x, y);
			squares.put(loc, square);
		}
		return square;

	}
	
	public Point pSTopW(Number param1, double param2) {
		return null;
	}

	@Override
	public void setProps(int width, int height, String name) {
		this.width = width;
		this.height = height;
		this.name = name;
	}

	@Override
	public void addObj(BasicObject object, double x, double y) {
		object.x = x;
		object.y = y;

		//internalAddObject
		if (object instanceof BasicObject) {
			boDict.put(object.objectId, object);
		} else if (object instanceof GameObject) {
			goDict.put(object.objectId, (GameObject) object);
		}
	}

	@Override
	public void removeObj(int id) {

		//internalRemoveObj
		if (goDict.containsKey(id)) {
			goDict.remove(id);
		}

		if (boDict.containsKey(id)) {
			boDict.remove(id);
		}
	}

	/**
	 * Called from GameSprite (OnEnterFrame)
	 */
	@Override
	public void draw(Camera camera, int param2) {

		//draw background

		//draw tiles

		//draw objects

		//draw overlay

		//draw filter (darkness, blind)

	}

	@Override
	public void setGroundTile(double x, double y, int type) {
		Square square = this.getSquare(x, y);
		square.setTileType(type);
	}

}

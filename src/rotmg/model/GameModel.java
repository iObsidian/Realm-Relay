package rotmg.model;

import java.util.HashMap;

import rotmg.objects.GameObject;
import rotmg.objects.Player;

public class GameModel {

	public static GameModel instance;

	public static GameModel getInstance() {
		if (instance == null) {
			instance = new GameModel();
		}

		return instance;
	}

	public Player player;

	public HashMap<Integer, GameObject> gameObjects;

	public GameModel() {
		super();
	}

	public GameObject getGameObject(int param1) {
		GameObject loc2 = this.gameObjects.get(param1);
		if ((loc2 == null) && this.player.objectId == param1) {
			loc2 = this.player;
		}
		return loc2;
	}

}

package realmrelay.game.game;

import javafx.scene.Camera;
import realmrelay.game.core.model.PlayerModel;
import realmrelay.game.map.AbstractMap;
import realmrelay.game.messaging.GameServerConnection;
import realmrelay.game.messaging.incoming.MapInfo;
import realmrelay.game.objects.GameObject;
import realmrelay.game.ui.HUDView;

public class AGameSprite {

	public AGameSprite() {
		this.moveRecords = new MoveRecords();
		//this.camera = new Camera();
		//super();
	}

	public boolean isEditor;
	//public Tutorial tutorial;
	//public MapUserInput mui;
	public int lastUpdate;
	public MoveRecords moveRecords;
	public AbstractMap map;
	public PlayerModel model;
	public HUDView hudView;
	public Camera camera;
	public GameServerConnection gsc;
	public boolean isSafeMap = false;

	public void initialize() {
	}

	public void setFocus(GameObject param1) {
	}

	public void applyMapInfo(MapInfo param1) {
	}

	public boolean evalIsNotInCombatMapArea() {
		return false;
	}

}

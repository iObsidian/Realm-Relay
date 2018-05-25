package rotmg.game;

import rotmg.game._as3.Signal;
import rotmg.game._as3.Sprite;
import rotmg.game.core.model.PlayerModel;
import rotmg.game.map.AbstractMap;
import rotmg.game.map.Camera;
import rotmg.game.messaging.GameServerConnection;
import rotmg.game.messaging.incoming.MapInfo;
import rotmg.game.objects.GameObject;
import rotmg.game.ui.HUDView;
import rotmg.map.Camera;
import rotmg.messaging.GameServerConnection;
import rotmg.messaging.incoming.MapInfo;
import rotmg.objects.GameObject;

/**
 * Implemented by GameSprite
 */
public class AGameSprite extends Sprite {

	public final Signal closed = new Signal();

	public boolean isEditor;

	public MapUserInput mui;

	public int lastUpdate;

	public MoveRecords moveRecords;

	public AbstractMap map;

	public PlayerModel model;

	public HUDView hudView;

	public Camera camera;

	public GameServerConnection gsc;

	public AGameSprite() {
		this.moveRecords = new MoveRecords();
		this.camera = new Camera();
	}

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

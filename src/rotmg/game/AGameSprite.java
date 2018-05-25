package rotmg.game;

import flash.display.Sprite;
import rotmg.core.model.PlayerModel;
import rotmg.map.Camera;
import rotmg.messaging.GameServerConnection;
import rotmg.messaging.incoming.MapInfo;
import rotmg.objects.GameObject;
import rotmg.ui.HUDView;

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

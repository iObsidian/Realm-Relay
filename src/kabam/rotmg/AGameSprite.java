package kabam.rotmg;

import flash.display.Sprite;
import kabam.rotmg.core.model.PlayerModel;
import kabam.rotmg.map.AbstractMap;
import kabam.rotmg.map.Camera;
import kabam.rotmg.messaging.GameServerConnection;
import kabam.rotmg.messaging.incoming.MapInfo;
import kabam.rotmg.objects.GameObject;
import kabam.rotmg.tutorial.Tutorial;
import kabam.rotmg.ui.HUDView;
import org.osflash.signals.Signal;


/**
 * Implemented by GameSprite
 */
public class AGameSprite extends Sprite {

	public final Signal closed = new Signal();

	public boolean isEditor;

	public Tutorial tutorial;

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

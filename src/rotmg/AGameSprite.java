package rotmg;

<<<<<<< HEAD:src/rotmg/AGameSprite.java
<<<<<<< HEAD:src/kabam/rotmg/AGameSprite.java
import flash.display.Sprite;
import kabam.rotmg.core.model.PlayerModel;
import kabam.rotmg.map.AbstractMap;
import kabam.rotmg.map.Camera;
import kabam.rotmg.messaging.GameServerConnection;
import kabam.rotmg.messaging.incoming.MapInfo;
import kabam.rotmg.objects.GameObject;
import kabam.rotmg.tutorial.Tutorial;
import kabam.rotmg.ui.HUDView;
=======
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/AGameSprite.java
=======
import kabam.rotmg.messaging.GameServerConnection;
import kabam.rotmg.messaging.incoming.MapInfo;
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/AGameSprite.java
import org.osflash.signals.Signal;

import flash.display.Sprite;
import rotmg.core.model.PlayerModel;
import rotmg.map.AbstractMap;
import rotmg.map.Camera;
import rotmg.messaging.GameServerConnection;
import rotmg.messaging.incoming.MapInfo;
import rotmg.objects.GameObject;
import rotmg.tutorial.Tutorial;
import rotmg.ui.HUDView;

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

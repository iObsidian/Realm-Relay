package kabam.rotmg.map.mapoverlay;

import kabam.rotmg.map.Camera;
import kabam.rotmg.objects.GameObject;

public interface IMapOverlayElement {

	boolean draw(Camera param1, int param2);

	void dispose();

	GameObject getGameObject();

}

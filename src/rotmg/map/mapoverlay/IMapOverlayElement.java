package rotmg.map.mapoverlay;

import rotmg.map.Camera;
import rotmg.objects.GameObject;

public interface IMapOverlayElement {

	boolean draw(Camera param1, int param2);

	void dispose();

	GameObject getGameObject();

}

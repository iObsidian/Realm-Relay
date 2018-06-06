package rotmg.map.mapoverlay;

import rotmg.objects.GameObject;
import rotmg.map.Camera;

public interface IMapOverlayElement {

	Boolean draw(Camera param1, int param2);

	void dispose();

	GameObject getGameObject();

}

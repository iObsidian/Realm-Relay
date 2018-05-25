package rotmg.ui.model;

import rotmg.objects.GameObject;

public class UpdateGameObjectTileVO {

	public int tileX;

	public int tileY;

	public GameObject gameObject;

	public UpdateGameObjectTileVO(int param1, int param2, GameObject param3) {
		super();
		this.tileX = param1;
		this.tileY = param2;
		this.gameObject = param3;
	}

}

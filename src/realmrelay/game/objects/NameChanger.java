package realmrelay.game.objects;

import realmrelay.game.GameSprite;
import realmrelay.game._as3.XML;
import realmrelay.game.objects.GameObject;
import realmrelay.game.objects.IInteractiveObject;
import realmrelay.game.objects.NameChangerPanel;


public class NameChanger extends GameObject implements IInteractiveObject {


	public int rankRequired = 0;

	public NameChanger(XML param1) {
		super(param1);
		isInteractive = true;
	}

	public void setRankRequired(int param1) {
		this.rankRequired = param1;
	}

	public realmrelay.game.ui.panels.Panel getPanel(GameSprite param1) {
		return new NameChangerPanel(param1, this.rankRequired);
	}
}

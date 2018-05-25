package rotmg.objects;

import rotmg.game.GameSprite;
import rotmg.game._as3.XML;
import rotmg.game.objects.GameObject;
import rotmg.game.objects.IInteractiveObject;
import rotmg.game.objects.NameChangerPanel;


public class NameChanger extends GameObject implements IInteractiveObject {


	public int rankRequired = 0;

	public NameChanger(XML param1) {
		super(param1);
		isInteractive = true;
	}

	public void setRankRequired(int param1) {
		this.rankRequired = param1;
	}

	public rotmg.game.ui.panels.Panel getPanel(GameSprite param1) {
		return new NameChangerPanel(param1, this.rankRequired);
	}
}

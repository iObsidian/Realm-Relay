package rotmg.objects;

import alde.flash.utils.XML;
import rotmg.GameSprite;
import rotmg.ui.panels.Panel;
import rotmg.view.NameChangerPanel;

public class NameChanger extends GameObject implements IInteractiveObject {

	public int rankRequired = 0;

	public NameChanger(XML param1) {
		super(param1);
		isInteractive = true;
	}

	public void setRankRequired(int param1) {
		this.rankRequired = param1;
	}

	public Panel getPanel(GameSprite param1) {
		return new NameChangerPanel(param1, this.rankRequired);
	}
}

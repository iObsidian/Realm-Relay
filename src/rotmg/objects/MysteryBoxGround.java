package rotmg.objects;

import alde.flash.utils.XML;
import rotmg.GameSprite;
import rotmg.view.MysteryBoxPanel;
import rotmg.ui.panels.Panel;

public class MysteryBoxGround extends GameObject implements IInteractiveObject {

	public MysteryBoxGround(XML param1) {
		super(param1);
		isInteractive = true;
	}

	public Panel getPanel(GameSprite param1) {
		return new MysteryBoxPanel(param1, objectType);
	}
}

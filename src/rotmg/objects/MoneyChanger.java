package rotmg.objects;

import alde.flash.utils.XML;
import rotmg.GameSprite;
import rotmg.view.MoneyChangerPanel;
import rotmg.ui.panels.Panel;

public class MoneyChanger extends GameObject implements IInteractiveObject {

	public MoneyChanger(XML param1) {
		super(param1);
		isInteractive = true;
	}

	public Panel getPanel(GameSprite param1) {
		return new MoneyChangerPanel(param1);
	}
}

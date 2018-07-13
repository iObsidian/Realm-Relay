package rotmg.objects;

import alde.flash.utils.XML;
import rotmg.GameSprite;
import rotmg.ui.panels.GuildHallPortalPanel;
import rotmg.ui.panels.Panel;

public class GuildHallPortal extends GameObject implements IInteractiveObject {

	public GuildHallPortal(XML param1) {
		super(param1);
		isInteractive = true;
	}

	public Panel getPanel(GameSprite param1) {
		return new GuildHallPortalPanel(param1, this);
	}
}

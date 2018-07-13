package rotmg.objects;

import alde.flash.utils.XML;
import rotmg.GameSprite;
import rotmg.ui.panels.Panel;


public class Portal extends GameObject implements IInteractiveObject {

	//private static final RegExp NAME_PARSER = /(^\s+)\s\(([0-9]+)\/[0-9]+\)/;

	public boolean nexusPortal;

	public boolean lockedPortal;

	public boolean active = true;

	public Portal(XML param1) {
		super(param1);
		isInteractive = true;
		this.nexusPortal = param1.hasOwnProperty("NexusPortal");
		this.lockedPortal = param1.hasOwnProperty("LockedPortal");
	}

	@Override
	public Panel getPanel(GameSprite param1) {
		return null;
	}

	/**@Override protected BitmapData makeNameBitmapData() {
	Array loc1 = name.match(NAME.ARSER);
	StringBuilder loc2 = new PortalNameParser().makeBuilder(name_);
	BitmapTextFactory loc3 = StaticInjectorContext.getInjector().getInstance(BitmapTextFactory);
	return loc3.make(loc2, 16, 16777215, true, IDENTITY_MATRIX, true);
	}

	 @Override public void draw(List<IGraphicsData> param1, Camera param2, int param3) {
	 super.draw(param1, param2, param3);
	 if (this.nexusPortal) {
	 drawName(param1, param2);
	 }
	 }

	 public Panel getPanel(GameSprite param1) {
	 return new PortalPanel(param1, this);
	 }*/
}

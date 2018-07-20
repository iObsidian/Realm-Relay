package rotmg.map.partyoverlay;

import alde.flash.utils.Vector;
import rotmg.ui.PlayerGroupMenu;
import flash.events.MouseEvent;
import rotmg.objects.GameObject;
import rotmg.objects.Player;
import rotmg.ui.menu.Menu;
import rotmg.ui.tooltip.PlayerGroupToolTip;

public class PlayerArrow extends GameObjectArrow {

	public PlayerArrow() {
		super(16777215, 4179794, false);
	}

	protected void onMouseOver(MouseEvent param1) {
		super.onMouseOver(param1);
		setToolTip(new PlayerGroupToolTip(this.getFullPlayerVec(), false));
	}

	protected void onMouseOut(MouseEvent param1) {
		super.onMouseOut(param1);
		setToolTip(null);
	}

	protected void onMouseDown(MouseEvent param1) {
		super.onMouseDown(param1);
		removeMenu();
		setMenu(this.getMenu());
	}

	protected Menu getMenu() {
		Player loc1 = (Player) go;
		if (loc1 == null || loc1.map == null) {
			return null;
		}
		Player loc2 = loc1.map.player;
		if (loc2 == null) {
			return null;
		}
		return new PlayerGroupMenu(loc1.map, this.getFullPlayerVec());
	}

	private Vector<Player> getFullPlayerVec() {
		Vector<Player> loc1 = new Vector<>((Player) go);
		for (GameObject loc2 : extraGOs) {
			loc1.push((Player) loc2);
		}
		return loc1;
	}

}

package rotmg.ui;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import flash.events.Event;
import flash.events.MouseEvent;
import org.osflash.signals.Signal;
import rotmg.map.AbstractMap;
import rotmg.objects.Player;
import rotmg.ui.menu.Menu;
import rotmg.ui.menu.MenuOption;

public class PlayerGroupMenu extends Menu {

	public AbstractMap map;
	public Vector<Player> players;
	public MenuOption teleportOption;
	public LineBreakDesign lineBreakDesign;
	public Signal unableToTeleport;
	private Vector<GameObjectListItem> playerPanels;
	private int posY = 4;

	public PlayerGroupMenu(AbstractMap param1, Vector<Player> param2) {
		super(3552822, 16777215);
		this.playerPanels = new Vector<GameObjectListItem>();
		this.unableToTeleport = new Signal();
		this.map = param1;
		this.players = param2.concat();
		this.createHeader();
		this.createPlayerList();
	}

	private void createPlayerList() {
		GameObjectListItem loc2 = null;
		for (Player loc1 : this.players) {
			loc2 = new GameObjectListItem(11776947, true, loc1);
			loc2.x = 0;
			loc2.y = this.posY;
			addChild(loc2);
			this.playerPanels.add(loc2);
			loc2.textReady.addOnce(new SignalConsumer<>(this::onTextChanged));
			this.posY = this.posY + 32;
		}
	}

	private void onTextChanged() {
		draw();
		for (GameObjectListItem loc1 : this.playerPanels) {
			loc1.textReady.remove(new SignalConsumer<>(this::onTextChanged));
		}
	}

	private void createHeader() {
		if (this.map.allowPlayerTeleport()) {
			this.teleportOption = new TeleportMenuOption(this.map.player);
			this.teleportOption.x = 8;
			this.teleportOption.y = 8;
			this.teleportOption.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onTeleport));
			addChild(this.teleportOption);
			this.lineBreakDesign = new LineBreakDesign(150, 1842204);
			this.lineBreakDesign.x = 6;
			this.lineBreakDesign.y = 40;
			addChild(this.lineBreakDesign);
			this.posY = 52;
		}
	}

	private void onTeleport(Event param1) {
		Player loc2 = this.map.player;
		Player loc3 = null;
		for (Player loc4 : this.players) {
			if (loc2.isTeleportEligible(loc4)) {
				loc3 = loc4;
				if (loc2.msUtilTeleport() > Player.MS_BETWEEN_TELEPORT) {
					if (loc4.isFellowGuild) {
						break;
					}
					continue;
				}
				break;
			}
		}
		if (loc3 != null) {
			loc2.teleportTo(loc3);
		} else {
			this.unableToTeleport.dispatch();
		}
		remove();
	}

}

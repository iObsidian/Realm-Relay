package rotmg.ui.tooltip;

import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import rotmg.objects.Player;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.GameObjectListItem;
import rotmg.util.TextKey;
import spark.filters.DropShadowFilter;

public class PlayerGroupToolTip extends ToolTip {


	public Vector<Player> players = null;

	private Vector<GameObjectListItem> playerPanels;

	private TextFieldDisplayConcrete clickMessage;

	public PlayerGroupToolTip(Vector<Player> param1, boolean param2) {
		super(3552822, 0.5, 16777215, 1, param2);
		this.playerPanels = new Vector<GameObjectListItem>();
		this.clickMessage = new TextFieldDisplayConcrete().setSize(12).setColor(11776947);
		this.clickMessage.setStringBuilder(new LineBuilder().setParams(TextKey.PLAYER_TOOL_TIP_CLICK_MESSAGE));
		this.clickMessage.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		addChild(this.clickMessage);
		this.setPlayers(param1);
		if (!param2) {
			filters = new Vector<>();
		}
		waiter.push(this.clickMessage.textChanged);
	}

	public void setPlayers(Vector<Player> param1) {
		GameObjectListItem loc4 = null;
		this.clear();
		this.players = param1.slice();
		if (this.players == null || this.players.length == 0) {
			return;
		}
		int loc2 = 0;
		for (Player loc3 : param1) {
			loc4 = new GameObjectListItem(11776947, true, loc3);
			loc4.x = 0;
			loc4.y = loc2;
			addChild(loc4);
			this.playerPanels.add(loc4);
			loc4.textReady.addOnce(new SignalConsumer<>(this::onTextChanged));
			loc2 = loc2 + 32;
		}
		this.clickMessage.x = width / 2 - this.clickMessage.width / 2;
		this.clickMessage.y = loc2;
		draw();
	}

	private void onTextChanged() {
		this.clickMessage.x = width / 2 - this.clickMessage.width / 2;
		draw();
		for (GameObjectListItem loc1 : this.playerPanels) {
			loc1.textReady.remove(new SignalConsumer<>(this::onTextChanged));
		}
	}

	private void clear() {
		graphics.clear();
		for (GameObjectListItem loc1 : this.playerPanels) {
			removeChild(loc1);
		}
		this.playerPanels.length = 0;
	}
}

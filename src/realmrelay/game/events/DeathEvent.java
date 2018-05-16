package realmrelay.game.events;

import realmrelay.game._as3.Event;
import realmrelay.game.objects.Player;
import realmrelay.game._as3.BitmapData;

public class DeathEvent extends Event {

	public static final String DEATH = "DEATH";

	public BitmapData background;

	public Player player;

	public int accountId;

	public int charId;

	public DeathEvent(BitmapData param1, int param2, int param3) {
		super(DEATH);
		this.background = param1;
		this.accountId = param2;
		this.charId = param3;
	}
}


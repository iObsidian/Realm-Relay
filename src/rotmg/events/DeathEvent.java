package rotmg.events;

import flash.display.BitmapData;
import flash.events.Event;
import rotmg.objects.Player;

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


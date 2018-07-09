package rotmg.events;

import flash.events.Event;

public class GuildResultEvent extends Event {

	public static final String EVENT = "GUILDRESULTEVENT";

	public boolean success;

	public String errorKey;

	public Object errorTokens;

	public GuildResultEvent(boolean param1, String param2, Object param3) {
		super(EVENT);
		this.success = param1;
		this.errorKey = param2;
		this.errorTokens = param3;
	}
}

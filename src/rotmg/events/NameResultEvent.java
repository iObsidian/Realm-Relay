package rotmg.events;

import flash.events.Event;
import rotmg.messaging.incoming.NameResult;

public class NameResultEvent extends Event {

	public static final String NAMERESULTEVENT = "NAMERESULTEVENT";

	public NameResult m;

	public NameResultEvent(NameResult param1) {
		super(NAMERESULTEVENT);
		this.m = param1;
	}
}

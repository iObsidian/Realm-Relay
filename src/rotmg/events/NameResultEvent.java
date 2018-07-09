package rotmg.events;

import flash.events.Event;
<<<<<<< HEAD:src/kabam/rotmg/events/NameResultEvent.java
import kabam.rotmg.messaging.incoming.NameResult;
=======
import rotmg.messaging.incoming.NameResult;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/events/NameResultEvent.java

public class NameResultEvent extends Event {

	public static final String NAMERESULTEVENT = "NAMERESULTEVENT";

	public NameResult m;

	public NameResultEvent(NameResult param1) {
		super(NAMERESULTEVENT);
		this.m = param1;
	}
}

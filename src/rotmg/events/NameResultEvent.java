
package rotmg.events;

//import flash.events.Event;
//import kabam.rotmg.messaging.impl.incoming.NameResult;

import rotmg.game._as3.Event;
import rotmg.game.messaging.incoming.NameResult;
import rotmg.messaging.incoming.NameResult;

public class NameResultEvent extends Event {

	public static final String NAMERESULTEVENT ="NAMERESULTEVENT";

	public NameResult m;

	public  NameResultEvent(NameResult param1)  {
		super(NAMERESULTEVENT);
		this.m = param1;
	}
}

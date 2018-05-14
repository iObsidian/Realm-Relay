
package realmrelay.game.events;

//import flash.events.Event;
//import kabam.rotmg.messaging.impl.incoming.NameResult;

import realmrelay.game._as3.Event;
import realmrelay.game.messaging.incoming.NameResult;

public class NameResultEvent extends Event {

	public static final String NAMERESULTEVENT ="NAMERESULTEVENT";

	public NameResult m;

	public  NameResultEvent(NameResult param1)  {
		super(NAMERESULTEVENT);
		this.m = param1;
	}
}

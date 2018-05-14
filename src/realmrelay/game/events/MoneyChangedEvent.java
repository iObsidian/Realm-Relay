
package realmrelay.game.events;
//	import flash.events.Event;

import realmrelay.game._as3.Event;

public class MoneyChangedEvent extends Event {

	public static final String MONEY_CHANGED = "MONEY_CHANGED";

	public MoneyChangedEvent() {
		super(MONEY_CHANGED, true);
	}
}

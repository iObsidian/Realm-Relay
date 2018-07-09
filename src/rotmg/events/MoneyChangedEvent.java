package rotmg.events;
//	import flash.events.Event;

import flash.events.Event;

public class MoneyChangedEvent extends Event {

	public static final String MONEY_CHANGED = "MONEY_CHANGED";

	public MoneyChangedEvent() {
		super(MONEY_CHANGED, true);
	}
}

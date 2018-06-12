package rotmg.packages.models;

import flash.events.ErrorEvent;

public class SecurityErrorEvent extends ErrorEvent {

	static public final String SECURITY_ERROR = "securityError";

	public SecurityErrorEvent(String type) {
		super(type);
	}
}

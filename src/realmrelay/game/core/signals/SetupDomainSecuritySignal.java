package realmrelay.game.core.signals;

import realmrelay.game._as3.Signal;

public class SetupDomainSecuritySignal extends Signal {

	private static SetupDomainSecuritySignal instance;

	public static SetupDomainSecuritySignal getInstance() {
		if (instance == null) {
			instance = new SetupDomainSecuritySignal();
		}
		return instance;
	}

}

package rotmg.core.signals;

public class SetupDomainSecuritySignal extends Signal {

	private static SetupDomainSecuritySignal instance;

	public static SetupDomainSecuritySignal getInstance() {
		if (instance == null) {
			instance = new SetupDomainSecuritySignal();
		}
		return instance;
	}

}

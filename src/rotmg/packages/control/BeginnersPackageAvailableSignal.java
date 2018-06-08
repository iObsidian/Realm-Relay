package rotmg.packages.control;

import org.osflash.signals.Signal;

public class BeginnersPackageAvailableSignal extends Signal {

	static BeginnersPackageAvailableSignal instance;

	public static BeginnersPackageAvailableSignal getInstance() {

		if (instance == null) {
			instance = new BeginnersPackageAvailableSignal();
		}

		return instance;
	}
}

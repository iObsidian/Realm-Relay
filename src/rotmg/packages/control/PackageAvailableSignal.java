package rotmg.packages.control;

import org.osflash.signals.Signal;

public class PackageAvailableSignal extends Signal {

	PackageAvailableSignal instance;

	public PackageAvailableSignal getInstance() {
		if (instance == null) {
			instance = new PackageAvailableSignal();
		}
		return instance;
	}
}

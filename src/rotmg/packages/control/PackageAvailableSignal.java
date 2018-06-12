package rotmg.packages.control;

public class PackageAvailableSignal extends Signal {

	PackageAvailableSignal instance;

	public PackageAvailableSignal getInstance() {
		if (instance == null) {
			instance = new PackageAvailableSignal();
		}
		return instance;
	}
}

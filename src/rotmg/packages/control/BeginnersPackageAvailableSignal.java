package rotmg.packages.control;

public class BeginnersPackageAvailableSignal extends Signal {

	static BeginnersPackageAvailableSignal instance;

	public static BeginnersPackageAvailableSignal getInstance() {

		if (instance == null) {
			instance = new BeginnersPackageAvailableSignal();
		}

		return instance;
	}
}

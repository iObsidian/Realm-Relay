package rotmg.packages.control;

public class InitPackagesSignal extends Signal {

	static InitPackagesSignal instance;

	public static InitPackagesSignal getInstance() {

		if (instance == null) {
			instance = new InitPackagesSignal();
		}

		return instance;
	}
}

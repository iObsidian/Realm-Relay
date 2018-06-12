package rotmg.startup.control;

public class StartupSignal extends Signal {

	public static StartupSignal instance;

	public static StartupSignal getInstance() {
		if (instance == null) {
			instance = new StartupSignal();
		}

		return instance;
	}

}

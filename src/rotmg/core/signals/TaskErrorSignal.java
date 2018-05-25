package rotmg.core.signals;

import rotmg.game._as3.Signal;

public class TaskErrorSignal extends Signal<Task> {

	private static TaskErrorSignal instance;

	public static TaskErrorSignal getInstance() {
		if (instance == null) {
			instance = new TaskErrorSignal();
		}
		return instance;
	}

}

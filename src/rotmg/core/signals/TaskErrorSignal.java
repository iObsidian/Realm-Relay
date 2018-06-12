package rotmg.core.signals;
import rotmg.lib.tasks.Task;

public class TaskErrorSignal extends Signal<Task> {

	private static TaskErrorSignal instance;

	public static TaskErrorSignal getInstance() {
		if (instance == null) {
			instance = new TaskErrorSignal();
		}
		return instance;
	}

}

package rotmg.lib.tasks.tasks;

import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import rotmg.lib.tasks.Task;

public class TaskMonitor {

	public static TaskMonitor instance;
	private Vector<Task> tasks;

	public TaskMonitor() {
		super();
		this.tasks = new Vector<Task>(0);
	}

	public static TaskMonitor getInstance() {
		if (instance == null) {
			instance = new TaskMonitor();
		}
		return instance;
	}

	public void add(Task param1) {
		this.tasks.push(param1);
		param1.finished.addOnce(new SignalConsumer<>(this::onTaskFinished));
	}

	public Boolean has(Task param1) {
		return this.tasks.indexOf(param1) != -1;
	}

	private void onTaskFinished(Task param1) {
		this.tasks.splice(this.tasks.indexOf(param1), 1);
	}

}

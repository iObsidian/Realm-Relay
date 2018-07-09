<<<<<<< HEAD:src/kabam/rotmg/core/signals/TaskErrorSignal.java
package kabam.rotmg.core.signals;
=======
package rotmg.core.signals;
import org.osflash.signals.Signal;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/core/signals/TaskErrorSignal.java

import org.osflash.signals.Signal;
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

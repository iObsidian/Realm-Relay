package rotmg.lib.tasks;

import org.osflash.signals.Signal;

/*
 * Very loose, this is an interface
 *
 * TODO rework
 */
public abstract class Task {

	public Signal started;
	public TaskResultSignal finished;
	public TaskResultSignal lastly;
	public boolean isStarted;
	public boolean isFinished;
	public boolean isOK;
	public String error;

	abstract void start();

	abstract void reset();

}

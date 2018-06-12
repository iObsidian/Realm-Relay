package rotmg.lib.tasks;

/*
 * Very loose, this is an interface
 *
 * TODO rework
 */
public abstract class Task {

	abstract void start();

	abstract void reset();

	public Signal started;

	/*public TaskResultSignal finished();

	public TaskResultSignal lastly;*/

	public boolean isStarted;

	public boolean isFinished;

	public boolean isOK;

	public String error;

}

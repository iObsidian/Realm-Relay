package robotlegs.bender.bundles.mvcs;

public abstract class Mediator {

	public void addViewListener(String reconnect, Object onReconnect) {
	}

	public abstract void initialize();

	public void removeViewListener(String reconnect, Object onReconnect) {
	}

}

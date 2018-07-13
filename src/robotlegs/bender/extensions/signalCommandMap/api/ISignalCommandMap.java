package robotlegs.bender.extensions.signalCommandMap.api;

public class ISignalCommandMap {

	public static ISignalCommandMap instance = new ISignalCommandMap();

	public static ISignalCommandMap getInstance() {
		return instance;
	}
}

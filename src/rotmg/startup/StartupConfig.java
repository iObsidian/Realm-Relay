package rotmg.startup;

import org.swiftsuspenders.Injector;
import robotlegs.bender.extensions.signalCommandMap.api.ISignalCommandMap;
import robotlegs.bender.framework.api.IConfig;

public class StartupConfig implements IConfig {

	public Injector injector;

	public ISignalCommandMap commandMap;

	public StartupConfig() {
		super();
	}

	public void configure() {
		/*this.injector.map(StartupSequence).asSingleton();
		this.commandMap.map(StartupSignal).toCommand(StartupCommand);*/
	}
}

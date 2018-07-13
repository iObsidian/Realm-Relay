package rotmg.characters.deletion;

import org.swiftsuspenders.Injector;
import robotlegs.bender.extensions.mediatorMap.api.IMediatorMap;
import robotlegs.bender.extensions.signalCommandMap.api.ISignalCommandMap;
import robotlegs.bender.framework.api.IConfig;

public class DeletionConfig implements IConfig {

	public Injector injector = Injector.getInstance();

	public IMediatorMap mediatorMap = IMediatorMap.getInstance();

	public ISignalCommandMap commandMap = ISignalCommandMap.getInstance();

	public DeletionConfig() {
		super();
	}

	public void configure() {
		/*this.injector.map(DeleteCharacterTask);
		this.mediatorMap.map(ConfirmDeleteCharacterDialog).toMediator(ConfirmDeleteCharacterMediator);
		this.commandMap.map(DeleteCharacterSignal).toCommand(DeleteCharacterCommand);*/
	}

}

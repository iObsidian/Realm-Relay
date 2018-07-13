package rotmg.characters.reskin;

import robotlegs.bender.framework.api.IConfig;
import robotlegs.bender.framework.api.IContext;
import rotmg.characters.reskin.control.ReskinHandler;
import rotmg.messaging.GameServerConnection;
import rotmg.messaging.outgoing.Reskin;
import rotmg.net.api.MessageMap;


public class ReskinConfig implements IConfig {

	public IContext context;

	/*public IMediatorMap mediatorMap;

	public ISignalCommandMap commandMap;*/

	public MessageMap messageMap;

	public ReskinConfig() {
		super();
	}

	public void configure() {
		/*this.mediatorMap.map(ReskinCharacterView).toMediator(ReskinCharacterMediator);
		this.mediatorMap.map(ReskinPanel).toMediator(ReskinPanelMediator);
		this.commandMap.map(AddReskinConsoleActionSignal).toCommand(AddReskinConsoleActionCommand);
		this.commandMap.map(OpenReskinDialogSignal).toCommand(OpenReskinDialogCommand);
		this.commandMap.map(ReskinCharacterSignal).toCommand(ReskinCharacterCommand);*/
		this.messageMap.map(GameServerConnection.RESKIN).toMessage(Reskin.class).toHandler(ReskinHandler.class);
		/*this.context.lifecycle.afterInitializing(this.onInit);*/
	}

	private void onInit() {
		/*AddReskinConsoleActionSignal.getInstance().dispatch();*/
	}
}
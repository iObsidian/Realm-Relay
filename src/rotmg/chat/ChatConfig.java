package rotmg.chat;

import org.swiftsuspenders.Injector;
import robotlegs.bender.extensions.mediatorMap.api.IMediatorMap;
import robotlegs.bender.extensions.signalCommandMap.api.SignalCommandMap;
import robotlegs.bender.framework.api.IConfig;
import rotmg.chat.control.TextHandler;
import rotmg.messaging.GameServerConnection;
import rotmg.messaging.incoming.Text;
import rotmg.net.api.MessageMap;
import rotmg.net.impl.MessageCenter;

public class ChatConfig implements IConfig {

	public Injector injector = Injector.getInstance();

	public MessageMap messageMap = MessageCenter.getInstance();

	public SignalCommandMap commandMap = SignalCommandMap.getInstance();

	public IMediatorMap mediatorMap = IMediatorMap.getInstance();

	public ChatConfig() {
		super();
	}

	public void configure() {
		/*this.injector.map(ChatModel.class).asSingleton();
		this.injector.map(ChatConfig).asSingleton();
		this.injector.map(ChatListItemFactory).asSingleton();
		this.injector.map(TellModel).asSingleton();
		this.injector.map(AddChatSignal).asSingleton();
		this.injector.map(ScrollListSignal).asSingleton();
		this.injector.map(ShowChatInputSignal).asSingleton();
		this.commandMap.map(AddTextLineSignal).toCommand(ParseAddTextLineCommand);
		this.commandMap.map(ExitGameSignal).toCommand(ClearTellModelCommand);
		this.commandMap.map(GameClosedSignal).toCommand(ClearTellModelCommand);*/
		this.messageMap.map(GameServerConnection.TEXT).toMessage(Text.class).toHandler(TextHandler.class);
		/*this.commandMap.map(ParseChatMessageSignal).toCommand(ParseChatMessageCommand);
		this.mediatorMap.map(ChatInput).toMediator(ChatInputMediator);
		this.mediatorMap.map(ChatList).toMediator(ChatListMediator);
		this.mediatorMap.map(Chat).toMediator(ChatMediator);
		this.mediatorMap.map(ChatInputNotAllowed).toMediator(ChatInputNotAllowedMediator);*/
	}
}

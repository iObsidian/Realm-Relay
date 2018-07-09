package rotmg.signals;

import rotmg.chat.model.ChatMessage;

public class AddTextLineSignal extends DeferredQueueSignal<ChatMessage> {

	private static AddTextLineSignal instance;

	public static AddTextLineSignal getInstance() {
		if (instance == null) {
			instance = new AddTextLineSignal();
		}
		return instance;
	}
}

package realmrelay.game.signals;

import realmrelay.game.chat.model.ChatMessage;

public class AddTextLineSignal extends DeferredQueueSignal<ChatMessage> {

	private static AddTextLineSignal instance;

	public static AddTextLineSignal getInstance() {
		if (instance == null) {
			instance = new AddTextLineSignal();
		}
		return instance;
	}
}

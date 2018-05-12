package realmrelay.game.signals;

import kabam.lib.signals.DeferredQueueSignal;
import kabam.rotmg.chat.model.ChatMessage;
import realmrelay.game.chat.model.ChatMessage;

public class AddTextLineSignal extends DeferredQueueSignal<ChatMessage> {

	static AddTextLineSignal instance;

	public static AddTextLineSignal getInstance() {
		if (instance == null) {
			instance = new AddTextLineSignal();
		}
		return instance;
	}
}

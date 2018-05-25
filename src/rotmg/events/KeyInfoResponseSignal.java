package rotmg.events;

import rotmg.game._as3.Signal;
import rotmg.game.messaging.incoming.KeyInfoResponse;
import rotmg.game.signals.AddTextLineSignal;
import rotmg.game.signals.GiftStatusUpdateSignal;
import rotmg.messaging.incoming.KeyInfoResponse;

public class KeyInfoResponseSignal extends Signal<KeyInfoResponse> {

	static KeyInfoResponseSignal instance;

	public static KeyInfoResponseSignal getInstance() {
		if (instance == null) {
			instance = new KeyInfoResponseSignal();
		}
		return instance;
	}

}

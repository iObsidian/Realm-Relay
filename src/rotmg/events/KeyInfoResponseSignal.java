package rotmg.events;

import org.osflash.signals.Signal;

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

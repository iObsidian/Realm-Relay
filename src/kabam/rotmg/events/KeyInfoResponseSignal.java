package kabam.rotmg.events;

import kabam.rotmg.messaging.incoming.KeyInfoResponse;
import org.osflash.signals.Signal;

public class KeyInfoResponseSignal extends Signal<KeyInfoResponse> {

	static KeyInfoResponseSignal instance;

	public static KeyInfoResponseSignal getInstance() {
		if (instance == null) {
			instance = new KeyInfoResponseSignal();
		}
		return instance;
	}

}

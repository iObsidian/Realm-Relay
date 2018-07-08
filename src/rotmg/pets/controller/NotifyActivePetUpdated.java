package rotmg.pets.controller;

import org.osflash.signals.Signal;

public class NotifyActivePetUpdated extends Signal {

	static NotifyActivePetUpdated instance;

	public static NotifyActivePetUpdated getInstance() {
		if (instance == null) {
			instance = new NotifyActivePetUpdated();
		}

		return instance;
	}
}

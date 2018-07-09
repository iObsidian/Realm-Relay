package rotmg.news.controller;

import org.osflash.signals.Signal;

public class NewsButtonRefreshSignal extends Signal {

	static NewsButtonRefreshSignal instance;

	public static NewsButtonRefreshSignal getInstance() {

		if (instance == null) {
			instance = new NewsButtonRefreshSignal();
		}

		return instance;
	}
}

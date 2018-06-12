package rotmg.news.controller;

public class NewsButtonRefreshSignal  extends Signal {
	
	static NewsButtonRefreshSignal instance;

	public static NewsButtonRefreshSignal getInstance() {

		if (instance == null) {
			instance = new NewsButtonRefreshSignal();
		}
		
		return instance;
	}
}

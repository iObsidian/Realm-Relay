package rotmg.core.service;

import rotmg.core.service.tracking.GoogleAnalyticsTracker;

public class GoogleAnalytics {

	private GoogleAnalyticsTracker tracker;

	public GoogleAnalytics() {
		super();
	}

	static GoogleAnalytics instance;

	public static GoogleAnalytics getInstance() {
		if (instance == null) {
			instance = new GoogleAnalytics();
		}
		return instance;
	}

	public void init(String param1, String param2) {

	}


	public void trackEvent(String param1, String param2) {
		trackEvent(param1, param2, "", 0);
	}

	public void trackEvent(String param1, String param2, String param3, double param4) {

	}

	public void trackPageView(String param1) {
	}


}
